from util import primes_to, factorial
if __name__ == "__main__":
  ps = primes_to(100)
  last = ps[0]
  for p in ps[1:]:
    print(f"({p}-1)! = {factorial(p-1) % p} mod {p}")
    last = p