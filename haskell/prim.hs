import Data.List

graph = [('a','b',4),('a','h',10),('b','c',8),('b','h',11),('c','d',7),('c','f',4),('c','i',2),('d','e',9),('d','f',14),('e','f',10),('f','g',2),('g','h',1),('g','i',6),('h','i',7)]

-- Returns 1st element from a 3-tuple.
first (a,_,_) = a

-- Returns 2nd element from a 3-tuple.
second (_,b,_) = b

-- Returns 3rd element from a 3-tuple.
third (_,_,c) = c

-- Creates list of 1st elements from a 3-tuple list.
firstL [] = []
firstL ((a,_,_):list) = a:(firstL list)

-- Creates list of 2nd elements from a 3-tuple list.
secondL [] = []
secondL ((_,b,_):list) = b:(secondL list)

-- Creates list of 3rd elements from a 3-tuple list.
thirdL [] = []
thirdL ((_,_,c):list) = c:(thirdL list)

-- Prints total weight of graph.
weightTot (('a','b',w):list) = sum(thirdL (('a','b',w):list))

-- Checks if a vertex from vertex list is connected to a certain edge.
-- checkVert :: Eq a => [a] -> (a, a, t) -> Bool
checkVert vertices (a,b,_) = ((a `elem` vertices) && not (b `elem` vertices)) || (not (a `elem` vertices) && (b `elem` vertices))

-- Prints out list of all edges connected to a list of vertices.
connEdges vertices [] = []
connEdges vertices ((a,b,w):list) = if (checkVert vertices (a,b,w)) then ((a,b,w):connEdges vertices list) else (connEdges vertices list)

-- Compares the weight of two edges.
compEdges :: Ord b => (a, a, b) -> (a, a, b) -> Ordering
compEdges a b = compare (third a) (third b)

-- Prints an edge from a list with the smallest weight.
minimalEdge list = minimumBy compEdges list

-- Prints the smallest edge connected to a vertex in the list.
minimalConnEdge vertices list = minimalEdge (connEdges vertices list)

-- Prints out the first and second vertices as a list.
getVerts (a,b,c) = [first (a,b,c)] ++ [second (a,b,c)]

