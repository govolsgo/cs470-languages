import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;
import java.util.Collections;

public class Graph{
    private ArrayList<String> vertices = new ArrayList<String>();
    private ArrayList<TreeMap<String,Integer>> edges = new ArrayList<TreeMap<String,Integer>>();
    private int numVerts = 0;
    private int numEdges = 0;
    private int totWeight = 0;
    
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

    public void printVerts(){
	System.out.println("Vertices:");
	for(int i = 0; i < vertices.size(); i++){
	    System.out.print(i+1);
	    System.out.println(": " + vertices.get(i));
	}
    }

    public void addEdge(String vert1, String vert2, int weight){
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
	
	// Repack the TreeMap.
	edges.set(loc1,adjList1);
	edges.set(loc2,adjList2);
    }
    
    public void addEdgeSet(String vertex, TreeMap<String,Integer> edgeSet){
	addVert(vertex);
	int loc = vertices.indexOf(vertex);
	edges.set(loc,edgeSet);
	numEdges += edgeSet.size();
	for (Map.Entry<String,Integer> entry : edgeSet.entrySet()) {
	    totWeight += entry.getValue();
	}
    }

    public void printEdges(){
	System.out.println("Edges:");
	for(int i = 0; i < edges.size(); i++){
	    System.out.print(vertices.get(i) + "\t-> ");
	    // Unpack for printing.
	    TreeMap<String,Integer> temp = edges.get(i);
	    for (Map.Entry<String,Integer> entry : temp.entrySet()) {
		System.out.print("(" + entry.getKey() + "," + entry.getValue()+ ")");
	    }
	    System.out.print("\n");
	}
    }

    public int sizeVerts(){
	return numVerts;
    }
    
    public int sizeEdges(){
	return numEdges;
    }

    public int weight(){
	return totWeight;
    }
    
    public ArrayList<String> getEdges(){
	ArrayList<String> formattedEdges = new ArrayList<String>();

	for(int i = 0; i < edges.size(); i++){
	    // Unpack to create the ArrayList.
	    TreeMap<String,Integer> edgeMap = edges.get(i);
	    for (Map.Entry<String,Integer> entry : edgeMap.entrySet()) {
		String formattedEdge;
		String reverseFormattedEdge;

		// Create string of the edge in proper format.
		formattedEdge =  "(" + vertices.get(i) + ",";
		formattedEdge += entry.getKey() + "," + entry.getValue() + ")";
		
		// Create a string of the same edge, but vertices swapped.
		// Will use this to make sure the edge is not in the array already.
		reverseFormattedEdge =  "(" + entry.getKey() + ",";
		reverseFormattedEdge += vertices.get(i) + "," + entry.getValue() + ")";
				
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

    public ArrayList<String> getVerts(){
	return vertices;
    }

    public String getVerts(int loc){
	return vertices.get(loc);
    }

    public void removeEdge(String vert1, String vert2){
	int loc1 = vertices.indexOf(vert1);
	int loc2 = vertices.indexOf(vert2);

	// Unpack the TreeMap for each vertex of the edge.
	TreeMap<String,Integer> adjList1 = edges.get(loc1);
	TreeMap<String,Integer> adjList2 = edges.get(loc2);

	// Remove the edge from the list.
	totWeight -= adjList1.get(vert2); // Update weight.
	adjList1.remove(vert2);
	adjList2.remove(vert1);
	numEdges--;
	
	// Repack the TreeMap.
	edges.set(loc1,adjList1);
	edges.set(loc2,adjList2);
    }

    public String getMinEdge(int loc){
	TreeMap<String,Integer> edgeSet = edges.get(loc);
	String minKey = edgeSet.firstKey();

	for (Map.Entry<String,Integer> entry : edgeSet.entrySet()) {
	    if(entry.getValue() < edgeSet.get(minKey)){
		minKey = entry.getKey();
	    }
	}
	return minKey;
    }

    public void updateEdgeSet(String vertex, TreeMap<String,Integer> edgeSet){
	int loc = vertices.indexOf(vertex);
	int oldRowWeight = 0;
	int newRowWeight = 0;
	int oldRowEdgeCount = 0;
	int newRowEdgeCount = 0;
	
	// Modify tracking vars to prep for update.
	for(Map.Entry<String,Integer> entry : edgeSet.entrySet()){
	    newRowEdgeCount++;
	    newRowWeight += entry.getValue();
	}
	for(Map.Entry<String,Integer> entry : getEdges(vertex).entrySet()){
	    oldRowEdgeCount++;
	    oldRowWeight += entry.getValue();
	}
	numEdges += newRowEdgeCount - oldRowEdgeCount;
	totWeight += newRowWeight - oldRowWeight;

	// Update the edges.
	edges.set(loc,edgeSet);
    }
}
