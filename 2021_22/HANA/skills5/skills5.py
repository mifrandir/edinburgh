import matplotlib.pyplot as plt
from numpy import sin

def plot(xs, ns, f, title, filename):
    plt.clf()
    plt.title(title)
    for n in ns:
        plt.plot(xs, list(map(f(n), xs)), label=f"$n={n}$")
    plt.xlim(0, 1)
    plt.hlines(0.25, 0, 1.0, colors='gray', linestyles='dotted')
    plt.ylim(-0.01, 0.51)
    plt.vlines(0.9, -0.01, 0.51, colors='black', linestyles='dotted')
    plt.legend()
    plt.savefig(filename, dpi=300)

ns = [n * n for n in range(10)]
f = lambda n: lambda x: x**n / (1 + x**n)
plot([x / 100 for x in range(101)], ns, f, "$f_n$ on $E=[0,1)$", "skills5-pointwise.png")
plot([x / 100 for x in range(91)], ns, f, "$f_n$ on $E=[0,0.9)$", "skills5-uniform.png")