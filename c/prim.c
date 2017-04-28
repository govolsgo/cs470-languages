#include "prim.h"

int getMinEdge(int vert, char edges[NUM_VERTICES][NUM_EDGES][2][VERT_NAME_LEN],
	       int edgeSize[NUM_VERTICES])
{
  int minEdgeLoc = -1;
  int minEdgeWeight = -1;
  int i;

  for(i = 0; i < edgeSize[vert]; i++)
    {
      if(atoi(edges[vert][i][1]) != -1)
	{
	  if(atoi(edges[vert][i][1]) < minEdgeWeight || minEdgeWeight == -1)
	    {
	      minEdgeLoc = i;
	      minEdgeWeight = atoi(edges[vert][i][1]);
	    }
	}
    }

  return minEdgeLoc;
}
