-- Informatics 1 - Introduction to Computation
-- Functional Programming Tutorial 5
--
-- Week 5(14-18 Oct.)

module Tutorial5 where

import Data.Char
import Data.Ratio
import Test.QuickCheck

-- 1. Map

-- a.
doubles :: [Int] -> [Int]
doubles xs = map (*2) xs

-- b.        
penceToPounds :: [Int] -> [Float]
penceToPounds xs = map (\x -> (fromIntegral x :: Float) / 100) xs

-- c.
uppers :: String -> String
uppers xs = map toUpper xs

-- d.
uppersComp :: String -> String
uppersComp xs = [toUpper x | x <- xs]

propUppers :: String -> Bool
propUppers str = uppers str == uppersComp str


-- 2. Filter
-- a.
alphas :: String -> String
alphas str = filter (\x -> isDigit x && isLetter x) str

-- b.
above :: Int -> [Int] -> [Int]
above x ys = filter (> x) ys

-- c.
unequals :: [(Int,Int)] -> [(Int,Int)]
unequals xys = filter (\(x,y) -> x /= y) xys

-- e.
rmChar :: Char -> String -> String
rmChar c str = filter (== c) str

-- e.
rmCharComp :: Char -> String -> String
rmCharComp c str = [x | x <- str, x /= c]


-- 3. Comprehensions vs. map & filter
-- a.
largeDoubles :: [Int] -> [Int]
largeDoubles xs = [2 * x | x <- xs, x > 3]

largeDoubles' :: [Int] -> [Int]
largeDoubles' xs = map (2*) (filter (>3) xs)

prop_largeDoubles :: [Int] -> Bool
prop_largeDoubles xs = largeDoubles xs == largeDoubles' xs 

-- b.
reverseEven :: [String] -> [String]
reverseEven strs = [reverse s | s <- strs, even (length s)]

reverseEven' :: [String] -> [String]
reverseEven' strs = map reverse (filter (\x -> even (length x)) strs)

prop_reverseEven :: [String] -> Bool
prop_reverseEven strs = reverseEven strs == reverseEven' strs



-- 4. Foldr
-- a.
andRec :: [Bool] -> Bool
andRec []     = True
andRec (x:xs) = x && andRec xs

andFold :: [Bool] -> Bool
andFold xs = foldr (&&) True xs

prop_and :: [Bool] -> Bool
prop_and xs = andRec xs == andFold xs

-- b.
concatRec :: [[a]] -> [a]
concatRec [] = []
concatRec (x:xs) = x ++ concatRec xs

concatFold :: [[a]] -> [a]
concatFold xs = foldr (++) [] xs

prop_concat :: [String] -> Bool
prop_concat strs = concatRec strs == concatFold strs

-- c.
rmCharsRec :: String -> String -> String
rmCharsRec [] str = str
rmCharsRec _ [] = []
rmCharsRec (x:xs) str = rmCharsRec xs (rmChar x str)

rmCharsFold :: String -> String -> String
rmCharsFold xs str = foldr (\x str -> rmChar x str) str xs

prop_rmChars :: String -> String -> Bool
prop_rmChars chars str = rmCharsRec chars str == rmCharsFold chars str


type Matrix = [[Rational]]

-- 5
-- a.
uniform :: [Int] -> Bool
uniform [] = True
uniform (x:xs) = all (x==) xs

-- b.
valid :: Matrix -> Bool
valid mtrx = not (null mtrx) && not (null (head mtrx)) && uniform (map (length) mtrx)


-- 6.
matrixWidth :: Matrix -> Int
matrixWidth (x:xs) = length x

matrixHeight :: Matrix -> Int
matrixHeight m = length m

plusM :: Matrix -> Matrix -> Matrix
plusM m1 m2
    | not (valid m1) || not (valid m2) = error "One of the given matrices is not valid."
    | matrixWidth m1 /= matrixWidth m2 = error "Matrices do not have the same width."
    | matrixHeight m1 /= matrixHeight m2 = error "Matrices do not have the same height."
    | otherwise = [[x+y | (x,y) <- zip xs ys] | (xs, ys) <- zip m1 m2]

-- 7.
transpose :: Matrix -> Matrix
transpose ([]:_) = []
transpose xs = map head xs : transpose (map tail xs)

timesM :: Matrix -> Matrix -> Matrix
timesM m1 m2
    | not (valid m1) || not (valid m2) = error "One of the given matrices is not valid."
    | otherwise = [[dot x y | y <- transpose m2] | x <- m1]

dot :: [Rational] -> [Rational] -> Rational
dot v1 v2 = sum [x*y | (x, y) <- zip v1 v2]

-- 8.
-- b.
zipWith' :: (a -> b -> c) -> [a] -> [b] -> [c]
zipWith' f xs ys = [f x y | (x, y) <- zip xs ys]

-- c.
zipWith'' :: (a -> b -> c) -> [a] -> [b] -> [c]
zipWith'' f xs ys = map (uncurry f) (zip xs ys)

-- -----------------------------------
-- -----------------------------------
-- -- Optional material
-- -----------------------------------
-- -----------------------------------
-- -- 9.

-- Mapping functions
mapMatrix :: (a -> b) -> [[a]] -> [[b]]
mapMatrix f m = [[f x | x <- xs] | xs <- m]

zipMatrix :: (a -> b -> c) -> [[a]] -> [[b]] -> [[c]]
--zipMatrix f m1 m2 = [zipWith f xs ys | (xs, ys) <- zip m1 m2]
zipMatrix f = zipWith (zipWith f)


-- All ways of deleting a single element from a list
removes :: [a] -> [[a]]     
--removes xs = [rm xs i | i <- [0..(length xs)-1]]
--    where rm xs i = take i xs ++ drop (i+1) xs
removes (x:[]) = [[]]
removes (x:xs) = xs : map (x:) (removes xs)

-- Produce a matrix of minors from a given matrix
minors :: Matrix -> [[Matrix]]
minors m = undefined

-- A matrix where element a_ij = (-1)^(i + j)
signMatrix :: Int -> Int -> Matrix
signMatrix w h = undefined
        
determinant :: Matrix -> Rational
determinant = undefined

cofactors :: Matrix -> Matrix
cofactors m = undefined        
                
scaleMatrix :: Rational -> Matrix -> Matrix
scaleMatrix k = undefined

inverse :: Matrix -> Matrix
inverse m = undefined

-- Tests
identity :: Int -> Matrix
identity n = undefined

prop_inverse2 :: Rational -> Rational -> Rational 
                -> Rational -> Property
prop_inverse2 a b c d = undefined

type Triple a = (a,a,a)
        
prop_inverse3 :: Triple Rational -> 
                 Triple Rational -> 
                 Triple Rational ->
                 Property
prop_inverse3 r1 r2 r3 = undefined