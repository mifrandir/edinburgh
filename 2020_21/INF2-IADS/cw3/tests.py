import math
import graph

g = graph.Graph(12, "twelvenodes")
print(g.tourValue(), g.perm)
g.swapHeuristic(12)
print(g.tourValue(), g.perm)
g.TwoOptHeuristic(12)
print(g.tourValue(), g.perm)

g = graph.Graph(12, "twelvenodes")
print(g.tourValue(), g.perm)
g.Greedy()
print(g.tourValue(), g.perm)

g = graph.Graph(-1, "cities50")
print(g.tourValue(), g.perm)
g.DepthLimitedAstar(10)
print(g.tourValue(), g.perm)