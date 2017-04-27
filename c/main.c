#include "stdio.h"
#include "string.h"
#include "graph.h"
#include "prim.h"
#include "constants.h"

int main()
{
  char fileName[1000];
  FILE* file;
  
  int graphVertexSize = 0;
  int MSTVertexSize = 0;

  char graphVertexNames[NUM_VERTICES][VERT_NAME_LEN];
  char MSTVertexNames[NUM_VERTICES][VERT_NAME_LEN];

  char graphEdges[NUM_VERTICES][NUM_EDGES][2][VERT_NAME_LEN];
  int graphEdgeSize[NUM_VERTICES] = {0};

  char workingEdges[NUM_VERTICES][NUM_EDGES][2][VERT_NAME_LEN];
  int workingEdgeSize[NUM_VERTICES] = {0};

  char MSTEdges[NUM_VERTICES][NUM_EDGES][2][VERT_NAME_LEN];
  int MSTEdgeSize[NUM_VERTICES] = {0};
  
  getFileName(fileName);
  file = openFile(fileName);

  readData(graphVertexNames,&graphVertexSize,graphEdges,graphEdgeSize,file);
  fclose(file);

  printGraph(graphVertexNames,graphVertexSize,graphEdges,graphEdgeSize);
  
  return 0;
}


