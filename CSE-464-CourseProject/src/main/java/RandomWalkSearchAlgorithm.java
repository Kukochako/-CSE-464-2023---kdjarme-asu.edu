//~~~~~~~~~~~~~~~~~~~~~~~~~~~~Part 3: Feature 4~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~Random Walk~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class RandomWalkSearchAlgorithm extends SearchAlgorithm{

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~CONSTRUCTOR~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public RandomWalkSearchAlgorithm(Graph<String, DefaultEdge> ig) {
        super(ig);
    }

    //RWS Searching starts by adding no nodes to the explored queue
    public List<String> initializeExploredList(String src){

        super.explored.add(src);

        System.out.print("Random Search Path");

        return super.explored;

    }

    //RWS pulls values from the start of the list
    public String getNextNode(){

        return super.explorable.remove(0);

    }

    //RWS adds adjacent nodes to explored and explorable list if they have not been explored yet
    public void updateExplorableList(String current){
        //Grab all the edges for the current node
        List<String> nodes = classGraph.getAdjacentNodes(current);

        //insert the objects into the queue then randomize
        for(String adjacentNode : nodes){

            if(!super.explored.contains(adjacentNode)){
                super.explored.add(adjacentNode);
                super.explorable.add(adjacentNode);
            }
        }

        Collections.shuffle(super.explorable); //randomize order
    }
}