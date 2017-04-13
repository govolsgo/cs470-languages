import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;
import java.util.Set;

public class Prim {
    // New Graph object to store the graph from the file.
    private Graph inputGraph = new Graph();

    // New Graph object to store the MST we create.
    private Graph mst = new Graph();

    // New Graph object to store a copy of inputGraph we can modify.
    private Graph workingGraph = new Graph();
    
    public static void main(String[] args) {
	Prim primObj = new Prim();

	// Make sure an input file was entered.
	if(args.length > 0){
	    primObj.readGraph(args[0]); // Read in a graph.
	}
	else{
	    // Print error and exit if no input file is specified.
	    System.out.println("No input file specified.");
	    System.exit(0);
	}

	// Create the MST.
	primObj.createMST();

	// Print the results.
	primObj.printInputGraph();
	System.out.print("\n");
	primObj.printMSTGraph();
    }

    public void createMST(){
	String minEdge;
	int minWeight;
	TreeMap<String,Integer> edgeSet;
	ArrayList<TreeMap<String,Integer>> edgeSets;
	
	// First run, prime MST.
	// Find the smallest edge connected to the first vertex.
	minEdge = workingGraph.getMinEdge(0);
	edgeSet = workingGraph.getEdges(workingGraph.getVerts(0));
	minWeight = edgeSet.get(minEdge);
	
	// Add both vertices and the edge to the MST.
	mst.addVert(workingGraph.getVerts(0));
	mst.addVert(minEdge);
	mst.addEdge(workingGraph.getVerts(0), minEdge, minWeight);
	
	// Remove added edge from workingGraph.
	workingGraph.removeEdge(workingGraph.getVerts(0), minEdge);

	// Loop to create the rest of the graph.
	while(!inputGraph.getVerts().equals(mst.getVerts())){
	    // Get the new edge to add to the MST.
	    String[] newEdge = getMinConnEdge();

	    // Add the new vertex and edge.
	    mst.addVert(newEdge[1]);
	    mst.addEdge(newEdge[0],newEdge[1],Integer.parseInt(newEdge[2]));

	    // Remove the edge added to the MST from the copy Graph
	    // we can modify.
	    workingGraph.removeEdge(newEdge[0],newEdge[1]);

	    // Remove any edges from the copy Graph so we don't accidentally
	    // add an edge that creates a cycle.
	    preventCycles();
	}
    }
    
    private String[] getMinConnEdge(){
	ArrayList<String> vertices = mst.getVerts();
	
	// Used to store the current minimal edge.
	String v1 = new String();
	String v2 = new String();
	int minEdge = -1;

	// Used to store a set of edges from a call to Graph.
	TreeMap<String,Integer> edges;
	Boolean unInit = true; // Check if we've initialized the tracking vars.

	// Loop through every vert in the MST.
	for(int i = 0; i < vertices.size(); i++){
	    // Store edges connected to that vert.
	    edges = workingGraph.getEdges(vertices.get(i));

	    // Make sure there are actually edges connected to this vert.
	    if(edges.size() != 0){
		// Initialize tracking vars if this is the first run.
		if(unInit == true){
		    v1 = vertices.get(i);
		    v2 = edges.firstKey();
		    minEdge = edges.get(v2);
		    unInit = false;
		}

		// Loop through every edge connected to this vert.
		for(Map.Entry<String,Integer> entry : edges.entrySet()){
		    // Update tracker vars if there is a smaller edge.
		    if(entry.getValue() < minEdge){
			v1 = vertices.get(i);
			v2 = entry.getKey();
			minEdge = entry.getValue();
		    }
		}
	    }
	}

	// Create a String array to return the minimal edge.
	String[] minEdgeVerts = {v1,v2,Integer.toString(minEdge)};

	return minEdgeVerts;
    }

    public int inputWeight(){
	return inputGraph.weight();
    }

    public int MSTWeight(){
	return mst.weight();
    }

    private void preventCycles(){
	ArrayList<String> addedVerts = mst.getVerts();

	// Loop through the verts in the MST.
	for(int i = 0; i < addedVerts.size(); i++){
	    // Get the vert name and edges connected to it that we are checking.
	    String workingVert = addedVerts.get(i);
	    TreeMap<String,Integer> edges = workingGraph.getEdges(workingVert);

	    // Create a Set of the edges. Avoids a concurrent modification
	    // exception (I think).
	    Set<Map.Entry<String,Integer>> edgeSet = edges.entrySet();

	    // Loop through every edge connected to the workingVert.
	    for(Map.Entry<String,Integer> entry : edgeSet){
		// If this vert is also in the MST, it creates a loop.
		// Get rid of it!!!
		if(addedVerts.contains(entry.getKey())){
		    workingGraph.removeEdge(workingVert, entry.getKey());
		}
	    }
	}
    }
    
    public void printInputGraph(){
	// Create ArrayList to store formatted edges for output.
    	ArrayList<String> formattedGraph = inputGraph.getEdges();

	// Loop through the ArrayList and print the edges.
	System.out.println("Input Graph:");
	for(int i = 0; i < formattedGraph.size(); i++){
	    System.out.println(formattedGraph.get(i));
	}
    }

    public void printMSTGraph(){
    	ArrayList<String> formattedGraph = mst.getEdges();

	System.out.println("MST Graph:");
	for(int i = 0; i < formattedGraph.size(); i++){
	    System.out.println(formattedGraph.get(i));
	}
    }
    
    public void readGraph(String fileLoc){
	try{
	    int lineCount = 0;
	    String line;	
	    BufferedReader inFile = new BufferedReader(new FileReader(fileLoc));
	    ArrayList<String> inputFile = new ArrayList<String>();
	    
	    // Store input file in memory.
	    line = inFile.readLine();
	    while(line != null){
		// Increment tracker var.
		lineCount++;
		
		// Split up string.
		String[] parsed = line.split(" ");

		// Store data
		for(int i = 0; i < 3; i++){
		    inputFile.add(parsed[i]);
		}

		// Read in next line.
		line = inFile.readLine();
	    }

	    // Create vertices.
	    for(int i = 0; i < lineCount; i++){
		// Add vertices to list.
		inputGraph.addVert(inputFile.get(i*3));
		inputGraph.addVert(inputFile.get(i*3+1));
		workingGraph.addVert(inputFile.get(i*3));
		workingGraph.addVert(inputFile.get(i*3+1));
	    }
	    
	    // Create edges.
	    for(int i = 0; i < lineCount; i++){
		// Add edge to list.
		inputGraph.addEdge(inputFile.get(i*3),
				   inputFile.get(i*3+1),
				   Integer.parseInt(inputFile.get(i*3+2)));
		workingGraph.addEdge(inputFile.get(i*3),
				   inputFile.get(i*3+1),
				   Integer.parseInt(inputFile.get(i*3+2)));
		
	    }
	}
	catch(Exception e){
	    // Catch FileNotFound or IO exceptions.
	    // Print error and exit.
	    System.out.println("Exception: " + e.getMessage());
	    System.exit(0);
	}
    }

    public int sizeInputEdges(){
	return inputGraph.sizeEdges();
    }
    
    public int sizeInputVerts(){
	return inputGraph.sizeVerts();
    }

    public int sizeMSTEdges(){
	return mst.sizeEdges();
    }
    
    public int sizeMSTVerts(){
	return mst.sizeVerts();
    }
}
