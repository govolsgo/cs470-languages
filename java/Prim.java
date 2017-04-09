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
	inputGraph.printEdges();
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

		//System.out.println(line);
	    }

	    line = inFile.readLine();
	    numEdges = Integer.parseInt(line); // Read number of edges.

	    // Create edges.
	    for(int i = 0; i < numEdges; i++){
		// Read in next line from file.
		line = inFile.readLine();

		// Split up string.
		String[] parsed = line.split(" ");

		/*	System.out.println(parsed[0]);
		System.out.println(parsed[1]);
		System.out.println(parsed[2]);*/
		
		// Add edge to list.
		inputGraph.addEdge(parsed[0],parsed[1],Integer.parseInt(parsed[2]));
		
		//System.out.println(line);
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
