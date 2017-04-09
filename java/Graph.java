import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;

public class Graph{
    private ArrayList<String> vertices = new ArrayList<String>();
    private ArrayList<TreeMap<String,Integer>> edges = new ArrayList<TreeMap<String,Integer>>();
    
    public void addVert(String newVert){
	vertices.add(newVert);
	edges.add(new TreeMap<String,Integer>()); // Add a new TreeMap that corresponds to this vert.	
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
	//	System.out.println("test1");
	// Unpack the TreeMap for each vertex of the edge.
	TreeMap<String,Integer> adjList1 = edges.get(loc1);
	TreeMap<String,Integer> adjList2 = edges.get(loc2);
	//	System.out.println("test2");
	// Add the edge to the list.
	adjList1.put(vert2,weight);
	adjList2.put(vert1,weight);
	//	System.out.println("test3");
	// Repack the TreeMap.
	edges.set(loc1,adjList1);
	edges.set(loc2,adjList2);
    }

    public void printEdges(){
	System.out.println("Edges:");
	for(int i = 0; i < edges.size(); i++){
	    System.out.print(vertices.get(i) + "\t->\t");
	    // Unpack for printing.
	    TreeMap<String,Integer> temp = edges.get(i);
	    for (Map.Entry<String,Integer> entry : temp.entrySet()) {
		System.out.print("(" + entry.getKey() + "," + entry.getValue()+ ")");
	    }
	    System.out.print("\n");
	    //System.out.print(i+1);
	    //System.out.println(": " + vertices.get(i));
	}
    }
}
