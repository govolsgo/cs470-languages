import java.util.ArrayList;

public class Graph{
    private ArrayList<String> vertices = new ArrayList<String>();

    public void addVert(String newVert){
	vertices.add(newVert);
    }

    public void printVerts(){
	System.out.println("Vertices:");
	for(int i = 0; i < vertices.size(); i++){
	    System.out.print(i+1);
	    System.out.println(": " + vertices.get(i));
	}
    }
}
