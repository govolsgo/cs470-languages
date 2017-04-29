#include <stdio.h>
#include <string.h>
#include "graph.h"
#include "prim.h"
#include "constants.h"

int main(int argc, char* argv[])
{
  char fileName[1000];

  /* If a filename was passed at runtime, copy it to the fileName var. */
  if(argc > 1)
    {
      strcpy(fileName, argv[1]);
    }
  
  FILE* file;
  
  int graphVertexSize = 0;
  int MSTVertexSize = 0;

  char graphVertexNames[NUM_VERTICES][VERT_NAME_LEN];
  char MSTVertexNames[NUM_VERTICES][VERT_NAME_LEN];

  char graphEdges[NUM_VERTICES][NUM_EDGES][2][VERT_NAME_LEN];
  int graphEdgeSize[NUM_VERTICES] = {0};

  char MSTEdges[NUM_VERTICES][NUM_EDGES][2][VERT_NAME_LEN];
  int MSTEdgeSize[NUM_VERTICES] = {0};

  char vert1[VERT_NAME_LEN];
  char vert2[VERT_NAME_LEN];
  char weight[VERT_NAME_LEN];

  /* Check if file was passed at runtime. Get the file to load if not. */
  if(argc == 1)
    {
      getFileName(fileName);
    }
  file = openFile(fileName); /* Try to open the file. */

  /* Read the graph from the file. */
  readData(graphVertexNames,&graphVertexSize,graphEdges,graphEdgeSize,file);
  fclose(file);

  /* Print the input graph before we modify it. */
  printf("Input Graph:\n");
  printGraph(graphVertexNames,graphVertexSize,graphEdges,graphEdgeSize);
  printf("\n");

  strcpy(vert1,graphVertexNames[0]);
  strcpy(vert2,graphEdges[0][getMinEdge(0,graphEdges,graphEdgeSize)][0]);
  strcpy(weight,graphEdges[0][getMinEdge(0,graphEdges,graphEdgeSize)][1]);
  addEdge(vert1,vert2,weight,MSTVertexNames,&MSTVertexSize,MSTEdges,
	  MSTEdgeSize);

  printf("Pre-remove: %s,%s,%s\n",graphVertexNames[0],graphEdges[0][0][0],
	 graphEdges[0][0][1]);
  removeEdge(vert1,vert2,weight,graphVertexNames,&graphVertexSize,graphEdges,
	     graphEdgeSize);
  printf("Post-remove: %s,%s,%s\n",graphVertexNames[0],graphEdges[0][0][0],
	 graphEdges[0][0][1]);
  

  /*
  printf("%s -> %s,%s\n",vert1,vert2,weight);
  printf("MSTVertexNames[0]: %s\n",MSTVertexNames[0]);
  printf("MSTVertexNames[1]: %s\n",MSTVertexNames[1]);
  printf("%s,%s,%s\n",MSTVertexNames[1],MSTEdges[1][0][0],MSTEdges[1][0][1]);
  */
  /*addEdge(vert1,vert2,weight,graphVertexNames,&graphVertexSize,graphEdges,
    graphEdgeSize);*/

  return 0;
}


