#ifndef PRIM_H
#define PRIM_H

#include <stdlib.h>
#include "graph.h"
#include "constants.h"

void addEdge(char vert1[VERT_NAME_LEN], char vert2[VERT_NAME_LEN],
	     char weight[VERT_NAME_LEN],
	     char vertexNames[NUM_VERTICES][VERT_NAME_LEN], int* vertexSize,
	     char edges[NUM_VERTICES][NUM_EDGES][2][VERT_NAME_LEN],
	     int edgeSize[NUM_VERTICES]);

int getMinEdge(int vert, char edges[NUM_VERTICES][NUM_EDGES][2][VERT_NAME_LEN],
	       int edgeSize[NUM_VERTICES]);

void removeEdge(char vert1[VERT_NAME_LEN], char vert2[VERT_NAME_LEN],
		char weight[VERT_NAME_LEN],
		char vertexNames[NUM_VERTICES][VERT_NAME_LEN], int* vertexSize,
		char edges[NUM_VERTICES][NUM_EDGES][2][VERT_NAME_LEN],
		int edgeSize[NUM_VERTICES]);

#endif
