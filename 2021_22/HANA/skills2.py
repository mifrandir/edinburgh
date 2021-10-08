import matplotlib.pyplot as plt
from numpy import sin

def plot(interval, sequence, title, filename):
    series = lambda x: sum(map(sequence, range(1, x+1)))
    abs_series = lambda x: sum([abs(sequence(k)) for k in range(1, x+1)])
    plt.clf()
    plt.title(title)
    plt.scatter(interval, list(map(series, interval)), label="sum")
    plt.scatter(interval, list(map(abs_series, interval)), label="absolute sum")
    plt.legend()
    plt.xticks(interval[::3])
    plt.savefig(filename)

interval = list(range(1,30))
plot(interval, lambda x: sin(2*x) / x, "Conditionally convergent series with $a_k=sin(2k)/k$", "skills2-conditionally-convergent.png")
plot(interval, lambda x: (-1)**x / x**1.1, "Absolutely convergent series wiht $a_k=(-1)^k/k^2$", "skills2-absolutely-convergent.png")