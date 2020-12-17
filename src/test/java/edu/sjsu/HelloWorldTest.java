package edu.sjsu;

//import static org.junit.Assert.assertTrue;
import org.junit.Test;
import static org.junit.Assert.*;



public class HelloWorldTest {


private static final double DELTA = 1e-15;

    @Test
    public void testOne() {
        assertEquals(0.0, 0.0, DELTA);
    }
}