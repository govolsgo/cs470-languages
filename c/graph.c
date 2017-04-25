#include "graph.h"

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

void getFileName(char fileName[])
{
  printf("Enter graph file name: ");
  scanf("%999s",fileName);

  return;
}

FILE* openFile(char fileName[])
{
  FILE* file;
  file = fopen(fileName,"r");

  /* Make sure we opened the file. Retry if not. */
  while(file == NULL)
    {
      printf("Can't open file.\n");
      getFileName(fileName);
      file = fopen(fileName,"r");
    }

  return file;
}

void readData(int* vertexSize, char vertexNames[NUM_VERTICES][VERT_NAME_LEN],
	      int* edgeSize, char edges[NUM_VERTICES][NUM_EDGES][2],
	      FILE* file)
{
  char tempVertex[VERT_NAME_LEN];
  int vert1Index = -1;
  
  fscanf(file,"%s",tempVertex);
  vert1Index = findVertIndex(tempVertex,*vertexSize,vertexNames);

  printf("Vertex 1 loc: %d\n",vert1Index);

  /*
  while(!feof(file))
    {
      if()
	{
	 
	}
    }
  */
}
