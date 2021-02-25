import numpy as np
from matplotlib import pyplot as plt

#-------------------------------------------------------
# Solution
#-------------------------------------------------------


def plot_columns(fig_type, x, y, plot_type):
    """
    Main function as described in task.
    """
    # we need to separate columns easily
    x = np.transpose(x)
    y = np.transpose(y)
    c = len(x)
    # handling fig_types differently
    if fig_type == "single":
        plot_rows_single(x, y, plot_type, c)
    elif fig_type == "subplots":
        plot_rows_subplots(x, y, plot_type, c)
    else:
        # undefined behaviour
        raise ValueError
    plt.legend()
    plt.show()


def get_layout(c):
    """
    Generates a plot arrangement based on the possible number of columns.
    """
    t = {
        1: (1, 1),
        2: (1, 2),
        3: (1, 3),
        4: (2, 2),
        5: (1, 5),
        6: (2, 3),
        7: (1, 7),  # this one hurts
        8: (2, 4),
        9: (3, 3),
        10: (2, 5)
    }
    return t[c]


def get_labels(c):
    """
    Generates labels that are either used for the subplots or for the
    curves within the plot.
    """
    return [f"Plot ${i+1}$" for i in range(c)]


def plot_rows_single(x, y, plot_type, c):
    """
    Handles the "single" case of plot_columns using transposed inputs
    """
    # only one subplot
    _, ax = plt.subplots()
    # pretending we are plotting to different axes
    axs = [ax for _ in range(c)]
    plot_rows_on_axs(x, y, plot_type, axs, get_labels(c), c)


def plot_rows_subplots(x, y, plot_type, c):
    """
    Handles the "subplots" case of plot_columns using transposed inputs
    """
    # creating subplots
    rows, cols = get_layout(c)
    fig, axs_ = plt.subplots(rows, cols)
    fig.tight_layout()
    # Flattening axs
    if cols == 1:  # one row, one column
        axs = [axs_]
    elif rows == 1:  # one row, more than one columns
        axs = axs_
    else:  # more than one row
        axs = []
        for r in axs_:
            axs.extend(r)
    # Setting titles as described in task description
    labels = get_labels(c)
    for i in range(c):
        axs[i].set_title(labels[i])
    # Passing empty labels because the plot title has already been set
    plot_rows_on_axs(x, y, plot_type, axs, ["" for _ in range(c)], c)


def plot_rows_on_axs(x, y, plot_type, axs, labels, c):
    """ Brings data, plot types, axes and labels together """
    # Plotting each row separately
    for i in range(c):
        # styling
        if plot_type[i] == "line":
            fmt = "-"
        else:
            fmt = "o"
        axs[i].plot(x[i], y[i], fmt, label=labels[i])


#-------------------------------------------------------
# Testing
#-------------------------------------------------------

import math


def test1():
    """ 1 plot, single, sin(x) """
    N = 100
    x = np.linspace(-2 * math.pi, 2 * math.pi, N)
    y = np.vectorize(math.sin)(x)
    plot_columns("subplots", np.reshape(x, (N, 1)), np.reshape(y, (N, 1)),
                 ["line"])


def test2():
    """ 2 plots, single, uniformly random """
    N = 100
    x = np.random.uniform(size=2 * N)
    y = np.random.uniform(size=2 * N)
    plot_columns("single", np.reshape(x, (N, 2)), np.reshape(y, (N, 2)),
                 ["scatter", "scatter"])


def test3():
    """ 9 plots, 3x3, sqrt in [0,1000] """
    N = 100
    c = 9
    x = np.linspace(0, 1000, c * N)
    y = np.vectorize(math.sqrt)(x)
    plot_columns("subplots", np.reshape(x, (N, c)), np.reshape(y, (N, c)),
                 ["line"] * (c - 3) + ["scatter"] * 3)


if __name__ == "__main__":
    test1()
    # test2()
    # test3()