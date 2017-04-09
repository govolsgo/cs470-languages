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
	//inputGraph.printEdges();
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

	    // System.out.println("Read input successfully.");
	    //System.out.println("Line count: " + lineCount);

	    // Create vertices.
	    for(int i = 0; i < lineCount; i++){
		//System.out.println("Creating vertices, line: " + i);
		// Add vertices to list.
		inputGraph.addVert(inputFile.get(i*3));
		//		System.out.println("Adding
		inputGraph.addVert(inputFile.get(i*3+1));

		//System.out.println(line);
	    }
		    /*
	    line = inFile.readLine();
	    numEdges = Integer.parseInt(line); // Read number of edges.

	    // Create edges.
	    for(int i = 0; i < numEdges; i++){
		// Read in next line from file.
		line = inFile.readLine();



		/*	System.out.println(parsed[0]);
		System.out.println(parsed[1]);
		System.out.println(parsed[2]);
		
		// Add edge to list.
		inputGraph.addEdge(parsed[0],parsed[1],Integer.parseInt(parsed[2]));
		
		//System.out.println(line);
		}*/
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
