def a(n):
    s = 0
    for k in range(1, n+1):
        s += 1 / (n+k)
    return s

for i in range(10):
    print(f"i={i}, a_n={a(10**i)}")
