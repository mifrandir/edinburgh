module Players.MonteCarlo where

import Constants
import Data.Maybe
import Debug.Trace
import Game
import Players.Minimax
import Types

{-
    Constants
-}

minimaxDepth :: Int
minimaxDepth = 1

simulationDepth :: Int
simulationDepth = 4*boardSize

numRuns :: Int
numRuns = 100

p :: Int
p = 37

mctsWinning :: Int
mctsWinning = (maxBound :: Int) `div` numRuns

{-
    Utils
-}

-- making a random number from a small seed
generateNumber :: Int -> Int -> Int -> Int
generateNumber lo hi seed = lo + (p * seed) `mod` (hi - lo)
  where
    tracer x = trace ("lo=" ++ show lo ++ " hi=" ++ show hi ++ " seed=" ++ show seed ++ " => " ++ show x) x

-- picking n random elements from a list with a given seed
-- duplication is possible
pick :: Int -> Int -> [a] -> [a]
pick seed 0 xs = []
pick seed num xs = (xs !! i) : pick (seed `div` length xs) (num -1) rest
  where
    i = seed `mod` length xs
    rest = take (length xs) $ drop (i + 1) $ cycle xs

{-
    Evaluation
-}

-- identical to minimax utility except we are using both scores
mctsUtility :: Game -> Int
mctsUtility (Game b ps)
  -- winning/losing positions don't require any real evaluation
  | currentCell p1 `elem` winningPositions p1 = turn p1 + mctsWinning -- later losses are better
  | currentCell p2 `elem` winningPositions p2 = turn p1 - mctsWinning -- later losses are better
  -- common case
  | isJust s1 && isJust s2 = fromJust s2 - fromJust s1
  -- if there are no winning paths, we don't re
  | otherwise = - mctsWinning -- Players want to avoid illegal game states
  where
    p1 = head ps
    p2 = ps !! 1
    s1 = minimumDistanceScore b p1
    s2 = minimumDistanceScore b p2

evalMCTSTree :: Int -> GameTree -> EvalTree
evalMCTSTree seed (StateTree g []) = StateTree (simulate g seed simulationDepth) []
evalMCTSTree seed (StateTree g ts) = StateTree (mctsUtility g) [(a, evalMCTSTree seed t) | (a, t) <- ts]

-- simulates a game with a given seed for a
-- given number of steps and returns the final score
--
-- if the starting player loses, the score is decremented, if they win
-- it's incremented and if no end is reached in the given number of moves,
-- the result is unchanged
simulate :: Game -> Int -> Int -> Int
simulate g@(Game b ps) seed steps
  | steps <= 0 = 0
  | c1 `elem` t1 = numRuns
  | c2 `elem` t2 = - numRuns
  | otherwise = sum results `div` length results
  where
    results = map (playToDepth simulationDepth) $ pick seed numRuns $ children g
    playToDepth :: Int -> Game -> Int
    playToDepth steps g@(Game b ps)
      | steps <= 0 = mctsUtility g
      | c1 `elem` t1 = mctsUtility g
      | c2 `elem` t2 = mctsUtility g
      | null cs = mctsUtility g
      -- since performAction changes the protagonist, we need to invert the result
      | otherwise = - playToDepth (steps - 1) (next (seed + steps) cs)
      where
        cs = children g
    p1 = head ps
    p2 = ps !! 1
    c1 = currentCell p1
    c2 = currentCell p2
    t1 = winningPositions p1
    t2 = winningPositions p2
    next :: Int -> [Game] -> Game
    next seed gs = gs !! generateNumber 0 (length gs) seed
    children :: Game -> [Game]
    children g = map (fromJust . performAction g) $ validActions g

{-
  Putting everything together
-}

monteCarlo :: Int -> Game -> Action
monteCarlo seed =
  minimaxABFromTree -- or 'minimaxABFromTree'
    . pruneBreadth breadth
    . highFirst
    . evalMCTSTree seed
    . pruneDepth minimaxDepth
    . generateGameTree

monteCarloAction :: Board -> [Player] -> String -> Int -> Maybe Action
monteCarloAction b ps _ r = let g = Game b ps in monteCarloAction' g (monteCarlo r g)
  where
    -- Goes through the list of actions until it finds a valid one.
    monteCarloAction' :: Game -> Action -> Maybe Action
    monteCarloAction' g' (Move s)
      | validStepAction g' s = Just (Move s)
      | otherwise = error "MonteCarlo chose an invalid move action."
    monteCarloAction' g' (Place w)
      | validWallAction g' w = Just (Place w)
      | otherwise = error "MonteCarlo chose an invalid wall action."

makeMonteCarloPlayer :: String -> Cell -> Int -> [Cell] -> Player
makeMonteCarloPlayer n c rws wps =
  Player
    { name = n,
      turn = 1,
      currentCell = c,
      remainingWalls = rws,
      winningPositions = wps,
      isHuman = False,
      chooseAction = monteCarloAction
    }