{-
    Module: Reed.

    *** PART III (10 pt) ***

    Define a player that uses teh Reed opening and play against it. Is the Reed opening a good
    opening? Write your answers in Reed.txt.
-}
module Players.Reed where

import Action
import Game
import Types
import Players.Minimax
import Debug.Trace

-- Create a player that starts with the Reed opening. After that, you may use your minimax action or
-- the given action for DumbPlayer.
-- [Hint 1: Use the variable 'turn' in Player.]
-- [Hint 2: Use 'wallTop' to get the walls you need.]
-- [Hint 3: Don't forget to check that the action is valid using 'validWallAction'.]
reedPlayerAction :: Board -> [Player] -> String -> Int -> Maybe Action
reedPlayerAction b ps _ r = let g = Game b ps in reedPlayerAction' g $ trace ("turn="++show (turn player)++" act="++show act) act
  where
    player :: Player
    player = head ps
    act :: Maybe Action
    act
      | turn player == 1 = Just (Place $ wallTop ('c', 3))
      | turn player == 2 = Just (Place $ wallTop ('f', 3))
      | otherwise = Nothing
    reedPlayerAction' :: Game -> Maybe Action -> Maybe Action
    reedPlayerAction' _ Nothing = minimaxAction b ps "" r
    reedPlayerAction' g a@(Just (Place w))
      | validWallAction g w = a
      | otherwise = reedPlayerAction' g Nothing

-- We build a Reed player from a name, a starting cell, a number of walls, an array of winning
-- positions and 'commandToAction'.
makeReedPlayer :: String -> Cell -> Int -> [Cell] -> Player
makeReedPlayer n c rws wps =
  Player
    { name = n,
      turn = 1,
      currentCell = c,
      remainingWalls = rws,
      winningPositions = wps,
      isHuman = False,
      chooseAction = reedPlayerAction
    }
