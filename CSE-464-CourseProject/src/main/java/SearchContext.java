import org.jgrapht.graph.DefaultEdge;

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~Part 3: Feature 3~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~Strategy Pattern~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
public class SearchContext{

    //Make a generalized Search object that will determine what type of search will be done
    private SearchAlgorithm sa;

    //Constructor
    public SearchContext(org.jgrapht.Graph<String, DefaultEdge> classGraph, ImportedGraph.Algorithm Algo){ 
        switch(Algo){

            case BFS:
                sa = new BFSSearchAlgorithm(classGraph);
            break;

            case DFS:
                sa = new DFSSearchAlgorithm(classGraph);
            break;

            case RAND:
                sa = new RandomWalkSearchAlgorithm(classGraph);
            break;
            
            default:
                return;

        }
     }

    //perform specific search based on the context
    public MyPath search(org.jgrapht.Graph<String, DefaultEdge> ig, String src, String dst){
        return sa.searchGraph(ig, src, dst);
    }

}   