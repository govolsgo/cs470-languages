import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;
import java.util.Collections;

public class Graph{
    // New ArrayList to store the names of the vertices.
    private ArrayList<String> vertices = new ArrayList<String>();

    // New ArrayList to store TreeMaps containing the edges.
    private ArrayList<TreeMap<String,Integer>> edges
	= new ArrayList<TreeMap<String,Integer>>();

    // New int to track the number of vertices.
    private int numVerts = 0;

    // New int to track the number of edges.
    // Even though the addEdge function adds the edge in two locations
    // (to make the graph undirected), this keeps track of the single
    // edge that was added.
    private int numEdges = 0;

    // New int to track the total weight of the graph.
    private int totWeight = 0;

    public void addEdge(String vert1, String vert2, int weight){
	// Get the index location of each vertex to pull the edge data.
	int loc1 = vertices.indexOf(vert1);
	int loc2 = vertices.indexOf(vert2);
	
	// Unpack the TreeMap for each vertex of the edge.
	TreeMap<String,Integer> adjList1 = edges.get(loc1);
	TreeMap<String,Integer> adjList2 = edges.get(loc2);
	
	// Add the edge to the list.
	adjList1.put(vert2,weight);
	adjList2.put(vert1,weight);
	numEdges++;
	totWeight += weight; // Update weight.
	
	// Save the updated TreeMap.
	edges.set(loc1,adjList1);
	edges.set(loc2,adjList2);
    }
    
    public void addVert(String newVert){
	if(!vertices.contains(newVert)){
	    // Add the new vertex if it's not already in the list.
	    vertices.add(newVert);
	    numVerts++;

	    // Sort the vertex list lexicographically.
	    Collections.sort(vertices);
	    
	    // Add a new TreeMap that corresponds to this vert.
	    edges.add(vertices.indexOf(newVert), new TreeMap<String,Integer>());
	}
    }

    public ArrayList<String> getEdges(){
	// Create an ArrayList to store the edges formatted for printing.
	ArrayList<String> formattedEdges = new ArrayList<String>();

	for(int i = 0; i < edges.size(); i++){
	    // Unpack to create the ArrayList.
	    TreeMap<String,Integer> edgeMap = edges.get(i);

	    // Loop through every edge in the TreeMap that was just unpacked.
	    for (Map.Entry<String,Integer> entry : edgeMap.entrySet()) {
		String formattedEdge;
		String reverseFormattedEdge;

		// Create string of the edge in proper format.
		formattedEdge =  "(" + vertices.get(i) + ",";
		formattedEdge += entry.getKey() + "," + entry.getValue() + ")";
		
		// Create a string of the same edge, but vertices swapped.
		// Will use this to make sure the edge is not in the array
		// already.
		reverseFormattedEdge =  "(" + entry.getKey() + ",";
		reverseFormattedEdge += vertices.get(i) + ","
		    + entry.getValue() + ")";
				
		// Check if reverseFormattedEdge is in ArrayList.
		// Add formattedEdge if not.
		if(!formattedEdges.contains(reverseFormattedEdge)){
		    formattedEdges.add(formattedEdge);
		}
	    }
	}
	return formattedEdges;
      }

    public TreeMap<String,Integer> getEdges(String vert){
	int location = vertices.indexOf(vert);

	return edges.get(location);
    }

    public String getMinEdge(int loc){
	// Pull the edges for a certain vertex.
	TreeMap<String,Integer> edgeSet = edges.get(loc);

	// Keep track of the key for the smallest edge.
	String minKey = edgeSet.firstKey(); 

	// Loop through the edges, finding the smallest edge.
	for (Map.Entry<String,Integer> entry : edgeSet.entrySet()) {
	    // Update the tracker var if a smaller edge is found.
	    if(entry.getValue() < edgeSet.get(minKey)){
		minKey = entry.getKey();
	    }
	}
	return minKey;
    }

    public ArrayList<String> getVerts(){
	return vertices;
    }

    public String getVerts(int loc){
	return vertices.get(loc);
    }

    /* This function was added for debugging purposes.
    public void printEdges(){
	System.out.println("Edges:");
	for(int i = 0; i < edges.size(); i++){
	    System.out.print(vertices.get(i) + "\t-> ");
	    // Unpack for printing.
	    TreeMap<String,Integer> temp = edges.get(i);
	    for (Map.Entry<String,Integer> entry : temp.entrySet()) {
		System.out.print("(" + entry.getKey() + ","
				 + entry.getValue()+ ")");
	    }
	    System.out.print("\n");
	}
     }*/

    /* This function was added for debugging purposes.
    public void printVerts(){
	System.out.println("Vertices:");
	for(int i = 0; i < vertices.size(); i++){
	    System.out.print(i+1);
	    System.out.println(": " + vertices.get(i));
	}
     }*/

    public void removeEdge(String vert1, String vert2){
	// Get the index location of each vertex to pull the edge data.
	int loc1 = vertices.indexOf(vert1);
	int loc2 = vertices.indexOf(vert2);

	// Unpack the TreeMap for each vertex of the edge.
	TreeMap<String,Integer> adjList1 = edges.get(loc1);
	TreeMap<String,Integer> adjList2 = edges.get(loc2);

	// Remove the edge from the list.
	totWeight -= adjList1.get(vert2); // Update weight.
	adjList1.remove(vert2);
	adjList2.remove(vert1);
	numEdges--; // Update the number of edges in the graph.
	
	// Save the updated TreeMap.
	edges.set(loc1,adjList1);
	edges.set(loc2,adjList2);
    }

    public int sizeEdges(){
	return numEdges;
    }

    public int sizeVerts(){
	return numVerts;
    }

    public int weight(){
	return totWeight;
    }
}
