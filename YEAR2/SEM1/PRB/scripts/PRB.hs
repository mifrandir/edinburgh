module PRB where

import Data.Ratio

allStrings :: [a] -> Integer -> [[a]]
allStrings _ 0 = [[]]
allStrings xs num = [x : l | x <- xs, l <- all_lower]
  where
    all_lower = allStrings xs $ num - 1

distinctFiniteSuccessP :: (a -> Bool) -> [a] -> Rational
distinctFiniteSuccessP p l = (count [p r | r <- l]) % toInteger (length l)
  where
    count [] = 0
    count (x : xs)
      | x = r + 1
      | otherwise = r
      where
        r = count xs

distinctFiniteE :: (a -> Integer) -> [a] -> Rational
distinctFiniteE f l = sum (map f l) % (toInteger $ length l)

countLocalEvents :: Integer -> Integer -> ([a] -> Bool) -> [a] -> Integer
countLocalEvents len offset f xs
  | len > toInteger (length xs) = 0
  | otherwise = a + countLocalEvents len offset f (safeDrop offset xs)
  where
    a :: Integer
    a = toInteger $ fromEnum $ f (take (fromInteger len) xs)
    safeDrop :: Integer -> [a] -> [a]
    safeDrop n xs
      | n > toInteger (length xs) = []
      | otherwise = drop (fromInteger n) xs