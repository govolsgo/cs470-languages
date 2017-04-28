#include "prim.h"

void addEdge(char vert1[VERT_NAME_LEN], char vert2[VERT_NAME_LEN],
	     char weight[VERT_NAME_LEN],
	     char vertexNames[NUM_VERTICES][VERT_NAME_LEN], int* vertexSize,
	     char edges[NUM_VERTICES][NUM_EDGES][2][VERT_NAME_LEN],
	     int edgeSize[NUM_VERTICES])
{
  int vert1Loc = findVertIndex(vert1,*vertexSize,vertexNames);
  int vert2Loc = findVertIndex(vert2,*vertexSize,vertexNames);

  printf("vert1Loc: %d\n",vert1Loc);
  printf("vert2Loc: %d\n",vert2Loc);

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

void removeEdge(char vert1[VERT_NAME_LEN], char vert2[VERT_NAME_LEN],
		char weight[VERT_NAME_LEN],
		char vertexNames[NUM_VERTICES][VERT_NAME_LEN], int* vertexSize,
		char edges[NUM_VERTICES][NUM_EDGES][2][VERT_NAME_LEN],
		int edgeSize[NUM_VERTICES])
{
  int vert1Loc = findVertIndex(vert1,*vertexSize,vertexNames);
  int vert2Loc = findVertIndex(vert2,*vertexSize,vertexNames);
  int vert1Edge,vert2Edge;
  int i;

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

  strcpy(edges[vert1Loc][vert1Edge][0],"\0");
  strcpy(edges[vert1Loc][vert1Edge][1],"-1");
  
  strcpy(edges[vert2Loc][vert2Edge][0],"\0");
  strcpy(edges[vert2Loc][vert2Edge][1],"-1");
  
  return;
}
