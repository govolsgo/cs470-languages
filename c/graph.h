#ifndef GRAPH_H
#define GRAPH_H

#include <stdio.h>
#include <string.h>
#include "prim.h"
#include "constants.h"

int findVertIndex(char findMe[], int vertexSize,
		  char vertexNames[NUM_VERTICES][VERT_NAME_LEN]);
void getFileName(char fileName[]);
FILE* openFile(char fileName[]);
void printGraph(char vertexNames[NUM_VERTICES][VERT_NAME_LEN], int vertexSize,
		char edges[NUM_VERTICES][NUM_EDGES][2][VERT_NAME_LEN],
		int edgeSize[NUM_VERTICES]);
void readData(char vertexNames[NUM_VERTICES][VERT_NAME_LEN], int* vertexSize, 
	      char edges[NUM_VERTICES][NUM_EDGES][2][VERT_NAME_LEN],
	      int edgeSize[NUM_VERTICES], FILE* file);

#endif
