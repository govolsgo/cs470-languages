% Carter Crews
% ASP Prim Implementation
% 2017

% Vertices
vertex(a;b;c;d;e;f;g;h;i).

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