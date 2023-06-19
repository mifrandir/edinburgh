-- Informatics 1 - Introduction to Computation
-- Functional Programming Tutorial 3
--
-- Week 3(30-04 Oct.)
module Tutorial3 where

import Data.Char
import Data.List
import Test.QuickCheck

import Data.Function
import Data.Maybe


-- 1.

halveEvensRec :: [Int] -> [Int]
halveEvensRec []     = []
halveEvensRec (x:xs)
      | mod x 2 == 0 = div x 2 : r
      | otherwise    = r
      where r = halveEvens xs

halveEvens :: [Int] -> [Int]
halveEvens xs = [x `div` 2 | x <- xs, x `mod` 2 == 0]

prop_halveEvens :: [Int] -> Bool
prop_halveEvens xs = halveEvensRec xs == halveEvens xs


-- 2.

inRangeRec :: Int -> Int -> [Int] -> [Int]
-- inRangeRec a b xs | a > b = inRangeRec b a xs
inRangeRec _ _ []         = []
inRangeRec a b (x:xs)
      | x >= a && x <= b = x : rest
      | otherwise        = rest
      where rest = inRangeRec a b xs

inRange :: Int -> Int -> [Int] -> [Int]
inRange lo hi xs = [x | x <- xs, lo <= x, x <= hi]

prop_inRange :: Int -> Int -> [Int] -> Bool
prop_inRange lo hi xs = inRangeRec lo hi xs == inRange lo hi xs


-- 3.

countPositivesRec :: [Int] -> Int
countPositivesRec [] = 0
countPositivesRec (x:xs)
      | x > 0     = rest + 1
      | otherwise = rest
      where rest = countPositivesRec xs

countPositives :: [Int] -> Int
countPositives list = length [x | x <- list, x > 0]

prop_countPositives :: [Int] -> Bool
prop_countPositives l = countPositivesRec l == countPositives l


-- 4.

multDigitsRec :: String -> Int
multDigitsRec [] = 1
multDigitsRec (x:xs)
      | isDigit x = digitToInt x * r
      | otherwise = r
      where r = multDigitsRec xs

multDigits :: String -> Int
multDigits str = product [digitToInt ch | ch <- str, isDigit ch]

prop_multDigits :: String -> Bool
prop_multDigits xs = multDigitsRec xs == multDigits xs


-- These are some helper functions for makeKey and makeKey itself.
-- Exercises continue below.

rotate :: Int -> [Char] -> [Char]
rotate k list 
      | 0 <= k && k <= length list = drop k list ++ take k list
      | otherwise = error "Argument to rotate too large or too small"

--  prop_rotate rotates a list of lenght l first an arbitrary number m times,
--  and then rotates it l-m times; together (m + l - m = l) it rotates it all
--  the way round, back to the original list
--
--  to avoid errors with 'rotate', m should be between 0 and l; to get m
--  from a random number k we use k `mod` l (but then l can't be 0,
--  since you can't divide by 0)
prop_rotate :: Int -> String -> Bool
prop_rotate k str = rotate (l - m) (rotate m str) == str
                        where l = length str
                              m = if l == 0 then 0 else k `mod` l

alphabet = ['A'..'Z']

makeKey :: Int -> [(Char, Char)]
makeKey k = zip alphabet (rotate k alphabet)

-- Ceasar Cipher Exercises
-- =======================


-- 5.

lookUp :: Char -> [(Char, Char)] -> Char
lookUp c abs = f [b | (a,b) <- abs, a == c]
      where 
      f [] = c 
      f (x:xs) = x


lookUpRec :: Char -> [(Char, Char)] -> Char
lookUpRec c [] = c
lookUpRec c ((a,b):abs)
      | c == a = b
      | otherwise = lookUpRec c abs 


prop_lookUp :: Char -> [(Char, Char)] -> Bool
prop_lookUp c k = lookUp c k == lookUpRec c k


-- 6.

encipher :: Int -> Char -> Char
encipher k ch = lookUpRec ch (makeKey k)


-- 7.

normalize :: String -> String
normalize "" = ""
normalize (c:cs)
      | isNormal c  = toUpper c : r
      | otherwise = r
      where r = normalize cs


isNormal :: Char -> Bool
isNormal c = isDigit c || isLetter c

encipherStr :: Int -> String -> String
encipherStr k str = [encipher k c | c <- normalize str]


-- 8.

reverseKey :: [(Char, Char)] -> [(Char, Char)]
reverseKey key = [(b,a) | (a,b) <- key]

reverseKeyRec :: [(Char, Char)] -> [(Char, Char)]
reverseKeyRec [] = []
reverseKeyRec ((a,b):abs) = (b,a) : reverseKeyRec abs 

prop_reverseKey :: [(Char, Char)] -> Bool
prop_reverseKey key = reverseKey key == reverseKeyRec key


-- 9.

decipher :: Int -> Char -> Char
decipher k c = lookUpRec c (reverseKeyRec (makeKey k))

decipherStr :: Int -> String -> String
decipherStr k str = [decipher k c | c <- normalize str]

-- Optional Material
-- =================


-- 10.

contains :: String -> String -> Bool
contains _ [] = True
contains [] _ = False
contains (c:cs) sub = take (length sub) (c:cs) == sub || contains cs sub


-- 11.

candidates :: String -> [(Int, String)]
candidates str = [(k,s) | (k, s) <- [(i,decipherStr i str) | i <- [0..25]], contains s "THE" || contains s "AND"]


-- 12.

splitEachFive :: String -> [String]
splitEachFive [] = []
splitEachFive str
      | m /= 0 = splitEachFive (str ++ replicate (5 - m) 'X') 
      | otherwise = take 5 str : splitEachFive (drop 5 str)
      where m = mod (length str) 5

prop_transpose :: String -> Bool
prop_transpose str = splitEachFive str == transpose (transpose (splitEachFive str))

-- 13.

join :: [String] -> String
join [] = []
join (x:xs) = x ++ join xs

prop_join :: [String] -> Bool
prop_join ss = concat ss == join ss

encrypt :: Int -> String -> String
encrypt k str = join (transpose (splitEachFive (encipherStr k str)))


-- 14.
decrypt :: Int -> String -> String
decrypt k str
      | mod l 5 /= 0 = error "You can't decrypt something that cannot be split into multiples of five." 
      | div l 5 == 5            = error "You can't decrypt a string that is shorter than five."
      | otherwise               = decipherStr k (join (transpose (split str)))
      where
      l = length str
      x = div l 5
      split :: String -> [String]
      split [] = []
      split s = take x s : split (drop x s)

prop_decrypt :: String -> Bool
prop_decrypt str = and [str == decrypt i (encrypt i str) | i <- [1..25]]