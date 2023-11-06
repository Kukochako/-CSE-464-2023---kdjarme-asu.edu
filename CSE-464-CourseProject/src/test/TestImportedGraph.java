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

        try {
            ig.parseGraph("src/dot/sample.dot");
        }
        catch(IOException e){
            System.out.println(e);
        }

        System.out.println("~~READY TO TEST~~");
    }

    @After
    public void teardown(){
        System.out.println("~~CLEANING~~\n");
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Feature 1 Tests~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @Test
    //tests Parse Graph functionality
    public void testParseGraph() {

        try {

            ImportedGraph sampleIG = new ImportedGraph();
            assertTrue(sampleIG.parseGraph("src/dot/sample.dot"));

        }
        catch(IOException e){
            System.out.println(e);
        }

    }

    @Test
    //tests Parse Graph functionality when a bad filepath is passed
    public void testParseGraphFalse() {

        try {

            ImportedGraph sampleIG = new ImportedGraph();
            assertFalse(sampleIG.parseGraph("NOT A REAL LOCATION"));

        }
        catch(IOException e){
            System.out.println(e);
        }

    }

    @Test
    //tests toString functionality
    public void testToString() throws IOException {

        String result = ig.toString(); //captures the value produced after toString is ran

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
    //tests outputGraph functionality
    public void testOutputGraph() throws IOException {

        try {
            ig.outputGraph("src/outputs/outputOG.txt");
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

    @Test
    //tests outputGraph functionality when a bad filepath is passed in
    public void testOutputGraphFalse() throws IOException {

        try {
            assertFalse(ig.outputGraph("/BADPATH"));
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Feature 2 Tests~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @Test
    // Add a new valid vertex to the graph
    public void testAddNodeValid(){

        String result = ig.addNode("e");
        String expected = "e successfully added!";

        assertEquals(expected, result);

    }

    @Test
    // Attempt to add an invalid vertex to the graph
    public void testAddNodeInvalid(){

        String result = ig.addNode("d");
        String expected = "Failed to add! Vertex d already exists!";

        assertEquals(expected, result);

    }

    @Test
    //Attempts to add a list of completely new vertices to the graph
    public void testAddNodesValid(){

        String[] newNodes = {"e","f","g","h","i"};
        ig.addNodes(newNodes);

        int expected = 9;

        assertEquals(expected, ig.getAmountOfVertices());

    }

    @Test
    //Attempts to add a list of some new and some old vertices to the graph
    public void testAddNodesSemiValid(){

        String[] newNodes = {"e","a","g","b","i"};
        ig.addNodes(newNodes);

        int expected = 7;

        assertEquals(expected, ig.getAmountOfVertices());

    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Feature 3 Tests~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    //adds a new edge to the graph
    public void testAddEdgeValid(){

        ig.addEdge("d", "b");

        int expected = 5;

        assertEquals(expected, ig.getAmountOfEdges());

    }

    @Test
    //adds an existing edge to the graph
    public void testAddEdgeInValid(){

        ig.addEdge("b", "c");

        int expected = 4;

        assertEquals(expected, ig.getAmountOfEdges());

    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Feature 4 Tests~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    //outputs imported graph into DOT File format
    public void testOutputDOTGraph() throws IOException {

        ig.outputDOTGraph("src/outputs/outputODG.dot");

        ImportedGraph resultIG = new ImportedGraph();

        try {
            resultIG.parseGraph("src/outputs/outputODG.dot");
        }
        catch(IOException e){
            System.out.println(e);
        }

        //All vertices and edges of the graphs must be the same
        assertEquals(ig.getAmountOfEdges(), resultIG.getAmountOfEdges());
        assertEquals(ig.getAmountOfVertices(), resultIG.getAmountOfVertices());

    }

    @Test
    //tests outputDotGraph if a bad filepath is entered
    public void testOutputDOTGraphFalse() {

        assertFalse(ig.outputDOTGraph("NOREAL"));

    }

    @Test
    //tests the output to image file feature for the imported graph
    public void testOutputGraphics() throws IOException {

        try {
            assertTrue(ig.outputGraphics("src/outputs/", "PNG"));
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    @Test
    //tests the output to image file feature for the imported graph if a bad filepath is entered
    public void testOutputGraphicsFalsePath() throws IOException {

        try {
            assertFalse(ig.outputGraphics("/NOTREAL", "PNG"));
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    @Test
    //tests the output to image file feature for the imported graph if a bad filepath is entered
    public void testOutputGraphicsFalseFormat() throws IOException {

        try {
            assertFalse(ig.outputGraphics("src/outputs/outputOutputGraphics", "BAD"));
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~PART 2 Tests~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Feature 1 Tests~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    // removes a new valid vertex to the graph
    public void testRemoveNodeValid(){

        String result = ig.removeNode("a");
        String expected = "a successfully removed!";

        assertEquals(expected, result);

    }

    @Test
    // Attempt to add an invalid vertex to the graph
    public void testRemoveNodeInvalid(){

        String result = ig.removeNode("q");
        String expected = "Failed to remove! Vertex q does not exist!";

        assertEquals(expected, result);

    }

    @Test
    //Attempts to remove a list of old vertices to the graph
    public void testRemoveNodesValid(){

        String[] newNodes = {"a","b","c","d"};
        ig.removeNodes(newNodes);

        int expected = 0;

        assertEquals(expected, ig.getAmountOfVertices());

    }

    @Test
    //Attempts to remove a list of some new and some old vertices to the graph
    public void testRemoveNodesSemiValid(){

        String[] newNodes = {"e","a","g","b","i"};
        ig.removeNodes(newNodes);

        int expected = 2;

        assertEquals(expected, ig.getAmountOfVertices());

    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Feature 3 Tests~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Test
    //adds a new edge to the graph
    public void testRemoveEdgeValid(){

        ig.removeEdge("b", "c");

        int expected = 3;

        assertEquals(expected, ig.getAmountOfEdges());

    }

    @Test
    //adds an existing edge to the graph
    public void testRemoveEdgeInValid(){

        ig.removeEdge("a", "q");

        int expected = 4;

        assertEquals(expected, ig.getAmountOfEdges());


    }

    //~~~~~~~~~~~~~~~Feature 2: DFS search~~~~~~~~~~~~~~//

    //tests DFS method using a graph in a straight line
    @Test
    public void testDFSStraight(){

        //System.out.println(p);

        String expected = "a -> b -> c -> d";

        assertEquals(expected, ig.GraphSearch("a", "d").toString());
    }

    //tests DFS search where only one path is valid
    @Test
    public void testDFSOneValidPath(){

        ig.addNode("e");
        ig.addNode("f");

        ig.addEdge("a", "e");
        ig.addEdge("e", "f");

        String expected = "a -> e -> f";

        assertEquals(expected, ig.GraphSearch("a", "f").toString());
    }

    //Tests DFS search when source node is the same as the destination node
    @Test
    public void testDFSImmediate(){

        String expected = "a";

        assertEquals(expected, ig.GraphSearch("a", "a").toString());
    }

    //Test DFS method searching from the middle
    @Test
    public void testDFSMiddlePath(){

        ig.addNode("e");
        ig.addNode("f");

        ig.addEdge("a", "e");
        ig.addEdge("e", "f");

        String expected = "e -> f";

        assertEquals(expected, ig.GraphSearch("e", "f").toString());
    }

    //Tests DFS search when source node cannot reach destination node
    @Test
    public void testDFSNoPath(){

        assertNull(ig.GraphSearch("a", "f"));

    }

}
