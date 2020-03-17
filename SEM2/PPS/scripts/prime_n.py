from util import primes_to, is_prime
def check_n(ps):
  n = 1
  for p in ps:
    n *= p
  n += 1 
  print(n, is_prime(n))

if __name__ == "__main__":
  p = primes_to(100)
  for i in range(len(p)):
    check_n(p[:i])
