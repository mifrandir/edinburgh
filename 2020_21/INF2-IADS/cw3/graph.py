import math
import random
from queue import PriorityQueue


def euclid(p, q):
    x = p[0] - q[0]
    y = p[1] - q[1]
    return math.sqrt(x * x + y * y)


class Graph:

    # Complete as described in the specification, taking care of two cases:
    # the -1 case, where we read points in the Euclidean plane, and
    # the n>0 case, where we read a general graph in a different format.
    # self.perm, self.dists, self.n are the key variables to be set up.
    def __init__(self, n, filename):
        if n >= 0:
            self.n = n
            self.dists = self.read_general(filename)
        else:
            self.dists = self.read_euclidean(filename)
            self.n = len(self.dists)
        self.perm = list(range(self.n))

    def read_general(self, filename):
        ds = [[0 for _ in range(self.n)] for _ in range(self.n)]
        with open(filename, "r") as f:
            for l in f:
                i, j, d = list(map(int, l.split()))
                ds[i][j] = ds[j][i] = d
        return ds

    def read_euclidean(self, filename):
        ps = []
        with open(filename, "r") as f:
            for l in f:
                x, y = list(map(int, l.strip().split()))
                ps.append((x, y))
        ds = []
        for p in ps:
            ds.append([])
            for q in ps:
                ds[-1].append(euclid(p, q))
        return ds

    # Complete as described in the spec, to calculate the cost of the
    # current tour (as represented by self.perm).
    def tourValue(self):
        return sum(
            map(lambda x: self.dists[x[0]][x[1]],
                zip(self.perm, self.perm[1:] + self.perm[:1])))

    # Attempt the swap of cities i and i+1 in self.perm and commit
    # commit to the swap if it improves the cost of the tour.
    # Return True/False depending on success.
    def trySwap(self, i):
        n = self.n
        # we only need to look at two edges in either case
        a = self.perm[(i - 1 + n) % n]
        b = self.perm[i]
        c = self.perm[(i + 1) % n]
        d = self.perm[(i + 2) % n]
        current_value = self.dists[a][b] + self.dists[c][d]
        swap_value = self.dists[a][c] + self.dists[b][d]
        if swap_value < current_value:
            self.perm[i], self.perm[(i + 1) % n] = c, b
            return True
        return False

    # Consider the effect of reversiing the segment between
    # self.perm[i] and self.perm[j], and commit to the reversal
    # if it improves the tour value.
    # Return True/False depending on success.
    def tryReverse(self, i, j):
        if j > i:
            # which part of the tour we reverse is irrelevant
            # since edges have the same value in both directions
            i, j = j, i
        # again, only two edges are actually changing
        n = self.n
        a = self.perm[(i - 1 + n) % n]
        b = self.perm[i]
        c = self.perm[j]
        d = self.perm[(j + 1) % n]
        current_value = self.dists[a][b] + self.dists[c][d]
        swap_value = self.dists[a][c] + self.dists[b][d]
        if swap_value < current_value:
            self.perm[i:j + 1].reverse()
            return True
        return False

    def swapHeuristic(self, k):
        better = True
        count = 0
        while better and (count < k or k == -1):
            better = False
            count += 1
            for i in range(self.n):
                if self.trySwap(i):
                    better = True

    def TwoOptHeuristic(self, k):
        better = True
        count = 0
        while better and (count < k or k == -1):
            better = False
            count += 1
            for j in range(self.n - 1):
                for i in range(j):
                    if self.tryReverse(i, j):
                        better = True

    # Implement the Greedy heuristic which builds a tour starting
    # from node 0, taking the closest (unused) node as 'next'
    # each time.
    def Greedy(self):
        left = set(range(1, self.n))
        self.perm[0] = 0
        for i in range(self.n - 1):
            a = self.perm[i]
            b = min(left, key=lambda x, a=a: self.dists[a][x])
            self.perm[(i + 1) % self.n] = b
            left.remove(b)

    def DepthLimitedAstar(self, max_depth):
        def get_out_val(i, left, init):
            out_val = init
            for j in left:
                if i == j:
                    continue
                out_val = min(out_val, self.dists[i][j])
            return out_val

        def evaluate(path):
            left = set(range(self.n))
            # values of the fixed edges
            v = sum(map(lambda x: self.dists[x[0]][x[1]], zip(path, path[1:])))
            left.difference_update(set(path))
            for i in left:
                v += get_out_val(i, left, self.dists[i][path[0]])
            if len(path) < self.n:
                v += get_out_val(path[-1], left, math.inf)
            return v

        def astar_with_depth(depth, path, left):
            q = PriorityQueue()
            q.put((evaluate(path), path, left))
            while not q.empty():
                _, p, l = q.get()
                if len(p) == len(path) + depth:
                    return p, l
                for i in l:
                    new_p = list(p)
                    new_l = set(l)
                    new_p.append(i)
                    new_l.remove(i)
                    q.put((evaluate(new_p), new_p, new_l))
            return None

        path = [0]
        left = set(range(1, self.n))
        while len(path) != self.n:
            path, left = astar_with_depth(min(max_depth, self.n - len(path)),
                                          path, left)
        self.perm = path
