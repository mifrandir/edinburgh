# Part 1

## Task 1.1

The predicates are

Connected(x, y) (true for all locations (x,y) that are adjacent, e.g. (B, C), (L, A))
IsAt(x, y) (true whenever an object in the world y is at the location x)
Defeated (true if the Minobot has been defeated)
Holding(x) (true if BoTheseus is holding the object x (e.g. Sword))
Holdable(x) (true if x is an object that BoTheseus could pick up (e.g. Sword))
HandsFull (true if BoTheseus is holding any object)

Since we are working with the closed world assumption, the initial 
state is fully determined by the conjunction

Init( Connected(A,B) & Connected(A,L) & Connected(B,A) & Connected(B,C) & Connected(B,E) & 
    Connected(C,B) & Connected(C,D) & Connected(D,C) & Connected(E,B) & Connected(E,F) & 
    Connected(F,E) & Connected(L,A) & IsAt(E, BoTheseus) & IsAt(L, Minobot) & IsAt(D, Sword)
)

The goal state is given by the predicates

Goal(Defeated(Minobot) & IsAt(E, BoTheseus))

## Task 1.2

The actions are

```
Action(Move(x, y),
    Precond: Connected(x,y) & IsAt(x, BoTheseus),
    Effect: !IsAt(x, BoTheseus) & IsAt(y, BoTheseus)
)

Action(PickUpAt(x, y),
    Precond: !HandsFull & Holdable(y) & isAt(x, BoTheseus) & isAt(x, y),
    Effect: HandsFull & Holding(y)
)

Action(SlayAt(x),
    Precond: isAt(x, BoTheseus) & isAt(x, Minobot) & Holding(Sword) & !Defeated,
    Effect: !isAt(x, Minobot) & Defeated
)
```

## Task 1.3

Starting state is

```
IsAt(A, BoTheseus) & IsAt(A, Sword) & IsAt(L, Minobot)
```

The goal state is

```
Defeated
```

### Step 1

The relevant action is `SlayAt(x)` for some `x` because it is the
only action that can possibly lead to `Defeated`. To avoid dead ends as described
in the task, we choose `x=L` because we know that Minobot can't move in the
current implementation. However, the algorithm may try all other locations first,
maybe even pathfinding for BoTheseus, only to find that the plan does not work.

The new goal state then is

```
Connected(A,B) & Connected(A,L) & Connected(B,A) & Connected(B,C) & Connected(B,E) & 
Connected(C,B) & Connected(C,D) & Connected(D,C) & Connected(E,B) & Connected(E,F) & 
Connected(F,E) & Connected(L,A) & isAt(L, BoTheseus) & isAt(L, Minobot) &
Holding(Sword) & !Defeated
```

### Step 2

Observe that `isAt(L, Minobot)` is already satisfied. The same goes for
`!Defeated`. Thus the relevant actions are those that lead 
to `isAt(L, BoTheseus)` or `Holding(Sword)`. I.e. `Move(x, L)`
and `PickUpAt(x, Sword)`. Again, we choose `Move(x, L)` for convenience
and there is only one assignment `x=A` that makes sense in the long run. 
This leads to the new goal state

```
isAt(A, BoTheseus) & Connected(A, L) & isAt(L, Minobot) & Holding(Sword) & !Defeated
```

### Step 3

The situation is essentially identical to Step 3 since `Connected(A, L)` is in the
initial state, i.e. the relevant actions
are those that lead to `isAt(A, BoTheseus)` or `Holding(Sword)`. In particular,
these are `Move(x, A)` and `PickUpAt(x, Sword)`. The only difference here
is that it makes more sense to try and satisfy `Holding(Sword)` because
BoTheseus is already in the chamber with the sword. The only action that 
achieves this is `PickUpAt(x, Sword)` with the assignment `x=A`. The new
goal state becomes

```
isAt(A, BoTheseus) & Connected(A, L) & isAt(L, Minobot) & !HandsFull & Holdable(Sword) & isAt(A, Sword)
```

### Step 4

The relevant part of the goal state is `isAt(A, BoTheseus)` since everything else is already satisfied
in the initial state. Thus the only relevant action is `Move(x, A)`. With some foresight, we choose
`x=B`. This leads to the goal state

```
isAt(B, BoTheseus) & Connected(B, A) & Connected(A, L) & isAt(L, Minobot) & !HandsFull & Holdable(Sword) & isAt(A, Sword)
```

All these conditions are met in the initial state so the algorithm terminates.

### The plan

The plan can be constructed by going backwards through the steps. We obtain

1. `Move(B, A)`
2. `PickUpAt(A, Sword)`
3. `Move(A, L)`
4. `SlayAt(L)`