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
  char weight[WEIGHT_DIGIT_LEN];
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
