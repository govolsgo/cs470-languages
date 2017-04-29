/**
 * @file graph.h
 * @author Carter Crews
 * @date  28 Apr 2017
 * @brief Header file for functionality over graphs.
 *
 * This file contains header information for the functions that operate over graphs.
 *
 */

#ifndef GRAPH_H
#define GRAPH_H

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "constants.h"

/**
 * Adds an edge to a graph.
 * @author Carter Crews
 * @param vert1 A string containing the name of the first vertex of the edge.
 * @param vert2 A string containing the name of the second vertex of the edge.
 * @param weight A string containing the weight of the edge.
 * @param vertexNames An array of strings containing names of vertices.
 * @param vertexSize A pointer to the size of vertexNames.
 * @param edges An array containing the edges.
 * @param edgeSize An array containing the number of edges in every row of the edges parameter.
 * @date 04/28/2017
 * @return void
 */
void addEdge(char vert1[VERT_NAME_LEN], char vert2[VERT_NAME_LEN],
	     char weight[VERT_NAME_LEN],
	     char vertexNames[NUM_VERTICES][VERT_NAME_LEN], int* vertexSize,
	     char edges[NUM_VERTICES][NUM_EDGES][2][VERT_NAME_LEN],
	     int edgeSize[NUM_VERTICES]);

/**
 * Finds the index location in the array of vertex names of a given vertex.
 * @author Carter Crews
 * @param findMe A string containing the vertex to find.
 * @param vertexSize The size of vertexNames.
 * @param vertexNames An array of strings containing names of vertices.
 * @date 04/28/2017
 * @return Location of findMe in vertexNames.
 */
int findVertIndex(char findMe[], int vertexSize,
		  char vertexNames[NUM_VERTICES][VERT_NAME_LEN]);

/**
 * Finds the smallest edge in a row of the array of edges.
 * @author Carter Crews
 * @param vert Row of edges to search.
 * @param edges An array containing the edges.
 * @param edgeSize An array containing the number of edges in every row of the edges parameter.
 * @date 04/28/2017
 * @return Location of the smallest edge of a given row in the array of edges.
 */
int getMinEdge(int vert, char edges[NUM_VERTICES][NUM_EDGES][2][VERT_NAME_LEN],
	       int edgeSize[NUM_VERTICES]);

/**
 * Finds all edges where both vertices are in the MST and removes them.
 * @author Carter Crews
 * @param vertexNames An array of strings containing names of all vertices.
 * @param vertexSize The size of vertexNames.
 * @param edges An array containing the edges.
 * @param edgeSize An array containing the number of edges in every row of the edges parameter.
 * @param treeVertexNames An array of strings containing names of vertices in the MST.
 * @param treeVertexSize The size of treeVertexNames.
 * @date 04/28/2017
 * @return void
 */
void preventCycles(char vertexNames[NUM_VERTICES][VERT_NAME_LEN],
		   int vertexSize,
		   char edges[NUM_VERTICES][NUM_EDGES][2][VERT_NAME_LEN],
		   int edgeSize[NUM_VERTICES],
		   char treeVertexNames[NUM_VERTICES][VERT_NAME_LEN],
		   int treeVertexSize);

/**
 * Prints a graph to the console.
 * @author Carter Crews
 * @param vertexNames An array of strings containing names of vertices.
 * @param vertexSize The size of vertexNames.
 * @param edges An array containing the edges.
 * @param edgeSize An array containing the number of edges in every row of the edges parameter.
 * @date 04/28/2017
 * @return void
 */
void printGraph(char vertexNames[NUM_VERTICES][VERT_NAME_LEN], int vertexSize,
		char edges[NUM_VERTICES][NUM_EDGES][2][VERT_NAME_LEN],
		int edgeSize[NUM_VERTICES]);

/**
 * Reads a graph in from a file.
 * @author Carter Crews
 * @param vertexNames An array of strings containing names of vertices.
 * @param A pointer to the size of vertexNames.
 * @param edges An array containing the edges.
 * @param edgeSize An array containing the number of edges in every row of the edges parameter.
 * @param file A stream to the file to read in.
 * @date 04/28/2017
 * @return void
 */
void readData(char vertexNames[NUM_VERTICES][VERT_NAME_LEN], int* vertexSize, 
	      char edges[NUM_VERTICES][NUM_EDGES][2][VERT_NAME_LEN],
	      int edgeSize[NUM_VERTICES], FILE* file);

/**
 * Removes an edge from the graph.
 * @author Carter Crews
 * @param vert1 A string containing the name of the first vertex of the edge.
 * @param vert2 A string containing the name of the second vertex of the edge.
 * @param weight A string containing the weight of the edge.
 * @param vertexNames An array of strings containing names of vertices.
 * @param vertexSize The size of vertexNames.
 * @param edges An array containing the edges.
 * @param edgeSize An array containing the number of edges in every row of the edges parameter.
 * @date 04/28/2017
 * @return void
 */
void removeEdge(char vert1[VERT_NAME_LEN], char vert2[VERT_NAME_LEN],
		char weight[VERT_NAME_LEN],
		char vertexNames[NUM_VERTICES][VERT_NAME_LEN], int vertexSize,
		char edges[NUM_VERTICES][NUM_EDGES][2][VERT_NAME_LEN],
		int edgeSize[NUM_VERTICES]);

#endif
