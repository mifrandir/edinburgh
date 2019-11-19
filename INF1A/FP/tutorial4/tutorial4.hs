-- Informatics 1 - Introduction to Computation
-- Functional Programming Tutorial 4
--
-- Week 4(07-11 Oct.)

module Tutoria4 where

import Data.List (nub)
import Data.Char
import Test.QuickCheck
import Network.HTTP (simpleHTTP,getRequest,getResponseBody)

-- <type decls>

type Link = String
type Name = String
type Email = String
type HTML = String
type URL = String

-- </type decls>
-- <sample data>

testURL     = "http://www.inf.ed.ac.uk/teaching/courses/inf1/A/testpage.html"

testHTML :: String
testHTML =    "<html>"
           ++ "<head>"
           ++ "<title>FP: Tutorial 4</title>"
           ++ "</head>"
           ++ "<body>"
           ++ "<h1>A Boring test page</h1>"
           ++ "<h2>for tutorial 4</h2>"
           ++ "<a href=\"http://www.inf.ed.ac.uk/teaching/courses/inf1/A/testpage.html\">FP Website</a><br>"
           ++ "<b>Lecturer:</b> <a href=\"mailto:wadler@inf.ed.ac.uk\">Philip Wadler</a><br>"
           ++ "<b>TA:</b> <a href=\"mailto:irene.vp@ed.ac.uk\">Irene Vlassi</a>"
           ++ "</body>"
           ++ "</html>"

testLinks :: [Link]
testLinks = [ "http://www.inf.ed.ac.uk/teaching/courses/inf1/A/testpage.html\">FP Website</a><br><b>Lecturer:</b> "
            , "mailto:wadler@inf.ed.ac.uk\">Philip Wadler</a><br><b>TA:</b> "
            , "mailto:irene.vp@ed.ac.uk\">Irene Vlassi</a></body></html>" ]


testAddrBook :: [(Name,Email)]
testAddrBook = [ ("Philip Wadler","wadler@inf.ed.ac.uk")
               , ("Irene Vlassi","irene.vp@ed.ac.uk")]

-- </sample data>
-- <system interaction>

getURL :: String -> IO String
getURL url = simpleHTTP (getRequest url) >>= getResponseBody

emailsFromURL :: URL -> IO ()
emailsFromURL url =
  do html <- getURL url
     let emails = (emailsFromHTML html)
     putStr (ppAddrBook emails)

emailsByNameFromURL :: URL -> Name -> IO ()
emailsByNameFromURL url name =
  do html <- getURL url
     let emails = (emailsByNameFromHTML html name)
     putStr (ppAddrBook emails)

-- </system interaction>
-- <exercises>

-- 1.
sameString :: String -> String -> Bool
sameString str1 str2 = not (f str1 str2)
  where f [] [] = False
        f _ []  = True
        f [] _  = True
        f (x:xs) (y:ys) = (toLower x /= toLower y) || f xs ys


-- 2.
prefix :: String -> String -> Bool
prefix p s = sameString p (take (length p) s)

prop_prefix_pos :: String -> Int -> Bool
prop_prefix_pos str n =  prefix substr (map toLower str) &&
                         prefix substr (map toUpper str)
                           where
                             substr  =  take n str

prop_prefix_neg :: String -> Int -> Bool
prop_prefix_neg str n = sameString str substr || (not $ prefix str substr)
                          where substr = take n str
        
        
-- 3.
contains :: String -> String -> Bool
contains _ [] = True
contains [] _ = False
contains str sub = prefix sub str || contains (drop 1 str) sub

prop_contains :: String -> Int -> Int -> Bool
prop_contains str n m
  | n + m > length str = prop_contains str n ((length str) - n)
  | otherwise          = contains (map toLower str) substr && contains (map toUpper str) substr 
  where
  substr = take (max n m) (drop (min n m) str)

-- 4.
takeUntil :: String -> String -> String
takeUntil _ [] = []
takeUntil d (x:xs)
  | prefix d (x:xs) = []
  | otherwise       = x : takeUntil d xs

dropUntil :: String -> String -> String
dropUntil _ [] = []
dropUntil d (x:xs)
  | prefix d (x:xs) = x:xs
  | otherwise       = dropUntil d xs


-- 5.
split :: String -> String -> [String]
split a [] = []
split delim str 
  | prefix delim str  = split_after (drop (length delim) str)
  | otherwise         = takeUntil delim str : split delim (dropUntil delim str)
  where 
  split_after :: String -> [String]
  split_after []  = [""]
  split_after str = split delim str

reconstruct :: String -> [String] -> String
reconstruct _ []      = []
reconstruct _ (x:[])  = x
reconstruct d (x:xs)   = (add_delim x) ++ reconstruct d xs
  where add_delim str = str ++ d

prop_split :: Char -> String -> String -> Bool
prop_split c sep str = reconstruct sep' (split sep' str) `sameString` str
  where sep' = c : sep

-- 6.
linksFromHTML :: HTML -> [Link]
linksFromHTML html = split delim (dropUntil delim html)
    where delim = "<a href=\""

testLinksFromHTML :: Bool
testLinksFromHTML  =  linksFromHTML testHTML == testLinks


-- 7.
takeEmails :: [Link] -> [Link]
takeEmails [] = []
takeEmails (x:xs)
    | prefix "mailto:" x = x : takeEmails xs
    | otherwise = takeEmails xs


-- 8.
link2pair :: Link -> (Name, Email)
link2pair link
    | not (prefix "mailto:" link) = error "Link does not start with \"mailto:\""
    | otherwise = (name, email)
      where
      name = takeUntil "<" (drop 1 (dropUntil ">" link))
      email = drop 7 (takeUntil "\"" link)




-- 9.
emailsFromHTML :: HTML -> [(Name,Email)]
emailsFromHTML html = nub [link2pair link | link <- takeEmails (linksFromHTML html)]

testEmailsFromHTML :: Bool
testEmailsFromHTML  =  emailsFromHTML testHTML == testAddrBook


-- 10.
findEmail :: Name -> [(Name, Email)] -> [(Name, Email)]
findEmail _ [] = []
findEmail name (x:xs)
        | contains (fst x) name = x : findEmail name xs
        | otherwise       = findEmail name xs


-- 11.
emailsByNameFromHTML :: HTML -> Name -> [(Name,Email)]
emailsByNameFromHTML html name = findEmail name (emailsFromHTML html)

-- Optional Material

-- 13.
hasInitials :: String -> Name -> Bool
hasInitials = undefined

-- 14.
emailsByMatchFromHTML :: (Name -> Bool) -> HTML -> [(Name, Email)]
emailsByMatchFromHTML = undefined

emailsByInitialsFromHTML :: String -> HTML -> [(Name, Email)]
emailsByInitialsFromHTML = undefined

-- 15.

-- If your criteria use parameters (like hasInitials), change the type signature.
myCriteria :: Name -> Bool
myCriteria = undefined

emailsByMyCriteriaFromHTML :: HTML -> [(Name, Email)]
emailsByMyCriteriaFromHTML = undefined

-- 16.
ppAddrBook :: [(Name, Email)] -> String
ppAddrBook addr = unlines [ name ++ ": " ++ email | (name,email) <- addr ]
