-- Informatics 1 - Introduction to Computation
-- Functional Programming Tutorial 8
--
-- Week 8(04-08 Nov.)
-- Indexed data represented as a tree


module KeymapTree ( Keymap,
                    size, depth,
                    get, set, del,
                    select,
                    toList, fromList,
                    merge, filterLT, filterGT                  
                  )

where

-- Modules for testing

import Test.QuickCheck
import Control.Monad
import Data.List

-- The data type

data Keymap k a = Leaf
                | Node k a (Keymap k a) (Keymap k a)

-- A test tree

testTree :: Keymap Int Int
testTree = Node 2 20 (Node 1 10 Leaf Leaf)
                     (Node 3 30 Leaf 
                               (Node 4 40 Leaf Leaf ))

-- Exercise 6

size :: Ord k => Keymap k a -> Int
size Leaf = 0
size (Node _ _ left right) = 1 + size left + size right

depth :: Ord k => Keymap k a -> Int
depth Leaf = 0
depth (Node _ _ left right) = 1 + max (depth left) (depth right)

-- Exercise 7

toList :: Ord k => Keymap k a -> [(k,a)]
toList Leaf = []
toList (Node k v left right) = toList left ++ [(k,v)] ++ toList right

-- Exercise 8

set :: Ord k => k -> a -> Keymap k a -> Keymap k a
set key value = f
    where
      f Leaf = Node key value Leaf Leaf
      f (Node k v left right) | key == k  = Node k value left right
                              | key <= k  = Node k v (set key value left) right
                              | otherwise = Node k v left (set key value right)

-- Exercise 9

get :: Ord k => k -> Keymap k a -> Maybe a
get key = f
  where
    f Leaf = Nothing
    f (Node k v left right) | key == k = Just v
                            | key <= k = get key left
                            | key >= k = get key right

prop_set_get :: Int -> Int -> Bool
prop_set_get k v = get k (set k v testTree) == Just v

-- Exercise 10

fromList :: Ord k => [(k,a)] -> Keymap k a
fromList = foldr (\(k,v) m -> set k v m) Leaf


prop_toList_fromList :: [Int] -> [Int] -> Bool
prop_toList_fromList xs ys = sort (toList (fromList zs)) == sort zs
    where
      zs = zip (nub xs) ys

prop_toList_fromList_sorted :: [Int] -> [Int] -> Bool
prop_toList_fromList_sorted xs ys = toList (fromList zs) == sort zs
    where
      zs = zip (nub xs) ys

-- Optional Material -----------------------------------

-- Exercise 13

filterLT :: Ord k => k -> Keymap k a -> Keymap k a
filterLT key = f
  where
    f Leaf = Leaf
    f (Node k v left right)
      | k < key  = Leaf
      | k >= key = Node k v (filterLT key left) right

filterGT :: Ord k => k -> Keymap k a -> Keymap k a
filterGT key = f
  where
    f Leaf = Leaf
    f (Node k v left right)
      | k <= key = Node k v left (filterGT key right)
      | k > key  = Leaf

-- Exercise 14

merge :: Ord k => Keymap k a -> Keymap k a -> Keymap k a
merge t1 t2 = foldr (\(k,v) t -> set k v t) t1 (toList t2)

prop_merge :: (Ord k, Eq a) => Keymap k a -> Keymap k a -> Bool
prop_merge t1 t2 = and (map (\x -> elem x l) (toList t1 ++ toList t2))
  where l = toList (merge t1 t2)

-- Exercise 15

del :: Ord k => k -> Keymap k a -> Keymap k a
del key = f
  where
    f Leaf = Leaf
    f (Node k v left right)
      | key == k = merge (del key left) (del key right)
      | key < k  = Node k v (del key left) right
      | key > k  = Node k v left (del key right)

-- Exercise 16

select :: Ord k => (a -> Bool) -> Keymap k a -> Keymap k a
select p = f
  where
    f Leaf = Leaf
    f (Node k v left right)
      | p v = Node k v newLeft newRight
      | otherwise = merge newLeft newRight
      where
        newLeft  = select p left
        newRight = select p right

-- Instances for QuickCheck -----------------------------
instance (Ord k, Show k, Show a) => Show (Keymap k a) where
    show = show . toList

instance (Ord k, Arbitrary k, Arbitrary a) => Arbitrary (Keymap k a) where
    arbitrary = liftM fromList $ liftM2 zip (liftM nub arbitrary) arbitrary