import math


def shuffle(xs):
    new = []
    p = math.ceil(len(xs) / 2)
    for x, y in zip(xs[:p], xs[p:]):
        new.append(x)
        new.append(y)
    return new


def check(xs):
    c = 0
    for i, (x, y) in enumerate(zip(xs, xs[1:])):
        if x == i:
            continue
        c += 1
        if c > 1:
            return False
    return c == 1


deck = list(range(16))
for i in range(4):
    deck = shuffle(deck)
    print([x+1 for x in deck])
    if check(deck):
        break
