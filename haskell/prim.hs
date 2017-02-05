graph = [('a','b',4),('a','h',10),('b','c',8),('b','h',11),('c','d',7),('c','f',4),('c','i',2),('d','e',9),('d','f',14),('e','f',10),('f','g',2),('g','h',1),('g','i',6),('h','i',7)]

-- Creates list of 1st elements from a 3-tuple list.
first [] = []
first ((a,_,_):list) = a:(first list)

-- Creates list of 2nd elements from a 3-tuple list.
second [] = []
second ((_,b,_):list) = b:(second list)

-- Creates list of 3rd elements from a 3-tuple list.
third [] = []
third ((_,_,c):list) = c:(third list)


-- Prints total weight of graph.
weight (('a','b',c):list) = sum(third (('a','b',c):list))

-- Checks if vertex is connected to an edge.
checkVert :: Eq a => a -> (a, a, t) -> Bool
checkVert a (b,c,_) = (a == b) || (a == c)