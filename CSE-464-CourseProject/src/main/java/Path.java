import java.util.ArrayList;
import java.util.List;

public class Path {

    //Class list that holds all the names of the nodes in the path
    private List<String> nodes = new ArrayList<String>();

    public int getSize(){ return nodes.size();}

    //Adds a new node to the Path
    public boolean addNode(String newString){
        return nodes.add(newString);
    }

    //removes a given node from the Path
    public boolean removeNode(String removeNode){
        return nodes.remove(removeNode);
    }

    //String output of path
    public String toString(){

        String completePath = "";

        for (int i = 0; i < nodes.size(); i++) {

            completePath += nodes.get(i);

            if(i < nodes.size() - 1)
                completePath += " -> ";
        }

        return completePath;

    }

}
