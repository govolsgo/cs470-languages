% Carter Crews
% ASP Prim Implementation
% 2017

% Vertices
node(a;b;c;d;e;f;g;h;i).

% Edges
edge(a,b,4).
edge(a,h,10).
edge(b,c,8).
edge(b,h,11).
edge(c,d,7).
edge(c,f,4).
edge(c,i,2).
edge(d,e,9).
edge(d,f,14).
edge(e,f,10).
edge(f,g,2).
edge(g,h,1).
edge(g,i,6).
edge(h,i,7).

% Graph is undirected
edge(A,B,W) :- edge(B,A,W).

% Find connected edges
route(A,B) :- edge(A,B,_).
route(A,C) :- edge(A,B,_), edge(B,C,_), A!=C.
route(A,C) :- route(A,B), edge(B,C,_), A!=C.

% Create trees from routes
tree(start,B,W) :- edge(start,B,W).
tree(A,B,W) : edge(A,B,W) :- route(B,start).

% Evals to true when a node is in the tree.
visited(start) :- node(start).
visited(B) :- visited(A), tree(A,B,W).

%#minimize {W,A,B : tree(A,B,W)}.
%#show visited/1.