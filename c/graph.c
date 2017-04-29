/**
 * @file graph.c
 * @author Carter Crews
 * @date  28 Apr 2017
 * @brief Implementation file for functionality over graphs.
 *
 * This file contains the implementation of the functions that operate over graphs.
 *
 */

#include "graph.h"

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

int findVertIndex(char findMe[], int vertexSize,
		  char vertexNames[NUM_VERTICES][VERT_NAME_LEN])
{
  int index = -1;
  int i = 0; 

  while(i < NUM_VERTICES && index == -1)
    {
      if(strcmp(findMe, vertexNames[i]) == 0)
	{
	  index = i;
	}
      i++;
    }

  return index;
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

void printGraph(char vertexNames[NUM_VERTICES][VERT_NAME_LEN], int vertexSize,
		char edges[NUM_VERTICES][NUM_EDGES][2][VERT_NAME_LEN],
		int edgeSize[NUM_VERTICES])
{
  int i,j;

  for(i=0; i<vertexSize; i++)
    {
      for(j=0; j<edgeSize[i]; j++)
	{
	  if(strcmp(vertexNames[i],edges[i][j][0]) < 0)
	    {
	      printf("(%s,%s,%s)\n",
		     vertexNames[i],edges[i][j][0],edges[i][j][1]);
	    }
	}
    }
}

void readData(char vertexNames[NUM_VERTICES][VERT_NAME_LEN], int* vertexSize, 
	      char edges[NUM_VERTICES][NUM_EDGES][2][VERT_NAME_LEN],
	      int edgeSize[NUM_VERTICES], FILE* file)
{
  char tempVertex1[VERT_NAME_LEN];
  char tempVertex2[VERT_NAME_LEN];
  char weight[VERT_NAME_LEN];
  int vert1Index = -1;
  int vert2Index = -1;

  /* Prime the while loop with an initial read. */
  fscanf(file,"%s",tempVertex1);
  fscanf(file,"%s",tempVertex2);
  fscanf(file,"%s",weight);
  vert1Index = findVertIndex(tempVertex1,*vertexSize,vertexNames);
  vert2Index = findVertIndex(tempVertex2,*vertexSize,vertexNames);

  /* While loop to read in vertices. */
  while(!feof(file))
    {
      /* Check if vertices exist in memory. If not, add them. */
      if(vert1Index == -1)
	{
	  strcpy(vertexNames[*vertexSize],tempVertex1);
	  vert1Index = *vertexSize;
	  (*vertexSize)++;
	}
      if(vert2Index == -1)
	{
	  strcpy(vertexNames[*vertexSize],tempVertex2);
	  vert2Index = *vertexSize;
	  (*vertexSize)++;
	}

      /* Copy the data read in to the array of edges. */
      strcpy(edges[vert1Index][edgeSize[vert1Index]][0],tempVertex2);
      strcpy(edges[vert1Index][edgeSize[vert1Index]][1],weight);
      edgeSize[vert1Index]++;

      /* Make the graph undirected. */
      strcpy(edges[vert2Index][edgeSize[vert2Index]][0],tempVertex1);
      strcpy(edges[vert2Index][edgeSize[vert2Index]][1],weight);
      edgeSize[vert2Index]++;

      /* Next read from file. */
      fscanf(file,"%s",tempVertex1);
      fscanf(file,"%s",tempVertex2);
      fscanf(file,"%s",weight);
      vert1Index = findVertIndex(tempVertex1,*vertexSize,vertexNames);
      vert2Index = findVertIndex(tempVertex2,*vertexSize,vertexNames);
    }

  return;
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
