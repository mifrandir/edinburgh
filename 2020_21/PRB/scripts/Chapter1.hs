module Chapter1 where

import Data.Ratio
import PRB

p1 :: Rational
p1 = distinctFiniteSuccessP (\(x : y : z : []) -> x == y || y == z) $ allStrings [1 .. 6] 3

p2 :: Rational
p2 = distinctFiniteSuccessP (\x -> a x && b x) $ [0 .. 600]
  where
    a x = mod x 2 + mod x 5 == 0
    b x = not $ mod x 3 == 0