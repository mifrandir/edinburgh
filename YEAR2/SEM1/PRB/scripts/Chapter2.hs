module Chapter2 where

import PRB

p1 :: Rational
p1 = distinctFiniteE varX $ allStrings [0, 1] 19
  where
    varX :: [Integer] -> Integer
    varX = countLocalEvents 4 1 $ (== 2) . sum