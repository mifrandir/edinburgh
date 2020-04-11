def is_divisible(divisors, n):
    for d in divisors:
        if n % d == 0:
            return True
    return False

if __name__ == "__main__":
    divisors = [3,4,5]
    count = 0
    for n in range(1,201):
        if is_divisible(divisors, n):
            print(n)
            count += 1
    print(f"final count={count}")