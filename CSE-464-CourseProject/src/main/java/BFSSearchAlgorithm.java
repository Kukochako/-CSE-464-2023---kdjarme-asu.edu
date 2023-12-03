import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class BFSSearchAlgorithm extends SearchAlgorithm{

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~CONSTRUCTOR~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public BFSSearchAlgorithm(Graph<String, DefaultEdge> ig) {
        super(ig);
    }

    //BFS Searching starts by adding source to the list of explored nodes
    public List<String> initializeExploredList(String src){

        super.explored.add(src);
        return super.explored;

    }

    //BFS pulls values from the start of the list
    public String getNextNode(){

        return super.explorable.remove(0);

    }

    //BFS adds adjacent nodes to explored and explorable list if they have not been explored yet
    public void updateExplorableList(String current){
        //Grab all the edges for the current node
        List<String> nodes = classGraph.getAdjacentNodes(current);

        for(String adjacentNode : nodes){
            if(!super.explored.contains(adjacentNode)){
                super.explored.add(adjacentNode);
                super.explorable.add(adjacentNode);
            }
        }
    }
}