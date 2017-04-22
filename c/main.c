#include "stdio.h"
#include "string.h"
#include "graph.h"
#include "prim.h"

int main ()
{
  char fileName[1000];
  FILE* file;
  char testInput[25];

  getFileName(fileName);
  file = openFile(fileName);
  

  printf("file=%p\n",file);
  printf("About to read input file.\n");
  fscanf(file,"%s",testInput);

  while(!feof(file))
    {
      printf("Read: %s\n",testInput);
      fscanf(file,"%s",testInput);
    }

  fclose(file);
  
  return 0;
}


