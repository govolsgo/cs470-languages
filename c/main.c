#include "stdio.h"
#include "string.h"
#include "graph.h"
#include "prim.h"
#include "constants.h"

int main()
{
  char fileName[1000];
  FILE* file;
  int vertexSize = 0;
  char vertexNames[NUM_VERTICES][VERT_NAME_LEN];
  char edges[NUM_VERTICES][NUM_EDGES][2][VERT_NAME_LEN];
  int edgeSize[NUM_VERTICES] = {0};
  
  getFileName(fileName);
  file = openFile(fileName);

  /* strcpy(vertexNames[50],"Alfa\0"); */
  
  readData(vertexNames,&vertexSize,edges,edgeSize,file);
  
  /*fscanf(file,"%s",testInput);

  while(!feof(file))
    {
      printf("Read: %s\n",testInput);
      fscanf(file,"%s",testInput);
    }*/

  fclose(file);
  
  return 0;
}


