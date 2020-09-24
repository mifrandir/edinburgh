module Util where

import Data.Ratio

fullRollDie :: Integer -> Integer -> [[Integer]]
fullRollDie n 0 = [[]]
fullRollDie n num = [x : l | x <- [1 .. n], l <- all_lower]
  where
    all_lower = fullRollDie n $ num - 1

getP :: (a -> Bool) -> [a] -> Rational
getP p l = (count [p r | r <- l]) % toInteger (length l)
  where
    count [] = 0
    count (x : xs)
      | x = r + 1
      | otherwise = r
      where
        r = count xs
