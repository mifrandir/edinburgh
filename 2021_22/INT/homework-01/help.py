from operator import mod
from typing import List


def get_primes(n: int) -> List[int]:
    primes = []
    for i in range(2, n + 1):
        for j in range(2, int(i ** 0.5) + 1):
            if i % j == 0:
                break
        else:
            primes.append(i)
    return primes


def get_primitive_roots(p):
    primitive_roots = set()
    for n in range(0, p):
        powers_mod_p = set()
        for i in range(1, p+1):
            powers_mod_p.add(mod(n**i, p))
        if len(powers_mod_p) == p-1:
            primitive_roots.add(n)
    return primitive_roots


primes_by_num_primitive_roots = dict()
primes = get_primes(1000)
print(primes)
for p in get_primes(1000):
    if p == 2:
        continue
    primitive_roots = get_primitive_roots(p)
    k = len(primitive_roots)
    print(p, primitive_roots)
    if k in primes_by_num_primitive_roots.keys():
        print(primes_by_num_primitive_roots[k], p)
        break
    else:
        primes_by_num_primitive_roots[k] = p