scores = [
    24.0, 28.0, 27.75, 27, 24.25, 23.5, 26.25, 24, 25, 30, 23.25, 26.25, 21.5,
    26, 28, 24.5, 22.5, 28.25, 21.25, 19.75
]
n = len(scores)
mean = sum(scores) / n
variance = sum(map(lambda x: (x-mean)**2, scores))/(n - 1)
print(mean, variance)