import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Prim {
    private Graph inputGraph = new Graph();
    private Graph mst = new Graph();
    
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
    }

    private Graph readGraph(String[] args){
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
	    }
	    
	    // Create edges.
	    for(int i = 0; i < lineCount; i++){
		// Add edge to list.
		inputGraph.addEdge(inputFile.get(i*3),
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

	return inputGraph;
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
    	ArrayList<String> formattedGraph = inputGraph.getEdges();

	System.out.println("MST Graph:");
	for(int i = 0; i < formattedGraph.size(); i++){
	    System.out.println(formattedGraph.get(i));
	}
    }
}
