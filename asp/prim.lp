% Carter Crews
% ASP Prim Implementation
% 2017

%%%%% FACTS %%%%%
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

%%%%% RULES %%%%%
% Create trees from routes
tree(A,B,W) ; not tree(A,B,W) :- edge(A,B,W).
:- node(A), not visited(A).
:- tree(A,B,W), B=start.
:- tree(A,B,W), tree(B,A,W), A!=B.

% Evals to true when a node is in the tree.
visited(start) :- node(start).
visited(B) :- visited(A), tree(A,B,W).

% Graph is undirected
edge(A,B,W) :- edge(B,A,W).

% Find the solution by minimizing weight of edges. Display result.
#minimize {W,A,B : tree(A,B,W)}.
#show tree/3.