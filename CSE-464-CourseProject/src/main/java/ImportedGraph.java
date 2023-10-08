import org.jgrapht.graph.*;
import org.jgrapht.nio.dot.*;

import java.io.*;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

public class ImportedGraph {

    //Instance variable that stores the value of the parsed graph
    private org.jgrapht.Graph<String, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class);

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~ACCESSORS~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    int getAmountOfVertices(){
        return g.vertexSet().size();
    }

    int getAmountOfEdges(){
        return g.edgeSet().size();
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~Feature 1: importing graphs~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    //Allows a user to parse a DOT file to create a graph
    boolean parseGraph(String filePath) throws IOException {

        //Creates importer object to facillitate translating dot format to graph variable
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
    boolean outputGraph(String filepath) throws IOException {

        try {
            Files.writeString(Paths.get(filepath + "outputOG.txt"), toString(), StandardCharsets.UTF_8);
        }
        catch(IOException e){
            System.out.println(e);
        }

        return true;
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~Feature 2: Adding Vertices~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    //Function that allows a user to add a node to the imported graph
    String addNode(String label){

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
    void addNodes(String[] labels){

        Set<String> allVertices = g.vertexSet();

        //if all vertex to be added already exists, do not append it to the graph
        for(String vertex : labels) {
            addNode(vertex);
        }
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~Feature 2: Adding Edges~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    //Adds an edge to and from specified nodes
    void addEdge(String srcLabel, String dstLabel){

        //First, check if the labels specified exist in the graph
        //and add them if they are not
        String[] sources = {srcLabel, dstLabel};
        this.addNodes(sources);

        //Then check if the edge relationship makes sense
        if(g.containsEdge(srcLabel, dstLabel)){
            System.out.println("Failed to add edge. " + srcLabel + " -> " + dstLabel + "already exists!");
        }
        else{ //If edge makes sense, add its relationship to the graph
            g.addEdge(srcLabel, dstLabel);
        }

    }

}
