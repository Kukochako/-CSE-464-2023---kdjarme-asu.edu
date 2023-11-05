import org.apache.commons.io.FileUtils;
import org.junit.*;

import static org.junit.Assert.*;

public class TestPath {

    Path p; //Local path used for testing

    @Before
    public void setUp(){

        p = new Path();

        p.addNode("a");
        p.addNode("b");

        System.out.println("~~READY TO TEST~~");
    }

    @After
    public void teardown(){
        System.out.println("~~CLEANING~~\n");
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~TESTS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    //checks to see if nodes can be properly added to the path
    @Test
    public void testAddNode(){

        p.addNode("c");
        p.addNode("d");

        int expected = 4;

        assertEquals(expected, p.getSize());

    }

    //checks to see if nodes can be properly removed from the path
    @Test
    public void testRemoveNode(){

        p.removeNode("a");

        int expected = 1;

        assertEquals(expected, p.getSize());

    }

    //checks to see if path can be properly printed out
    @Test
    public void testToString(){

        String expected = "a -> b";

        assertEquals(expected, p.toString());

    }

}
