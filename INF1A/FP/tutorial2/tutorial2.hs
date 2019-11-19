-- Informatics 1 - Introduction to Computation
-- Functional Programming Tutorial 2
--
-- Week 2(23-27 Sep.)

import Data.Char
import Data.List
import Test.QuickCheck
import Control.Monad (guard)
import Control.Exception


-- 1. halveEvens

-- List-comprehension version
halveEvens :: [Int] -> [Int]
halveEvens xs = [div x 2 | x <- xs, mod x 2 == 0]


-- This is for testing only. Do not try to understand this (yet).
halveEvensReference :: [Int] -> [Int]
halveEvensReference = (>>= \x -> guard (x `mod` 2 == 0) >>= \_ -> return $ x `div` 2)


-- -- Mutual test
prop_halveEvens :: [Int] -> Bool
prop_halveEvens xs = halveEvens xs == halveEvensReference xs


-- 2. inRange

-- List-comprehension version
inRange :: Int -> Int -> [Int] -> [Int]
inRange lo hi xs = [fst t | t <- zip xs [b >= lo | b <- [1..hi]], snd t]


-- 3. countPositives: sum up all the positive numbers in a list

-- List-comprehension version
countPositives :: [Int] -> Int
countPositives list = sum [1 | x <- list, mod x 2 == 0]

-- list comprehension returs lists, here a number is needed


-- 4. multDigits

-- List-comprehension version
multDigits :: String -> Int
multDigits str = product [x | c <- str, let x = ord c - 48, x >= 0 && x < 10]

countDigits :: String -> Int
countDigits str = sum [1 | c <- str, let x = ord c - 48, x >= 0 && x < 10]

prop_multDigits :: String -> Bool
prop_multDigits xs = multDigits xs <= 9 ^ (countDigits xs)


-- 5. capitalise

-- List-comprehension version
capitalise :: String -> String
capitalise (x : xs) = toUpper x : [toLower a | a <- xs]


-- 6. title

lowercase :: String -> String
lowercase xs = [toLower x | x <- xs]

-- List-comprehension version
title :: [String] -> [String]
title (w:ws) = capitalise w : [titlelise s | s <- ws]

titlelise :: String -> String
titlelise str
    | length str < 4 = lowercase str
    | otherwise = capitalise str

-- 7. signs

sign :: Int -> Char
sign i
    | i == 0 = '0'
    | i > 0 && i < 10 = '+'
    | i < 0 && i > -10 = '-'
    | otherwise = error "Argument out of range."

maybeSign :: Int -> Maybe Char
maybeSign i
    | i >= -9 && i <= 9 = Just (sign i)
    | otherwise = Nothing
signs :: [Int] -> String
signs xs = [sign x | x <- xs, maybeSign x /= Nothing ]


-- 8. score

score :: Char -> Int
score x  = sum $ flag <$> [isUpper x, isVowel x, isLetter x]
  where 
    flag :: Bool -> Int
    flag True  = 1
    flag False = 0
    isVowel :: Char -> Bool
    isVowel c = elem (toLower c) "aeiou"

totalScore :: String -> Int
totalScore xs = product [score x | x <- xs, (score x) > 0]

prop_totalScore_pos :: String -> Bool
prop_totalScore_pos xs = totalScore xs > 0

-- Tutorial Activity
-- 10. pennypincher

-- List-comprehension version.
pennypincher :: [Int] -> Int
pennypincher prices = sum [x | x <- prices, x * 9 <= 199000]

-- -- And the test itself
prop_pennypincher :: [Int] -> Bool
prop_pennypincher xs = sum [x | x <- xs, x > 0] >= pennypincher xs

-- Optional Material

-- 11. crosswordFind

-- List-comprehension version
crosswordFind :: Char -> Int -> Int -> [String] -> [String]
crosswordFind letter pos len words = [w | w <- words, w !! pos == letter, length w == len]


-- 12. search

-- List-comprehension version

search :: String -> Char -> [Int]
search str goal = undefined

-- Depending on the property you want to test, you might want to change the type signature
prop_search :: String -> Char -> Bool
prop_search str goal = undefined


-- 13. contains

contains :: String -> String -> Bool
contains str substr = undefined

-- Depending on the property you want to test, you might want to change the type signature
prop_contains :: String -> String -> Bool
prop_contains str1 str2 = undefined
