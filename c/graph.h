#include "stdio.h"
#include "string.h"
#include "prim.h"
#include "constants.h"

int findVertIndex(char findMe[], int vertexSize,
		  char vertexNames[NUM_VERTICES][VERT_NAME_LEN]);
void getFileName(char fileName[]);
FILE* openFile(char fileName[]);
void readData(int* vertexSize, char vertexNames[NUM_VERTICES][VERT_NAME_LEN],
	      int* edgeSize, char edges[NUM_VERTICES][NUM_EDGES][2],
	      FILE* file);
