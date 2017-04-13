import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;

public class Prim {
    private Graph inputGraph = new Graph();
    private Graph mst = new Graph();
    private Graph workingGraph = new Graph();
    
    public static void main(String[] args) {
	Prim primObj = new Prim();

	if(args.length > 0){
	    primObj.readGraph(args); // Read in a graph.
	}
	else{
	    System.out.println("No input file specified.");
	    System.exit(0);
	}

	primObj.printInputVerts();
	primObj.printInputEdges();
	System.out.println("sizeVerts: " + primObj.sizeInputVerts());
	System.out.println("sizeEdges: " + primObj.sizeInputEdges());
	System.out.println("totWeight: " + primObj.inputWeight());

	primObj.printInputGraph();
	primObj.createMST();
    }

    private void readGraph(String[] args){
	try{
	    int lineCount = 0;
	    String line;	
	    BufferedReader inFile = new BufferedReader(new FileReader(args[0]));
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

    private void printInputVerts(){
	inputGraph.printVerts();
    }

    private void printInputEdges(){
	inputGraph.printEdges();
    }

    private int sizeInputVerts(){
	return inputGraph.sizeVerts();
    }

    private int sizeInputEdges(){
	return inputGraph.sizeEdges();
    }

    private int inputWeight(){
	return inputGraph.weight();
    }

    private void printInputGraph(){
    	ArrayList<String> formattedGraph = inputGraph.getEdges();

	System.out.println("Input Graph:");
	for(int i = 0; i < formattedGraph.size(); i++){
	    System.out.println(formattedGraph.get(i));
	}
    }

    private void printMSTVerts(){
	mst.printVerts();
    }

    private void printMSTEdges(){
	mst.printEdges();
    }

    private int sizeMSTVerts(){
	return mst.sizeVerts();
    }

    private int sizeMSTEdges(){
	return mst.sizeEdges();
    }

    private int MSTWeight(){
	return mst.weight();
    }

    private void printMSTGraph(){
    	ArrayList<String> formattedGraph = mst.getEdges();

	System.out.println("MST Graph:");
	for(int i = 0; i < formattedGraph.size(); i++){
	    System.out.println(formattedGraph.get(i));
	}
    }

    private void createMST(){
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

	System.out.print("\n");
	workingGraph.printEdges();
	System.out.print("\n");
	printMSTEdges();

	String[] newEdge = getMinConnEdge();
	System.out.println("v1: " + newEdge[0]);
	System.out.println("v2: " + newEdge[1] + "\n");
	
	mst.addVert(newEdge[1]);
	mst.addEdge(newEdge[0],newEdge[1],Integer.parseInt(newEdge[2]));

	workingGraph.removeEdge(newEdge[0],newEdge[1]);

	System.out.println("big test");
	workingGraph.printEdges();
	System.out.print("\n");
	mst.printEdges();
	/*
	// Loop to create the rest of the graph.
	while(workingGraph.getVerts() == mst.getVerts()){
	    edgeSets = new ArrayList<TreeMap<String,Integer>>();
	    ArrayList<String> verts = new ArrayList<String>();
	    
	    for(int i = 0; i < workingGraph.sizeVerts(); i++){
		if(!mst.getVerts().contains(workingGraph.getVerts(i))){
		    String vertToAdd = workingGraph.getVerts(i);
		    edgeSet = workingGraph.getEdges(vertToAdd);
		    
		    TreeMap<String,Integer> connEdgeSet = new TreeMap<String,Integer>();

		    for(Map.Entry<String,Integer> entry : edgeSet.entrySet()){
			if(mst.getVerts().contains(entry.getKey())){
			    connEdgeSet.put(entry.getKey(), entry.getValue());
			}
		    }

		    edgeSets.add(connEdgeSet);
		    //missingVerts.addEdgeSet(vertToAdd, workingGraph.getEdges(vertToAdd));
		}
	    }

	    System.out.println("Edges:");
	    for(int i = 0; i < edgeSets.size(); i++){
		//System.out.print(vertices.get(i) + "\t->\t");
		// Unpack for printing.
		TreeMap<String,Integer> temp = edgeSets.get(i);
		for (Map.Entry<String,Integer> entry : temp.entrySet()) {
		    System.out.print("(" + entry.getKey() + "," + entry.getValue()+ ")");
		}
		System.out.print("\n");
		}*/
	




	    
	    /*Graph missingVerts = new Graph();
	    	    
	    // Create a Graph object that contains the edges connected to
	    // vertices not in the MST.
	    

	    System.out.println("missingverts:");
	    missingVerts.printEdges();
	    System.out.println("test1");

	    // Remove edges that are not connected to the MST at all.
	    for(int i = 0; i < missingVerts.sizeVerts(); i++){
		TreeMap<String,Integer> workingEdgeSet;
		workingEdgeSet = missingVerts.getEdges(missingVerts.getVerts(i));
		edgeSet = workingEdgeSet;

		System.out.println("test3");

		for(Map.Entry<String,Integer> entry : edgeSet.entrySet()) {
		    System.out.print("hey");
		    if(!mst.getVerts().contains(entry.getKey())){

			System.out.println(" there");

			workingEdgeSet.remove(entry.getKey());

			System.out.println("uhyeah");
		    }
		}

		System.out.println("test2");

		// Save modified TreeMap.
		missingVerts.updateEdgeSet(missingVerts.getVerts(i), workingEdgeSet);

		System.out.println("made it!");*/
	    
	
    }

    private String[] getMinConnEdge(){
	ArrayList<String> vertices = mst.getVerts();
	String v1 = new String();
	String v2 = new String();
	int minEdge = -1;
	TreeMap<String,Integer> edges;
	Boolean unInit = true;

	for(int i = 0; i < vertices.size(); i++){
	    edges = workingGraph.getEdges(vertices.get(i));
	    if(edges.size() != 0){
		if(unInit == true){
		    v1 = vertices.get(i);
		    v2 = edges.firstKey();
		    minEdge = edges.get(v2);
		    unInit = false;
		}
		for(Map.Entry<String,Integer> entry : edges.entrySet()){
		    if(entry.getValue() < minEdge){
			v1 = vertices.get(i);
			v2 = entry.getKey();
			minEdge = entry.getValue();
		    }
		}
		    
		
	    }
	}

	String[] minEdgeVerts = {v1,v2,Integer.toString(minEdge)};

	return minEdgeVerts;
	
	//System.out.println("v1: " + v1);
	//System.out.println("v2: " + v2);
	//System.out.println("min: " + minEdge);
	
    }
}
