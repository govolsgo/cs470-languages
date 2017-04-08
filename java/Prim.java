import java.io.*;

public class Prim {
    public static void main(String[] args) {
	if(args.length > 0){
	    readGraph(args); // Read in a graph, try args for file first.
	}
	else{
	    System.out.println("No input file specified.");
	    System.exit(0);
	}
    }

    static void readGraph(String[] args){
	try{
	    String line;	
	    BufferedReader inFile = new BufferedReader(new FileReader(args[0]));

	    // Prime the while loop.
	    line = inFile.readLine();
	    
	    while(line != null){
		System.out.println(line);
		
		// Read in next line from file.
		line = inFile.readLine();
	    }
	}
	catch(Exception e){
	    // Catch FileNotFound or IO exceptions.
	    // Print error and exit.
	    System.out.println(e.getMessage());
	    System.exit(0);
	}
    }
}
