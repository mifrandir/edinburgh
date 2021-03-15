import graph
g = graph.Graph(12, "twelvenodes")
print(g.tourValue())
g.swapHeuristic(12)
print(g.tourValue())
g.TwoOptHeuristic(12)
print(g.tourValue())
