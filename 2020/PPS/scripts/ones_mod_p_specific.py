from util import primes_to

def s(p):
    return sum([10**i for i in range(p-1)]) % p

for p in primes_to(100):
    m = s(p)
    if m != 0:
        print(f"p={p}, s(p)={m}")