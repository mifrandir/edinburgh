from util import primes_to, is_prime

if __name__ == "__main__":
    for i in primes_to(100000):
        if is_prime(i*i+1):
            print(i, i*i+1)
