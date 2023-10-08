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

}
