def is_prime(n):
  if n == 2 or n == 3: return True
  if n < 2 or n%2 == 0: return False
  if n < 9: return True
  if n%3 == 0: return False
  r = int(n**0.5)
  f = 5
  while f <= r:
    if n%f == 0: return False
    if n%(f+2) == 0: return False
    f +=6
  return True    

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

if __name__ == "__main__":
  for i in primes_to(100000):
    if is_prime(i*i+1):
      print(i, i*i+1)