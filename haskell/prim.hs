{- |
Module      :  prim.hs
Description :  Computes a MST from a graph.
Copyright   :  (c) Carter Crews, 2017
License     :  <license>
      
Maintainer  :  carccrew@ut.utm.edu
Stability   :  stable
Portability :  POSIX
      
This file contains functions necessary to compute a Minimum Spanning Tree using
Prim's algorithm. The prim function takes a graph as a list of 3-tuples. Each
3-tuple contains information about an edge in the graph, such as what vertex is on
each end of the edge and the weight of the edge. The other top-level function in
this file is weight. It will calculate the total weight of a graph or tree
submitted in the form of a list of 3-tuples as with prim.
-}

import Data.List

-- The example graph from the assignment page is hard-coded in here.
exampleGraph = [('a','b',4),('a','h',10),('b','c',8),('b','h',11),('c','d',7),('c','f',4),('c','i',2),('d','e',9),('d','f',14),('e','f',10),('f','g',2),('g','h',1),('g','i',6),('h','i',7)]

-- Returns 1st element from a 3-tuple.
first :: (t2, t1, t) -> t2
first (a,_,_) = a

-- Returns 2nd element from a 3-tuple.
second :: (t1, t2, t) -> t2
second (_,b,_) = b

-- Returns 3rd element from a 3-tuple.
third :: (t1, t, t2) -> t2
third (_,_,c) = c

-- Creates list of 1st elements from a 3-tuple list.
firstL :: [(t2, t1, t)] -> [t2]
firstL [] = []
firstL ((a,_,_):list) = a:(firstL list)

-- Creates list of 2nd elements from a 3-tuple list.
secondL :: [(t1, t2, t)] -> [t2]
secondL [] = []
secondL ((_,b,_):list) = b:(secondL list)

-- Creates list of 3rd elements from a 3-tuple list.
thirdL :: [(t1, t, t2)] -> [t2]
thirdL [] = []
thirdL ((_,_,c):list) = c:(thirdL list)

-- Prints total weight of graph.
weight :: Num a => [(t1, t, a)] -> a
weight list = sum(thirdL list)

-- Checks if a vertex from vertex list is connected to a certain edge.
checkVert :: (Foldable t1, Eq a) => t1 a -> (a, a, t) -> Bool
checkVert vertices (a,b,_) = ((a `elem` vertices) && not (b `elem` vertices)) || (not (a `elem` vertices) && (b `elem` vertices))

-- Prints out list of all edges connected to a list of vertices.
connEdges :: Eq t1 => [t1] -> [(t1, t1, t)] -> [(t1, t1, t)]
connEdges vertices [] = []
connEdges [] ((a,b,w):list) = (a,b,w):connEdges [] list
connEdges vertices ((a,b,w):list) = if (checkVert vertices (a,b,w)) then ((a,b,w):connEdges vertices list) else (connEdges vertices list)

-- Compares the weight of two edges.
compEdges :: Ord b => (a, a, b) -> (a, a, b) -> Ordering
compEdges a b = compare (third a) (third b)

-- Prints an edge from a list with the smallest weight.
minimalEdge :: Ord b => [(a, a, b)] -> [(a, a, b)]
minimalEdge [] = []
minimalEdge list = [minimumBy compEdges list]

-- Prints the smallest edge connected to a vertex in the list.
minimalConnEdge :: (Eq a, Ord b) => [a] -> [(a, a, b)] -> [(a, a, b)]
minimalConnEdge [] list = minimalEdge (connEdges [] list)
minimalConnEdge vertices list = minimalEdge (connEdges vertices list)

-- Prints out the first and second vertices as a list.
getVerts :: [(t1, t1, t)] -> [t1]
getVerts [(a,b,c)] = [first (a,b,c)] ++ [second (a,b,c)]

-- Check if the tree is finished. Return true if all of the vertices in the graph
-- are in the vertices list.
doneChk :: Ord a => [a] -> [(a, a, t)] -> Bool
doneChk vertices list = sort (nub (firstL list ++ secondL list)) == sort (nub (vertices))

-- Prim function - Creates a MST from a graph.
-- Gets the smallest edge in the list and then calls prim' on the rest.
prim :: (Ord b, Ord a) => [(a, a, b)] -> [(a, a, b)] 
prim list = minimalConnEdge [] list ++ prim' (getVerts (minimalConnEdge [] list)) list

-- Get the next edge to add to the list and check if we're done.
prim' :: (Ord a, Ord b) => [a] -> [(a, a, b)] -> [(a, a, b)]
prim' vertices list = minimalConnEdge vertices list ++ prim'' vertices list

-- Checks if the tree is complete. If so, return an empty list. If not,
-- call prim' with the new list of vertices.
prim'' :: (Ord a, Ord b) => [a] -> [(a, a, b)] -> [(a, a, b)]
prim'' vertices list = if(doneChk vertices list) then [] else(prim' (vertices ++ (getVerts (minimalConnEdge vertices list))) list)
