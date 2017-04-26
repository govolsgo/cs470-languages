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

void readData(char vertexNames[NUM_VERTICES][VERT_NAME_LEN], int* vertexSize, 
	      char edges[NUM_VERTICES][NUM_EDGES][2][WEIGHT_DIGIT_LEN],
	      int edgeSize[NUM_VERTICES], FILE* file)
{
  char tempVertex1[VERT_NAME_LEN];
  char tempVertex2[VERT_NAME_LEN];
  int weight;
  int vert1Index = -1;
  int vert2Index = -1;
  
  fscanf(file,"%s",tempVertex1);
  fscanf(file,"%s",tempVertex2);
  fscanf(file,"%d",&weight);
  vert1Index = findVertIndex(tempVertex1,*vertexSize,vertexNames);
  vert2Index = findVertIndex(tempVertex2,*vertexSize,vertexNames);

  printf("tempVertex1: %s\n",tempVertex1);
  printf("tempVertex2: %s\n",tempVertex2);
  printf("weight: %d\n",weight);
  printf("Vertex 1 loc: %d\n",vert1Index);
  printf("Vertex 2 loc: %d\n",vert2Index);

  while(vert1Index==-1/*!feof(file)*/)
    {
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

      strcpy(edges[vert1Index][edgeSize[vert1Index]][0],tempVertex2);
      edgeSize[vert1Index]++;
      
      
      printf("vertex[%d]: %s\n",vert1Index,vertexNames[vert1Index]);
      printf("vertex[%d]: %s\n",vert2Index,vertexNames[vert2Index]);
      printf("weight: %d\n",weight);
    }
}
