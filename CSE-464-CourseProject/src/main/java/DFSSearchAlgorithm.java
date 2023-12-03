import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;


public class DFSSearchAlgorithm extends SearchAlgorithm{

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~CONSTRUCTOR~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public DFSSearchAlgorithm(Graph<String, DefaultEdge> ig) {
        super(ig);
    }

    //DFS Searching starts by adding no nodes to the explored queue
    public List<String> initializeExploredList(String src){

        return new ArrayList<String>();

    }

    //DFS pulls values from the end of the list
    public String getNextNode(){

        return super.explorable.remove(super.explorable.size() - 1);

    }

    //DFS adds current node to explored if it has not yet been explored
    //and adds all nodes adjacent to the current node to the explorable list
    public void updateExplorableList(String current){
        //Grab all the edges for the current node
        List<String> nodes = getAdjacentNodes(current);

        if(!explored.contains(current)){
            explored.add(current);
            for(String adjacentNode : nodes){
                explorable.add(0,adjacentNode);
            }
        }
    }
}