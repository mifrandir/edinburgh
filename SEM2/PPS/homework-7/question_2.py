def primes_to(n):
  numbers = [i for i in range(2,n)]
  index = 0
  while index < len(numbers):
    p = numbers[index]
    q = p 
    while q <= n:
      q += p
      try:
        numbers.remove(q)
      except ValueError as _:
        pass
    index += 1
  return numbers

def factorial(n):
  f = 1
  while n > 1:
    f *= n
    n -= 1
  return f

if __name__ == "__main__":
  ps = primes_to(100)
  last = ps[0]
  for p in ps[1:]:
    print(f"({p}-1)! = {factorial(p-1) % p} mod {p}")
    last = p