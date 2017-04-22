#include "graph.h"

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

  while(file == NULL)
    {
      printf("Can't open file.\n");
      getFileName(fileName);
      file = fopen(fileName,"r");
    }

  printf("file=%p\n",file);
  printf("Leaving openFile()\n");

  return file;
}
