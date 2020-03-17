from util import primes_to


def find_n(p):
    n = 1
    s = 1
    while s % p != 0:
        s = 10 * s + 1
        n += 1
    return n


def mod_seq(p, n):
    r = []
    s = 1
    for _ in range(n):
        r.append(s)
        s = (10 * s + 1) % p
    return r


if __name__ == "__main__":
    for p in primes_to(100):
        if p == 2 or p == 5:
            continue
        else:
            n = find_n(p)
            seq = mod_seq(p, n)
            print(f"p={p}, n={n}, seq={seq}")
