import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.*;
import org.jgrapht.graph.*;
import org.jgrapht.nio.dot.*;

import java.io.*;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static guru.nidi.graphviz.model.Factory.*;

public class ImportedGraph {

    //Instance variable that stores the value of the parsed graph
    private org.jgrapht.Graph<String, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class);

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~ACCESSORS~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public int getAmountOfVertices(){
        return g.vertexSet().size();
    }

    public int getAmountOfEdges(){
        return g.edgeSet().size();
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~Feature 1: importing graphs~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    //Allows a user to parse a DOT file to create a graph
    public boolean parseGraph(String filePath) throws IOException {

        //Creates importer object to facilitate translating dot format to graph variable
        DOTImporter<String, DefaultEdge> dotimp = new DOTImporter<>();
        dotimp.setVertexFactory(vertexName -> vertexName); //defines how vertexes are created

        try {
            //reads dotfile from input and creates reader to process it
            File myFile = new File(filePath);
            FileReader rd = new FileReader(myFile);

            dotimp.importGraph(g, rd); //imports dotfile data into graph variable

            rd.close(); //closes reader to prevent memory leaks
        }
        catch(IOException e){
            System.out.println(e);
            return false;
        }

        return true;
    }

    //Prints out the number of nodes, the labels of the nodes, the number of edges
    //and their directions
    public String toString(){

        String returnMe = ""; //String that will contain the compiled information of the graph

        //grab all vertices and return them in proper string format
        Set<String> allVertices = g.vertexSet();
        returnMe += "Number of Vertices: " + allVertices.size() + "\n";
        returnMe += "Vertex Names:\n";
        for(String vertex : allVertices ){
            returnMe += vertex.toString() + "\n";
        }

        //grab all edges and return them in proper string format
        Set<DefaultEdge> allEdges = g.edgeSet();
        returnMe += "\nNumber of Edges: " + allEdges.size() + "\n";
        returnMe += "Edge Directions:\n";
        for(Object edge : allEdges){
            String temp = edge.toString();
            String[] tempArr = temp.split(":");

            returnMe += tempArr[0].substring(1, tempArr[0].length()-1) +
                    " -> " +
                    tempArr[1].substring(1,tempArr[1].length()-1) +
                    "\n";
        }

        return returnMe;
    }

    //writes out graph to string file of specified location
    public boolean outputGraph(String filepath) throws IOException {

        try {
            if(!Files.exists(Path.of(filepath))) {
                System.out.println("An error has occurred, check your filepath!");
                return false;
            }

            Files.writeString(Paths.get(filepath), toString(), StandardCharsets.UTF_8);
        }
        catch(Exception e){
            System.out.println("An error has occurred, check your filepath!");
            return false;
        }

        return true;
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~Feature 2: Adding Vertices~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    //Function that allows a user to add a node to the imported graph
    public String addNode(String label){

        Set<String> allVertices = g.vertexSet();
        //if all vertex to be added already exists, do not append it to the graph
        if(allVertices.contains(label)){
            return "Failed to add! Vertex " + label + " already exists!";
        }
        else{
            g.addVertex(label);
        }

        return label + " successfully added!";
    }

    //Function that allows a user to add multiple nodes at once to the imported graph
    public void addNodes(String[] labels){

        //if all vertex to be added already exists, do not append it to the graph
        for(String vertex : labels) {
            System.out.println(addNode(vertex));
        }
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~Feature 3: Adding Edges~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    //Adds an edge to and from specified nodes
    public void addEdge(String srcLabel, String dstLabel){

        //First, check if the labels specified exist in the graph
        //and add them if they are not
        String[] sources = {srcLabel, dstLabel};
        addNodes(sources);

        //Then check if the edge relationship makes sense
        if(g.containsEdge(srcLabel, dstLabel)){
            System.out.println("Failed to add edge. " + srcLabel + " -> " + dstLabel + " already exists!");
        }
        else{ //If edge makes sense, add its relationship to the graph
            g.addEdge(srcLabel, dstLabel);
            System.out.println("New edge: " + srcLabel + " -> " + dstLabel + " was added successfully!");
        }

    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~Feature 4: Exporting Graphs~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    //Function that exports the imported graph object to be output as a DOT file
    public boolean outputDOTGraph(String filePath){

        //Creates importer object to facillitate translating dot format to graph variable
        DOTExporter<String, DefaultEdge> dotexp = new DOTExporter<>(vertexName -> vertexName);
        //dotexp.setVertexAttributeProvider();

        try {
            if(!Files.exists(Path.of(filePath))) {
                System.out.println("An error has occurred, check your filepath!");
                return false;
            }
            //reads dotfile from input and creates reader to process it
            File myFile = new File(filePath);
            FileWriter wr = new FileWriter(myFile);

            dotexp.exportGraph(g,wr); //exports dotfile data into graph variable

            wr.close(); //closes writer to prevent memory leaks
        }
        catch(Exception e){
            System.out.println("An error has occurred, check your filepath!");
            return false;
        }

        return true;

    }

    public boolean outputGraphics(String filePath, String format) throws IOException {

            //checks if given directory exists
            if(!Files.exists(Path.of(filePath))) {
                System.out.println("An error has occurred, check your filepath!");
                return false;
            }

            //Creates Graph in format of viz-graph specifications
            MutableGraph vizg = mutGraph().setDirected(true);//mutgraph("exportedGraph").directed();

            //adds all vertices as nodes to the vizgraph
            Set<String> allVertices = g.vertexSet();
            for(String vertex : allVertices ) {
                Node newNode = node(vertex);
                vizg.add(newNode);
            }

            //adds all edges to the vizgraph
            Set<DefaultEdge> allEdges = g.edgeSet();
            for(Object edge : allEdges){

                //Grabs the name of the nodes
                String temp = edge.toString(); //array of all the edge in string format
                String[] tempArr = temp.split(":"); //splits the string between the name of the first node and the second

                //node(tempArr[0].substring(1, tempArr[0].length()-1)) refers to the name of the source node
                //node(tempArr[1].substring(1,tempArr[1].length()-1)) refers to the name of the target node
                vizg.add(node(tempArr[0].substring(1, tempArr[0].length()-1)).link(node(tempArr[1].substring(1,tempArr[1].length()-1))));
            }

            //Changes output image based on specified format,
            // more features intended to be added later
        switch(format){

            case "PNG":
                try {
                    Graphviz.fromGraph(vizg).width(900).render(Format.PNG).toFile(new File(filePath + "outputOutputGraphics.png"));
                }
                catch(Exception e){
                    System.out.println("An error has occurred, check your filepath!");
                    return false;
                }
            break;

            default:
                System.out.println("Format entered is not recognized!");
                return false;
        }

        return true;

    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~PART 2~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //~~~~~~~~~~~~~~~~~~~~~~~Feature 1: Removing Nodes and Edges~~~~~~~~~~~~~~~~~~~~~~~//

    //Function that allows a user to remove a node to the imported graph
    public String removeNode(String label){

        Set<String> allVertices = g.vertexSet();
        //if all vertex to be added already exists, do not append it to the graph
        if(!allVertices.contains(label)){
            return "Failed to remove! Vertex " + label + " does not exist!";
        }
        else{
            g.removeVertex(label);
        }

        return label + " successfully removed!";
    }

    //Function that allows a user to remove multiple nodes at once to the imported graph
    public void removeNodes(String[] labels){

        //if all vertex to be added already exists, do not append it to the graph
        for(String vertex : labels) {
            System.out.println(removeNode(vertex));
        }
    }

    //Removes an edge to and from specified nodes
    public void removeEdge(String srcLabel, String dstLabel){

        //Check if the edge relationship makes sense
        if(!g.containsEdge(srcLabel, dstLabel)){
            System.out.println("Failed to remove edge. " + srcLabel + " -> " + dstLabel + " does not exist!");
        }
        else{ //If edge makes sense, remove its relationship to the graph
            g.removeEdge(srcLabel, dstLabel);
            System.out.println("Edge: " + srcLabel + " -> " + dstLabel + " was removed successfully!");
        }

    }

    //Feature 2: DFS Search
    public MyPath GraphSearch(String src, String dst){

        List<String> discovered = new ArrayList<>();
        MyPath resultPath = new MyPath();
        discovered = DFS(resultPath, discovered, src, dst);

        resultPath = new MyPath();
        for(String node : discovered)System.out.println(node);
        System.out.println(resultPath);


        //Trim resultPath to only contain relevant path
        //if the no path to the dst was found
        if(!discovered.contains(dst))
            return null;
        else if((discovered.size() > 1)){ //if path was found, trim the explored nodes to only contains the one relevant to the path

            String currently = discovered.get(discovered.size()-1);
            String before = discovered.get(discovered.size()-2);
            int ref = 1;
            while(!before.equals(src)){
                //if edge to current node does exist, include it in path
                currently = discovered.get(discovered.size()-ref);
                before = discovered.get(discovered.size()-ref-1);

                if (g.containsEdge(before, currently)) {
                    ref = ref + 1;
                }
                else{
                    discovered.remove(discovered.size()-ref-1);
                }

                System.out.println(before + " " + currently);

            }

        }

        for(int i = 0; i < discovered.size(); i++){ resultPath.addNode(discovered.get(i)); }

        return resultPath;
    }



    private List<String> DFS(MyPath returnMe, List<String> discovered, String src, String dst){

        discovered.add(src);
        returnMe.addNode(src);
        //System.out.println(src);
        //Base case
        if(src.equals(dst)){
            return discovered;
        }

        //Grab all the edges for the current node
        List<String> edges = new ArrayList<>();
        Set<DefaultEdge> currentEdges = g.outgoingEdgesOf(src);

        for(Object edge : currentEdges){
            String temp = edge.toString();
            String[] tempArr = temp.split(":");

            edges.add(tempArr[1].substring(1,tempArr[1].length()-1));
            //System.out.println(edge);
        }

        for(String edge : edges){
            if(!discovered.contains(edge)){
                DFS(returnMe, discovered, edge, dst);
            }

            //returnMe.removeNode(edge);
        }

        return discovered;

    }

}
