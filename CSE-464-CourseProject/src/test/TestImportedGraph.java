import org.apache.commons.io.FileUtils;
import org.junit.*;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class TestImportedGraph {

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Test Value Setup~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    ImportedGraph ig; //Imported Graph Instance that will be used for testing

    @Before
    public void setup(){
        ig = new ImportedGraph();
        System.out.println("~~READY TO TEST~~");
    }

    @After
    public void teardown(){
        System.out.println("~~CLEANING~~\n");
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Feature 1 Tests~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @Test
    //tests Parse Graph functionality
    public void testParseGraph() throws IOException {

        try {
            assertTrue(ig.parseGraph("src/dot/sample.dot"));
        }
        catch(IOException e){
            System.out.println(e);
        }

    }

    @Test
    //tests toString functionality
    public void testToString() throws IOException {

        String result = ""; //captures the value produced after toString is ran

        try {
            ig.parseGraph("src/dot/sample.dot");

            result = ig.toString();
        }
        catch(IOException e){
            System.out.println(e);
        }


        String expected = "Number of Vertices: 4\n" +
                "Vertex Names:\n" +
                "a\n" +
                "b\n" +
                "c\n" +
                "d\n" +
                "\n" +
                "Number of Edges: 4\n" +
                "Edge Directions:\n" +
                "a -> b\n" +
                "b -> c\n" +
                "c -> d\n" +
                "d -> a\n";

        assertEquals("testToString failed!",
                expected,
                result);
    }

    @Test
    //tests toString functionality
    public void testOutputGraph() throws IOException {

        try {
            ig.parseGraph("src/dot/sample.dot");
            ig.outputGraph("src/outputs/");
        }
        catch(IOException e){
            System.out.println(e);
        }

        File expected = new File("src/outputs/expected_outputOG.txt");
        File result = new File("src/outputs/outputOG.txt");

        assertEquals("The files differ!",
                FileUtils.readFileToString(expected, "utf-8"),
                FileUtils.readFileToString(result, "utf-8"));

    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Feature 2 Tests~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @Test
    // Add a new valid vertex to the graph
    public void testAddNodeValid(){

        try {
            ig.parseGraph("src/dot/sample.dot");
        }
        catch(IOException e){
            System.out.println(e);
        }

        String result = ig.addNode("e");
        String expected = "e successfully added!";

        assertEquals(expected, result);

    }

    @Test
    // Attempt to add an invalid vertex to the graph
    public void testAddNodeInvalid(){

        try {
            ig.parseGraph("src/dot/sample.dot");
        }
        catch(IOException e){
            System.out.println(e);
        }

        String result = ig.addNode("d");
        String expected = "Failed to add! Vertex d already exists!";

        assertEquals(expected, result);

    }

    @Test
    //Attempts to add a list of completely new vertices to the graph
    public void testAddNodesValid(){

        String[] newNodes = {"e","f","g","h","i"};

        try {
            ig.parseGraph("src/dot/sample.dot");
        }
        catch(IOException e){
            System.out.println(e);
        }

        ig.addNodes(newNodes);

        int expected = 9;

        assertEquals(expected, ig.getAmountOfVertices());

    }

    @Test
    //Attempts to add a list of some new and some old vertices to the graph
    public void testAddNodesSemialid(){

        String[] newNodes = {"e","a","g","b","i"};

        try {
            ig.parseGraph("src/dot/sample.dot");
        }
        catch(IOException e){
            System.out.println(e);
        }

        ig.addNodes(newNodes);

        int expected = 7;

        assertEquals(expected, ig.getAmountOfVertices());

    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Feature 3 Tests~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    //adds a new edge to the graph
    public void testAddEdgeValid(){

        try {
            ig.parseGraph("src/dot/sample.dot");
        }
        catch(IOException e){
            System.out.println(e);
        }

        ig.addEdge("d", "b");

        int expected = 5;

        assertEquals(expected, ig.getAmountOfEdges());

    }

    @Test
    //adds an old edge to the graph
    public void testAddEdgeInValid(){

        try {
            ig.parseGraph("src/dot/sample.dot");
        }
        catch(IOException e){
            System.out.println(e);
        }

        ig.addEdge("b", "c");

        int expected = 4;

        assertEquals(expected, ig.getAmountOfEdges());

    }

}
