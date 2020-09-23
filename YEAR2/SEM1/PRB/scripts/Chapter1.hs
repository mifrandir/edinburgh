module Chapter1 where

import Data.Ratio
import Util

p1 :: Rational
p1 = getP (\(x : y : z : []) -> x == y || y == z) $ fullRollDie 6 3

p2 :: Rational
p2 = getP (\x -> a x && b x) $ [0 .. 600]
  where
    a x = mod x 2 + mod x 5 == 0
    b x = not $ mod x 3 == 0