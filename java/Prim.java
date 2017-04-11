import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Prim {
    public static void main(String[] args) {
	Graph inputGraph = new Graph();

	if(args.length > 0){
	    inputGraph = readGraph(args); // Read in a graph.
	}
	else{
	    System.out.println("No input file specified.");
	    System.exit(0);
	}

	inputGraph.printVerts();
	inputGraph.printEdges();
	System.out.println("sizeVerts: " + inputGraph.sizeVerts());
	System.out.println("sizeEdges: " + inputGraph.sizeEdges());
	System.out.println("totWeight: " + inputGraph.weight());

	ArrayList<String> test = inputGraph.getEdges();

	System.out.println("Formatted Edges:");
	for(int i = 0; i < test.size(); i++){
	    System.out.println(test.get(i));
	}
    }

    static Graph readGraph(String[] args){
	Graph inputGraph = new Graph();

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
}
