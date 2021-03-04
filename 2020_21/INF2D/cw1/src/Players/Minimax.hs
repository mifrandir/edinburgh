{-
    Module: Minimax.

    *** PART I (60pt) and PART II (10pt) ***
-}
module Players.Minimax where

import Action
import Board
import Cell
import Constants
import Data.Array
import Data.Bifunctor
import Data.Char
import Data.Graph
import Data.List
import Data.Map (Map)
import qualified Data.Map as Map
import Data.Maybe
import Data.Ord
import Data.Sequence (Seq ((:<|)), (><))
import qualified Data.Sequence as Sequence
import Data.Tree
import Debug.Trace
import Game
import Player
import Players.Dumb (dumbAction)
import Types

{-
    StateTree util.
-}

-- Map a function through the nodes of the tree.
mapStateTree :: (v -> w) -> StateTree v a -> StateTree w a
mapStateTree f (StateTree x ts) = StateTree (f x) [(a, mapStateTree f t) | (a, t) <- ts]

-- Calculate the depth of the tree (used to test pruneDepth).
stateTreeDepth :: StateTree v a -> Int
stateTreeDepth (StateTree _ []) = 0
stateTreeDepth (StateTree _ ts) = 1 + (maximum (map (stateTreeDepth . snd) ts))

-- Calculate the breadth of the tree (used to test pruneBreadth).
stateTreeBreadth :: StateTree v a -> Int
stateTreeBreadth (StateTree _ []) = 0
stateTreeBreadth (StateTree _ ts) = max (length ts) (maximum (map (stateTreeBreadth . snd) ts))

{-
    Result util.
-}

-- Negating the result is simply negating the score. You may ignore this although it may be useful
-- to implement the minimax algorithm.
negResult :: Result -> Result
negResult (Result x as) = Result (- x) as

{-
    *** Part I.a (10pt) ***

    First, we will generate a tree containing all the possible game states.
-}

-- Given a game, return a tree that encodes all the possible future game states.
-- [Hint: Use 'validActions' and 'performAction'.]
-- [Note: To speed things up, you may want to, at this stage, heuristically select which actions are
--  more relevant. In particular, you probably don't want to consider every single possible wall.]
generateGameTree :: Game -> GameTree
generateGameTree g = StateTree g $ map ext $ filter isValid partialChildren
  where
    actions :: [Action]
    actions = validActions g
    ext :: (Action, Maybe GameTree) -> (Action, GameTree)
    ext (a, Just t) = (a, t)
    makeGameTree :: Maybe Game -> Maybe GameTree
    makeGameTree (Just g) = Just $ generateGameTree g
    makeGameTree Nothing = Nothing
    isValid :: (Action, Maybe GameTree) -> Bool
    isValid (_, x) = isJust x
    partialChildren :: [(Action, Maybe GameTree)]
    partialChildren = [(a, makeGameTree $ performAction g a) | a <- validActions g]

{-
    *** PART I.b (5pt) ***

    Re-order the tree so that when traversed by the minimax algorithm, when it traverses the
    branches at each node, finds either the higher scores or the lower scores first, depending on
    the depth of the tree.
-}

-- Higher scoring nodes go first.
-- [Hint: You should use 'lowFirst'.]
highFirst :: (Ord v) => StateTree v a -> StateTree v a
highFirst (StateTree g cs) = StateTree g $ sortBy (flip cmp) $ map (Data.Bifunctor.second highFirst) cs
  where
    cmp :: (Ord v) => (a, StateTree v a) -> (a, StateTree v a) -> Ordering
    cmp (_, StateTree g1 _) (_, StateTree g2 _) = compare g1 g2

{-
    *** Part I.c (5pt) ***

    We don't want to look at all possible future game states as that would consume too much time and
    memory. Instead, we will only look a given number of steps into the future. Formally, the future
    game states are encoded in a tree, so we need a function that reduces the depth of a tree.
-}

-- Given a depth and a tree, return the same tree but cutting off the branches when the depth is
-- exceeded.
-- [Hint: You may want to use guards and recursion.]
pruneDepth :: Int -> StateTree v a -> StateTree v a
pruneDepth 0 (StateTree g _) = StateTree g []
pruneDepth d (StateTree g cs) = StateTree g $ map (Data.Bifunctor.second $ pruneDepth (d - 1)) cs

{-
    *** Part I.d (5pt) ***

    Similarly, we can also make our tree smaller by not considering all the possible game states at
    a given point. We need a function that reduces the breadth (or width) of a tree.
-}

-- Given a breadth (Int n) and a tree, return the same tree but only keeping the first n branches at
-- every node.
-- [Hint: Use 'take'.]
pruneBreadth :: Int -> StateTree v a -> StateTree v a
pruneBreadth d (StateTree g cs) = StateTree g $ map (Data.Bifunctor.second $ pruneBreadth d) $ take d cs

{-
    *** Part I.e (15pt) ***

    A crucial part of the minimax algorithm is defining a good utility function. It should measure
    how good a game position is for the current player. In our case, a game state should be better
    than another one if the player is closer to its winning positions.
-}

minimumDistanceScore :: Board -> Player -> Maybe Int
minimumDistanceScore b p
  | null validDists = Nothing
  | otherwise = Just $ minimum validDists
  where
    validDists :: [Int]
    validDists = mapMaybe (lookupIn allDists) (winningPositions p)
    lookupIn :: Map Cell Int -> Cell -> Maybe Int
    lookupIn ds c = Map.lookup c ds
    allDists = bfs (Sequence.singleton (currentCell p, 0)) Map.empty
    -- doing bfs to calculate distances to all cells that are
    -- closer than the winning cells (and the closest winning cell)
    bfs :: Seq (Cell, Int) -> Map Cell Int -> Map Cell Int
    -- if the queue is empty, bfs is done
    bfs Sequence.Empty ds = ds
    -- the sequence is our queue of nodes to visit (with a distance to write)
    -- and the map holds the distance values we've found so far
    bfs ((c, d) :<| cds) ds
      | isJust (Map.lookup c ds) = bfs cds ds -- if the element is already present, skip it
      | c `elem` winningPositions p = newDs -- if the position is winning, we've found the shortest path
      | otherwise = bfs (cds >< newCds) newDs -- insert the distance into the map and extend queue
      where
        -- updated states of the queu and the distances
        newDs = Map.insert c d ds
        newCds = Sequence.fromList $ zip neighbours (repeat $ d + 1)
        -- reachable cells that don't have a distance just yet
        neighbours = filter (\x -> isNothing $ Map.lookup x ds) (reachableCells b c)

-- Assign a value to each game (from the point of view of the current player).
-- [Hint 1: You may want to calculate the distance between the player's current cell and its winning
--  positions.]
-- [Hint 2: One way would be to use 'reachableCells' repeatedly.]
utility :: Game -> Int
utility (Game b ps)
  -- winning/losing positions don't require any real evaluation
  | currentCell p1 `elem` winningPositions p1 = - turn p1 - maxBound :: Int -- later wins are worse
  | currentCell p2 `elem` winningPositions p2 = turn p1 + minBound :: Int -- later losses are better
  -- common case, we check both scores to avoid cutting off by the losing player
  | isJust s1 && isJust s2 = - fromJust s1
  -- if there are no winning paths, we don't re
  | otherwise = minBound :: Int -- Players want to avoid illegal game states
  where
    p1 = head ps
    p2 = ps !! 1
    s1 = minimumDistanceScore b p1
    s2 = minimumDistanceScore b p2

-- Lifting the utility function to work on trees.
evalTree :: GameTree -> EvalTree
evalTree = mapStateTree utility

{-
    *** Part I.f (20pt) ***

    Finally, we ask you to implement the minimax algorithm. Given an evaluation tree, it should
    return the a high scoring action (according to the minimax algorithm).
-}

-- Given an evaluation tree (it stores a score in the node and each branch is labelled with the
-- action that leads to the next child) return a list of actions
-- [Hint 1: Use a helper function to keep track of the highest and lowest scores.]
-- [Hint 2: Use the 'Result' datatype.]
minimaxFromTree :: EvalTree -> Action
minimaxFromTree (StateTree _ cs) = trace ("utility: " ++ show v) a
  where
    (a, v) = maximumBy cmp choices
    cmp :: (Action, Int) -> (Action, Int) -> Ordering
    cmp (_, x) (_, y) = compare x y
    choices :: [(Action, Int)]
    choices = map (second findMin) cs
    findMin :: EvalTree -> Int
    findMin (StateTree v []) = - v -- if the leaf is on the min-level, it's an opposing utility value
    findMin (StateTree _ cs) = minimum $ map (\(_, t) -> findMax t) cs
    findMax :: EvalTree -> Int
    findMax (StateTree v []) = v
    findMax (StateTree _ cs) = maximum $ map (\(_, t) -> findMin t) cs

{-
    *** Part II (10pt) ***

    Extension of Part I.e, using alpha-beta pruning. You will need to change the 'minimax' function
    below to make it use this function.
-}

-- Same as above but now use alpha-beta pruning.
-- [Hint 1: Extend the helper function in I.e to keep track of alpha and beta.]
-- [Hint 2: Use the 'Result' datatype.]
minimaxABFromTree :: EvalTree -> Action
minimaxABFromTree t = trace ("utility: " ++ show v) a
  where
    (a, v) = findMaxAction (minBound :: Int) (maxBound :: Int) t
    cmp :: (Action, Int) -> (Action, Int) -> Ordering
    cmp (_, x) (_, y) = compare x y
    findMaxAction :: Int -> Int -> EvalTree -> (Action, Int)
    findMaxAction a b (StateTree v [(e, t)]) = (e, findMin a b t)
    findMaxAction a b (StateTree v ((e, t) : cs))
      | curV >= b = (e, curV)
      | otherwise = maximumBy cmp [(e, curV), oth]
      where
        curV = findMin a b t
        a' = max curV a
        oth = findMaxAction a' b (StateTree v cs)
    findMin :: Int -> Int -> EvalTree -> Int
    findMin a b (StateTree v []) = trace "Flipping" (- v)
    findMin a b (StateTree v [(_, t)]) = findMax a b t
    findMin a b (StateTree v ((_, t) : cs))
      | v' <= a = v'
      | otherwise = min v' (findMin a b' (StateTree v cs))
      where
        v' = findMax a b t
        b' = min v' b
    findMax :: Int -> Int -> EvalTree -> Int
    findMax a b (StateTree v []) = v
    findMax a b (StateTree v [(_, t)]) = findMin a b t
    findMax a b (StateTree v ((_, t) : cs))
      | v' >= b = v'
      | otherwise = max v' (findMax a' b (StateTree v cs))
      where
        v' = findMin a b t
        a' = max v' a

{-
    Putting everything together.
-}

-- Given depth for pruning (should be even).
depth :: Int
depth = 6

-- Given breadth for pruning.
breadth :: Int
breadth = 10

-- Function that combines all the different parts implemented in Part I.
minimax :: Game -> Action
minimax =
  minimaxABFromTree -- or 'minimaxABFromTree'
    . pruneBreadth breadth
    . highFirst
    . evalTree
    . pruneDepth depth
    . generateGameTree

-- Given a game state, calls minimax and returns an action.
minimaxAction :: Board -> [Player] -> String -> Int -> Maybe Action
minimaxAction b ps _ r = let g = Game b ps in minimaxAction' g (minimax g)
  where
    -- Goes through the list of actions until it finds a valid one.
    minimaxAction' :: Game -> Action -> Maybe Action
    minimaxAction' g' (Move s)
      | validStepAction g' s = Just (Move s)
      | otherwise = error "Minimax chose an invalid action."
    minimaxAction' g' (Place w)
      | validWallAction g' w = Just (Place w)
      | otherwise = error "Minimax chose an invalid action."

-- Make minimaxPlayer in the usual way using 'minimaxAction'.
makeMinimaxPlayer :: String -> Cell -> Int -> [Cell] -> Player
makeMinimaxPlayer n c rws wps =
  Player
    { name = n,
      turn = 1,
      currentCell = c,
      remainingWalls = rws,
      winningPositions = wps,
      isHuman = False,
      chooseAction = minimaxAction
    }