//~~~~~~~~~~~~~~~~~~~~~~~~~~~~Part 3: Feature 2~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~Template Pattern~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.graph.DefaultEdge;

public abstract class SearchAlgorithm{

    protected MyPath resultPath = new MyPath();

    protected List<String> explored = new ArrayList<String>(); //Each search algo starts with different values in their explored list
    protected List<String> explorable = new ArrayList<String>();

    ImportedGraph classGraph;

    public SearchAlgorithm(org.jgrapht.Graph<String, DefaultEdge> ig){
        classGraph = new ImportedGraph(ig);
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~Feature 4: Extaction Methods~~~~~~~~~~~~~~~~~~~~~~~~~//

    //builds path by reviewing all the nodes visited and removing ones that do not connect
    protected MyPath buildPath(List<String> explored, String src, String dst) {

        //if the no path to the dst was found
        if(!explored.contains(dst))
            return null;
        else if(explored.size() > 1){ //if path was found, trim the explored nodes to only contains the one relevant to the path

            String currently = explored.get(explored.size()-1);
            String before = explored.get(explored.size()-2);

            if(!explored.get(explored.size() - 1).equals(dst)){explored.add(dst);} //add destination to end of list so the path will guarantee end with the dst
            int ref = 1; //used to hold the position of where in the list we are comparing the values from

            //Strating from the end of the list, compare the current node to the one before it
            //if the node before it has an edge to the current node, move the reference pointer to compare from the node before the current node now
            //if there is no edge from the node before to the current node, remove the node before
            while(!before.equals(src)){

                //if edge to current node does exist, include it in path
                currently = explored.get(explored.size()-ref);
                before = explored.get(explored.size()-ref-1);

                if (classGraph.getIsEdge(before, currently)) {
                    ref = ref + 1;
                }
                else{
                    explored.remove(explored.size()-ref-1);
                }

                //System.out.println(before + " " + currently + " ref: " + ref);

            }
        }

        MyPath returnPath = new MyPath();
        for (int i = 0; i < explored.size(); i++) {
            returnPath.addNode(explored.get(i));
        }

        return returnPath;

    }

    //Returns a list of nodes that are adjacent to the given node
    protected List<String> getAdjacentNodes(String src){

        List<String> nodes = new ArrayList<String>();
        Set<DefaultEdge> currentEdges = classGraph.getOutgoingEdges(src);

        for (Object edge : currentEdges) {
            String temp = edge.toString();
            String[] tempArr = temp.split(":");

            //Extracts the name of the adjacent node from the edge string
            nodes.add(tempArr[1].substring(1, tempArr[1].length() - 1));
        }

        return nodes;
    }


    //Class specific search algorithm that searches given graph ig for dst starting from src
    protected MyPath searchGraph(org.jgrapht.Graph<String, DefaultEdge> ig, String src, String dst){

        resultPath= new MyPath();

        explored = initializeExploredList(src); //Each search algo starts with different values in their explored list
        explorable.add(src);

        //If the src node is the destination node just return the path with only the src
        if(src.equals(dst)) {
            resultPath.addNode(src);
            return resultPath;
        }

        //Search Template
        while(!explorable.isEmpty()){
            String current = getNextNode(); //grabs the next node to explore

            updateExplorableList(current); //updates explorable with the next values to explore

        }

        resultPath = buildPath(explored, src, dst); //converts list of explored nodes to MyPath Object

        return resultPath;
    }

    //Abstract methods
    abstract List<String> initializeExploredList(String src);
    abstract String getNextNode();
    abstract void updateExplorableList(String current);

}

