import math
import graph
import os.path
import random


class TestGraph:
    def __init__(self, n):
        self.euclidean = False
        self.vertices = []
        self.n = n
        self.dists = [[math.inf] * n] * n
        self.shortest_path = math.inf

    def generate_general(self):
        for i in range(1, self.n):
            for j in range(i):
                if i == j:
                    r = 0
                else:
                    r = random.randint(0, 10**6)
                self.dists[i][j] = self.dists[j][i] = r
        self.shuffle()

    def generate_metric(self):
        pass
    def generate_euclidean(self, x=1000, y=1000):
        self.euclidean = True
        self.vertices = []
        for _ in range(self.n):
            self.vertices.append((random.randint(0, x), random.randint(0, y)))
        for i in range(self.n):
            for j in range(self.n):
                self.dists[i][j] = graph.euclid(self.vertices[i],
                                                self.vertices[j])
        self.shuffle()

    def shuffle(self):
        perm = list(range(self.n))
        random.shuffle(perm)
        newDists = [[math.inf] * self.n] * self.n
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
                        f.write(f"{i} {i} {self.dists[i][j]}\n")


if __name__ == "__main__":
    g = TestGraph(12)
    g.generate_euclidean()
    g.write_to_file("test.txt")