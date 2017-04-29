#ifndef GRAPH_H
#define GRAPH_H

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "constants.h"

void addEdge(char vert1[VERT_NAME_LEN], char vert2[VERT_NAME_LEN],
	     char weight[VERT_NAME_LEN],
	     char vertexNames[NUM_VERTICES][VERT_NAME_LEN], int* vertexSize,
	     char edges[NUM_VERTICES][NUM_EDGES][2][VERT_NAME_LEN],
	     int edgeSize[NUM_VERTICES]);

int findVertIndex(char findMe[], int vertexSize,
		  char vertexNames[NUM_VERTICES][VERT_NAME_LEN]);

int getMinEdge(int vert, char edges[NUM_VERTICES][NUM_EDGES][2][VERT_NAME_LEN],
	       int edgeSize[NUM_VERTICES]);

void preventCycles(char vertexNames[NUM_VERTICES][VERT_NAME_LEN],
		   int vertexSize,
		   char edges[NUM_VERTICES][NUM_EDGES][2][VERT_NAME_LEN],
		   int edgeSize[NUM_VERTICES],
		   char treeVertexNames[NUM_VERTICES][VERT_NAME_LEN],
		   int treeVertexSize);

void printGraph(char vertexNames[NUM_VERTICES][VERT_NAME_LEN], int vertexSize,
		char edges[NUM_VERTICES][NUM_EDGES][2][VERT_NAME_LEN],
		int edgeSize[NUM_VERTICES]);

void readData(char vertexNames[NUM_VERTICES][VERT_NAME_LEN], int* vertexSize, 
	      char edges[NUM_VERTICES][NUM_EDGES][2][VERT_NAME_LEN],
	      int edgeSize[NUM_VERTICES], FILE* file);

void removeEdge(char vert1[VERT_NAME_LEN], char vert2[VERT_NAME_LEN],
		char weight[VERT_NAME_LEN],
		char vertexNames[NUM_VERTICES][VERT_NAME_LEN], int vertexSize,
		char edges[NUM_VERTICES][NUM_EDGES][2][VERT_NAME_LEN],
		int edgeSize[NUM_VERTICES]);

#endif
