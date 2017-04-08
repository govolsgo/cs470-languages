import java.io.BufferedReader;
import java.io.FileReader;

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
    }

    static Graph readGraph(String[] args){
	Graph inputGraph = new Graph();

	try{
	    int numVerts;
	    int numEdges;
	    String line;	
	    BufferedReader inFile = new BufferedReader(new FileReader(args[0]));

	    

	    line = inFile.readLine();
	    numVerts = Integer.parseInt(line); // Read number of vertices.

	    // Create vertices.
	    for(int i = 0; i < numVerts; i++){
		// Read in next line from file.
		line = inFile.readLine();
		// Add vertex to list.
		inputGraph.addVert(line);
	    }

	    line = inFile.readLine();
	    numEdges = Integer.parseInt(line); // Read number of edges.

	    // Create edges.
	    for(int i = 0; i < numEdges; i++){
		// Read in next line from file.
		line = inFile.readLine();

		System.out.println(line);
	    }
	}
	catch(Exception e){
	    // Catch FileNotFound or IO exceptions.
	    // Print error and exit.
	    System.out.println(e.getMessage());
	    System.exit(0);
	}

	return inputGraph;
    }
}
