package edu.sjsu;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    /**
     * Testing if server works
     */
    @Test
    public void testServer() {
        Server.startServer();


        Socket Skt;
        String host = "localhost";
        int port = App.port;
        try {

            Skt = new Socket(host, port);
            Skt.close();
        } catch (UnknownHostException e) {
            fail("Exception occured"+ e.getMessage());
         } catch (IOException e) {

            fail("Exception occured"+ e.getMessage());
         }

         
         Server.killServer();

    }
    
}
