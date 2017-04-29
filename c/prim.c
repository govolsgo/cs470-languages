#include "prim.h"

void addEdge(char vert1[VERT_NAME_LEN], char vert2[VERT_NAME_LEN],
	     char weight[VERT_NAME_LEN],
	     char vertexNames[NUM_VERTICES][VERT_NAME_LEN], int* vertexSize,
	     char edges[NUM_VERTICES][NUM_EDGES][2][VERT_NAME_LEN],
	     int edgeSize[NUM_VERTICES])
{
  int vert1Loc = findVertIndex(vert1,*vertexSize,vertexNames);
  int vert2Loc = findVertIndex(vert2,*vertexSize,vertexNames);

  /* If a vertex we are adding isn't in the array yet, add it. */
  if(vert1Loc == -1)
    {
      strcpy(vertexNames[*vertexSize],vert1);
      vert1Loc = *vertexSize;
      (*vertexSize)++;
    }
  if(vert2Loc == -1)
    {
      strcpy(vertexNames[*vertexSize],vert2);
      vert2Loc = *vertexSize;
      (*vertexSize)++;
    }

  /* Add the edge to both vertices to ensure graph is undirected. */
  strcpy(edges[vert1Loc][edgeSize[vert1Loc]][0],vert2);
  strcpy(edges[vert1Loc][edgeSize[vert1Loc]][1],weight);
  edgeSize[vert1Loc]++;
  
  strcpy(edges[vert2Loc][edgeSize[vert2Loc]][0],vert1);
  strcpy(edges[vert2Loc][edgeSize[vert2Loc]][1],weight);
  edgeSize[vert2Loc]++;

  return;
}

int getMinEdge(int vert, char edges[NUM_VERTICES][NUM_EDGES][2][VERT_NAME_LEN],
	       int edgeSize[NUM_VERTICES])
{
  int minEdgeLoc = -1;
  int minEdgeWeight = -1;
  int i;

  for(i = 0; i < edgeSize[vert]; i++)
    {
      /* Make sure the location we're looking at actually contains an edge. */
      if(atoi(edges[vert][i][1]) != -1)
	{
	  /* If we've found a smaller weight, or if this is the first edge,
	     update tracking variables. */
	  if(atoi(edges[vert][i][1]) < minEdgeWeight || minEdgeWeight == -1)
	    {
	      minEdgeLoc = i;
	      minEdgeWeight = atoi(edges[vert][i][1]);
	    }
	}
    }

  return minEdgeLoc;
}

void preventCycles(char vertexNames[NUM_VERTICES][VERT_NAME_LEN],
		   int vertexSize,
		   char edges[NUM_VERTICES][NUM_EDGES][2][VERT_NAME_LEN],
		   int edgeSize[NUM_VERTICES],
		   char treeVertexNames[NUM_VERTICES][VERT_NAME_LEN],
		   int treeVertexSize)
{
  int i,j;

  for(i = 0; i < vertexSize; i++)
    {
      for(j = 0; j < edgeSize[i]; j++)
	{
	  int vert1Loc;
	  int vert2Loc;
	  char vert1[VERT_NAME_LEN];
	  char vert2[VERT_NAME_LEN];
	  char weight[VERT_NAME_LEN];

	  strcpy(vert1,vertexNames[i]);
	  strcpy(vert2,edges[i][j][0]);
	  strcpy(weight,edges[i][j][1]);

	  if(strcmp(vert2,"\0") != 0)
	    {
	      vert1Loc = findVertIndex(vert1,treeVertexSize,treeVertexNames);
	      vert2Loc = findVertIndex(vert2,treeVertexSize,treeVertexNames);

	      if(vert1Loc != -1 && vert2Loc != -1)
		{
		  removeEdge(vert1,vert2,weight,vertexNames,vertexSize,edges,
			     edgeSize);
		}
	    }
	}
    }
}

void removeEdge(char vert1[VERT_NAME_LEN], char vert2[VERT_NAME_LEN],
		char weight[VERT_NAME_LEN],
		char vertexNames[NUM_VERTICES][VERT_NAME_LEN], int vertexSize,
		char edges[NUM_VERTICES][NUM_EDGES][2][VERT_NAME_LEN],
		int edgeSize[NUM_VERTICES])
{
  int vert1Loc = findVertIndex(vert1,vertexSize,vertexNames);
  int vert2Loc = findVertIndex(vert2,vertexSize,vertexNames);
  int vert1Edge,vert2Edge;
  int i;

  /* Find the location of the edges in the array. */
  for(i = 0; i < edgeSize[vert1Loc]; i++)
    {
      if(strcmp(vert2,edges[vert1Loc][i][0]) == 0)
	{
	  vert1Edge = i;
	}
    }
  for(i = 0; i < edgeSize[vert2Loc]; i++)
    {
      if(strcmp(vert1,edges[vert2Loc][i][0]) == 0)
	{
	  vert2Edge = i;
	}
    }

  /* Remove the edges. 
     Update the weight with -1 to indicate that no edge exists in this 
     location. */
  strcpy(edges[vert1Loc][vert1Edge][0],"\0");
  strcpy(edges[vert1Loc][vert1Edge][1],"-1");
  
  strcpy(edges[vert2Loc][vert2Edge][0],"\0");
  strcpy(edges[vert2Loc][vert2Edge][1],"-1");
  
  return;
}
