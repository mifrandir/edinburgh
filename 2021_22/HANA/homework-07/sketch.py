from matplotlib.colors import LinearSegmentedColormap
import matplotlib.pyplot as plt

max_n = 10
bounds = [1 / (max_n-j)**2 for j in range(max_n)]
plt.hlines(list(range(1, max_n))[::-1], bounds[:-1], bounds[1:])
yticks = list(range(max_n+1))[::5]
plt.yticks(yticks, yticks)
plt.xticks([0,1])
plt.title(f"$f(x)$ on $(1/n^2, 1]$ for $n={max_n}$")
plt.savefig('sketch.jpg', dpi=500)