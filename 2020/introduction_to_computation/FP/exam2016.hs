import Data.Char

f :: [Int] -> [Int] -> Int
f xs ys = sum [ x | (x, y) <- zip xs ys, mod x y == 0 ]

test_f :: Bool
test_f =
    f [6, 9, 2, 7] [2, 3, 5, 1] == 22 &&
    f [6, 9, 2] [2, 3, 5, 1] == 15 &&
    f [1, 2, 3, 4, 5] [5, 4, 3, 2, 1] == 12 &&
    f [10, 20, 30, 40] [3, 4, 5, 6, 7] == 50

g :: [Int] -> [Int] -> Int
g [] _ = 0
g _ [] = 0
g (x:xs) (y:ys)
    | mod x y == 0 = x + r
    | otherwise = r
    where r = g xs ys

prop_gf :: [Int] -> [Int] -> Bool
prop_gf xs ys = g xs ys == f xs ys

p :: String -> Int
p str = newMax [ord c - 48 | c <- str, isDigit c]

newMax :: [Int] -> Int
newMax [] = 0
newMax xs = maximum xs

test_p :: Bool
test_p = test_2 p

test_2 :: (String -> Int) -> Bool
test_2 f =
    f "Inf1-FP" == 1 &&
    f "Functional" == 0 &&
    f "1+1=2" == 2 &&
    f "3.157/3 > 19" == 9

q :: String -> Int
q [] = 0
q (x:xs)
    | isDigit x = max (ord x - 48) r
    | otherwise = r
    where
        r = q xs

test_q :: Bool
test_q = test_2 q

prop_qp :: String -> Bool
prop_qp str = q str == p str 

r :: String -> Int
r = (foldr (\a b -> max (ord a - 48) b) 0) . (filter isDigit)

test_r :: Bool
test_r = test_2 r

prop_rq :: String -> Bool
prop_rq str = r str == q str

data Move = Go Int
          | Turn
          | Dance
          deriving (Eq, Show)
        
data Command = Nil
             | Command :#: Move
             deriving (Eq, Show)

type Position = Int
data Direction = L | R deriving (Show, Eq)
type State = (Position, Direction) 

state :: Move -> State -> State
state (Go x) (p, R) = (p+x, R)
state (Go x) (p, L) = (p-x, L)
state (Turn) (p, R) = (p, L)
state (Turn) (p, L) = (p, R)
state Dance x = x

test_state :: Bool
test_state =
    state (Go 3) (0, R) `eqState` (3, R) &&
    state (Go 3) (0, L) `eqState` (-3, L) &&
    state Turn (-2, L) `eqState` (-2, R) &&
    state Dance (4, R) `eqState` (4, R)

eqState :: State -> State -> Bool
eqState (x1, R) (x2, R) = x1 == x2
eqState (x1, L) (x2, L) = x1 == x2
eqState _ _ = False

trace :: Command -> State -> [State]
trace c s = reverse $ h (moveSeq c) [s]
    where
        h :: [Move] -> [State] -> [State]
        h [] xs = xs
        h (m:ms) (x:xs) = h ms (state m x : x : xs)

moveSeq :: Command -> [Move]
moveSeq Nil = []
moveSeq (c :#: m) = moveSeq c ++ [m]

test_trace :: Bool
test_trace =
    trace (Nil) (3,R) `eqStateList` [(3,R)] &&
    trace (Nil :#: Go 3 :#: Turn :#: Go 4) (0,L) `eqStateList` [(0,L),(-3,L),(-3,R),(1,R)] &&
    trace (Nil :#: Go 3 :#: Dance :#: Turn :#: Turn) (0,R) `eqStateList` [(0,R),(3,R),(3,R),(3,L),(3,R)]

eqStateList :: [State] -> [State] -> Bool
eqStateList [] [] = True
eqStateList (x:xs) (y:ys) = eqState x y && eqStateList xs ys
eqStateList _ _ = False

dancify :: Command -> Command
dancify c = h c (reverse $ trace c (0,R))
    where
        h :: Command -> [State] -> Command
        h Nil _ = Nil
        h (c :#: Dance) (x:xs) = h c xs :#: Dance
        h (c :#: Turn) (x:xs) = h c xs :#: Turn :#: Dance
        h (c :#: Go d) (x:xs)
            | containsPos x xs = h c xs :#: Go d :#: Dance
            | otherwise = h c xs :#: Go d

containsPos :: State -> [State] -> Bool
containsPos (x,_) xs = or $ map (\(y,_) -> x == y) xs

test_dancify :: Bool
test_dancify =
    dancify Nil == Nil &&
    dancify (Nil :#: Go 3 :#: Turn :#: Go 4) == Nil :#: Go 3 :#: Turn :#: Dance :#: Go 4 &&
    dancify (Nil :#: Go 3 :#: Dance :#: Turn :#: Turn) == Nil :#: Go 3 :#: Dance :#: Turn :#: Dance :#: Turn :#: Dance &&
    dancify (Nil :#: Go 3 :#: Turn :#: Go 2 :#: Go 1 :#: Turn :#: Go 4) == Nil :#: Go 3 :#: Turn :#: Dance :#: Go 2 :#: Go 1 :#: Dance :#: Turn :#: Dance :#: Go 4