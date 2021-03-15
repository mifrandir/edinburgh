import math

from pandas.core.frame import DataFrame
import graph
import os.path
import random
import time
import matplotlib.pyplot as plt
import pandas as pd


class TestGraph:
    def __init__(self, n):
        self.euclidean = False
        self.vertices = [None] * n
        self.n = n
        self.dists = [[math.inf for _ in range(n)] for _ in range(n)]

    def generate_general(self):
        random.seed(time.time_ns())
        for i in range(self.n):
            self.dists[i][i] = 0
            for j in range(i):
                r = random.randint(0, 10**6)
                self.dists[i][j] = r
                self.dists[j][i] = r
        self.shuffle()

    def generate_metric(self):
        self.generate_general()  # generate random values
        stable = False
        while not stable:
            stable = True
            for i in range(1, self.n):
                for j in range(i):
                    lo = math.inf
                    for k in range(self.n):
                        if k == j or k == i:
                            continue
                        d = self.dists[i][k] + self.dists[k][j]
                        lo = min(lo, d)
                    if self.dists[i][j] > lo:
                        r = random.randint(0, lo)
                        self.dists[i][j] = r
                        stable = False

    def generate_euclidean(self, x=1e+06, y=1e+06):
        self.euclidean = True
        self.vertices = []
        for _ in range(self.n):
            self.vertices.append((random.randint(0, x), random.randint(0, y)))
        for i in range(self.n):
            for j in range(i):
                self.dists[i][j] = graph.euclid(self.vertices[i],
                                                self.vertices[j])
                self.dists[j][i] = self.dists[i][j]
        self.shuffle()

    def shuffle(self):
        perm = list(range(self.n))
        random.shuffle(perm)
        newDists = [[math.inf for _ in range(self.n)] for _ in range(self.n)]
        newVerts = []
        for i in range(self.n):
            newVerts.append(self.vertices[perm[i]])
            for j in range(self.n):
                newDists[i][j] = self.dists[perm[i]][perm[j]]
        self.dists = newDists
        self.vertices = newVerts

    def write_to_file(self, filename):
        with open(filename, "w") as f:
            if self.euclidean:
                for x, y in self.vertices:
                    f.write(f"{x} {y}\n")
            else:
                for i in range(self.n):
                    for j in range(i + 1, self.n):
                        f.write(f"{i} {j} {self.dists[i][j]}\n")

    def write_subgraph_to_file(self, filename, n):
        if n > self.n:
            n = self.n
        with open(filename, "w") as f:
            if self.euclidean:
                for x, y in self.vertices[:n]:
                    f.write(f"{x} {y}\n")
            else:
                for i in range(n):
                    for j in range(i + 1, n):
                        f.write(f"{i} {j} {self.dists[i][j]}\n")


class TestResult:
    def __init__(self, alg, tour_value, runtime):
        self.alg = alg
        self.tour_value = tour_value
        self.runtime = runtime

    def __str__(self) -> str:
        return f"{self.label}: V={self.tour_value}, T={self.runtime}"

    def to_array(self):
        return [self.alg.label, self.alg.k, self.tour_value, self.runtime]


class Algorithm:
    def __init__(self, label, k, f):
        self.label = label
        self.k = k
        self.f = f

    def run(self, n, filename):
        print(f"Running {str(self)}...", end="", flush=True)
        g = graph.Graph(n, filename)
        t = time.time()
        self.f(g)
        t = time.time() - t
        print(f"DONE ({t:.2f}s)")
        return TestResult(self, g.tourValue(), t)

    def __str__(self):
        if self.k > 0:
            return f"{self.label}(k={self.k})"
        else:
            return self.label


def make_greedy():
    return Algorithm("Greedy", 0, graph.Graph.Greedy)


def make_2opt(k):
    return Algorithm("2-Opt", k, lambda g: graph.Graph.TwoOptHeuristic(g, k))


def make_swap(k):
    return Algorithm("Swap", k, lambda g: graph.Graph.swapHeuristic(g, k))


def make_dla(k):
    return Algorithm("DLA*", k, lambda g: graph.Graph.DepthLimitedAstar(g, k))


def get_algs(two_opt_k, depth_limit):
    return {
        "Swap": graph.Graph.swapHeuristic,
        # swapHeuristic will return None (i.e. false) so or will call 2-opt afterwards
        "2-Opt": lambda g: g.swapHeuristic() or g.TwoOptHeuristic(two_opt_k),
        "Greedy": graph.Graph.Greedy,
        "DLA*": lambda g: graph.Graph.DepthLimitedAstar(g, depth_limit),
    }


def test_algs(n, filename, algs):
    res = []
    for alg in algs:
        res.append(alg.run(n, filename))
    return res


def make_algs():
    algs = []
    algs.append(make_greedy())
    for k in range(1, 14):
        algs.append(make_swap(k))
    for k in range(1, 14):
        algs.append(make_2opt(k))
    for k in range(1, 14):
        algs.append(make_dla(k))
    return algs


def make_entry_f(graph_type, n):
    return lambda r: r.to_array() + [graph_type, n]


def generate_csv(csv_f):
    f = "test.txt"
    res = []
    ns = range(10, 40)
    g = TestGraph(ns[-1])
    g.generate_euclidean()
    for n in ns:
        g.write_subgraph_to_file(f, n)
        algs = make_algs()
        make_entry = make_entry_f("Euclidean", n)
        res.extend(list(map(make_entry, test_algs(-1, f, algs))))
    g = TestGraph(ns[-1])
    g.generate_metric()
    for n in ns:
        g.write_subgraph_to_file(f, n)
        algs = make_algs()
        make_entry = make_entry_f("Metric", n)
        res.extend(list(map(make_entry, test_algs(n, f, algs))))
    g = TestGraph(ns[-1])
    g.generate_general()
    for n in ns:
        g.write_subgraph_to_file(f, n)
        algs = make_algs()
        make_entry = make_entry_f("General", n)
        res.extend(list(map(make_entry, test_algs(n, f, algs))))
    df = DataFrame(res)
    df.columns = ["alg_type", "k", "tour_value", "runtime", "graph_type", "n"]
    print(df)
    df.to_csv(csv_f)


def plot_n_vs_tour(df, ax):
    ax.set_title("Absolute tour values versus graph size")
    ax.set_xlabel("Number of nodes $n$")
    ax.set_ylabel("Absolute tour value")
    plot_n_vs_param(df, ax, "tour_value", colour_f=colur_by_alg_and_graph)


def plot_n_vs_normal_tour(df, ax):
    ax.set_title("Normalised tour values versus graph size")
    ax.set_xlabel("Number of nodes $n$")
    ax.set_ylabel("Tour value relative to most expensive DLA*")
    plot_n_vs_param(df,
                    ax,
                    "normal_tour_value",
                    colour_f=colur_by_alg_and_graph)


def plot_n_vs_runtime(df, ax):
    ax.set_title("Runtime versus graph size")
    ax.set_xlabel("Number of nodes $n$")
    ax.set_ylabel("Runtime in seconds")
    plot_n_vs_param(df, ax, "runtime", colour_f=colur_by_alg_and_graph)


def plot_k_vs_tour(df, ax):
    ax.set_title("Absolute tour values versus parameter")
    ax.set_xlabel("Parameter $k$")
    ax.set_ylabel("Absolute tour value")
    plot_k_vs_param(df, ax, "tour_value", colour_f=colur_by_alg_and_graph)


def plot_k_vs_normal_tour(df, ax):
    ax.set_title("Normalised tour values versus parameter")
    ax.set_xlabel("Parameter $k$")
    ax.set_ylabel("Tour value relative to most expensive DLA*")
    plot_k_vs_param(df,
                    ax,
                    "normal_tour_value",
                    colour_f=colur_by_alg_and_graph)


def plot_k_vs_runtime(df, ax):
    ax.set_title("Runtime versus parameter")
    ax.set_xlabel("Parameter $k$")
    ax.set_ylabel("Runtime in seconds")
    plot_k_vs_param(df, ax, "runtime", colour_f=colur_by_alg_and_graph)


def colour_by_alg_type(alg_type, graph_type):
    return {
        "DLA*": "r",
        "Greedy": "#ffff00",
        "Swap": 'g',
        "2-Opt": 'b'
    }[alg_type]


def colur_by_alg_and_graph(alg_type, graph_type):
    return {
        ("DLA*", "General"): "#ff0000",
        ("DLA*", "Metric"): "#bb0000",
        ("DLA*", "Euclidean"): "#880000",
        ("Greedy", "General"): "#ffff00",
        ("Greedy", "Metric"): "#bbbb00",
        ("Greedy", "Euclidean"): "#888800",
        ("2-Opt", "General"): "#00ff00",
        ("2-Opt", "Metric"): "#00bb00",
        ("2-Opt", "Euclidean"): "#008800",
        ("Swap", "General"): "#0000ff",
        ("Swap", "Metric"): "#0000bb",
        ("Swap", "Euclidean"): "#000088",
    }[(alg_type, graph_type)]


def plot_n_vs_param(df, ax, param, colour_f=None):
    to_plot = set()
    for r in df.iloc:
        to_plot.add((r["alg_type"], r["k"], r["graph_type"]))
    for (alg_type, k, graph_type) in sorted(to_plot):
        plot_data = df[(df["alg_type"] == alg_type) & (df["k"] == k) &
                       (df["graph_type"] == graph_type)]
        if k > 0:
            label = f"{alg_type}(k={k}), {graph_type}"
        else:
            label = f"{alg_type}, {graph_type}"
        c = None
        if colour_f:
            c = colour_f(alg_type, graph_type)
        ax.plot(plot_data["n"], plot_data[param], label=label, c=c)
    ax.legend(prop={"size": 5})


def plot_k_vs_param(df, ax, param, colour_f=None):
    to_plot = set()
    for r in df.iloc:
        to_plot.add((r["alg_type"], r["n"], r["graph_type"]))
    for (alg_type, n, graph_type) in sorted(to_plot):
        plot_data = df[(df["alg_type"] == alg_type) & (df["n"] == n) &
                       (df["graph_type"] == graph_type)]
        label = f"{alg_type}($k$), {graph_type}, $n$={n}"
        c = None
        if colour_f:
            c = colour_f(alg_type, graph_type)
        ax.plot(plot_data["k"], plot_data[param], label=label, c=c)
    ax.legend(prop={"size": 6})


def normalise_tour_values(df):
    # separating graph types and sizes
    setups = set()
    for r in df.iloc:
        setups.add((r["graph_type"], r["n"]))
    # finding the biggest k (i.e. the most accurate DLA* run)
    k = max(df[df["alg_type"] == "DLA*"]["k"])
    ref_vals = dict()
    for (graph_type, n) in setups:
        # finding the tour value of DLA* for this graph with the k above
        ref_val = df[(df["graph_type"] == graph_type) & (df["n"] == n) &
                     (df["alg_type"] == "DLA*") &
                     (df["k"] == k)]["tour_value"].iloc[0]
        ref_vals[(graph_type, n)] = ref_val
    df["normal_tour_value"] = df.apply(
        lambda row: row["tour_value"] / ref_vals[
            (row["graph_type"], row["n"])],
        axis=1)
    return df


def plot_triple_for_k(name, df, k):
    fig, ax = plt.subplots(1, 4)
    df = normalise_tour_values(df)
    df = df[(df["k"] == k) | (df["alg_type"] == "Greedy")]
    plot_n_vs_normal_tour(df, ax[0])
    plot_n_vs_tour(df, ax[1])
    plot_n_vs_runtime(df, ax[2])
    plt.legend()
    plt.show()


def plot_quadruple_for_k(name, df, k):
    fig, ax = plt.subplots(2, 2, figsize=(15, 10))
    fig.set_dpi(200)
    df = normalise_tour_values(df)
    df = df[(df["k"] == k) | (df["alg_type"] == "Greedy")]
    plot_n_vs_tour(df, ax[0][0])
    plot_n_vs_normal_tour(df, ax[0][1])
    plot_n_vs_runtime(df, ax[1][0])
    plot_n_vs_runtime(df[df["alg_type"] != "DLA*"], ax[1][1])
    fig.savefig(f"{name}_k_{k}")


def plot_quadruple_for_n(name, df, n):
    fig, ax = plt.subplots(2, 2, figsize=(15, 10))
    fig.set_dpi(200)
    df = normalise_tour_values(df)
    df = df[(df["n"] == n) & (df["alg_type"] != "Greedy")]
    plot_k_vs_tour(df, ax[0][0])
    plot_k_vs_normal_tour(df, ax[0][1])
    plot_k_vs_runtime(df, ax[1][0])
    plot_k_vs_runtime(df[df["alg_type"] != "DLA*"], ax[1][1])
    fig.savefig(f"{name}_n_{n}.png")


if __name__ == "__main__":
    name = "data/test6"
    f = name + ".csv"
    if not os.path.exists(f):
        generate_csv(f)
    df = pd.read_csv(f)
    for n in range(10, 40):
        plot_quadruple_for_n(name, df, n)
    for k in range(1, 14):
        plot_quadruple_for_k(name, df, k)