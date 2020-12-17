package edu.sjsu;

import java.awt.Dimension;
import java.io.*;
import java.net.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.Port;
import javax.swing.*;

import java.net.*;
import java.io.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import edu.sjsu.Controllers.MainController;
import edu.sjsu.Views.MainView;
 
class KnockKnockProtocol {
    private static final int WAITING = 0;
    private static final int SENTKNOCKKNOCK = 1;
    private static final int SENTCLUE = 2;
    private static final int ANOTHER = 3;
 
    private static final int NUMJOKES = 5;
 
    private int state = WAITING;
    private int currentJoke = 0;
 
    private String[] clues = { "Turnip", "Little Old Lady", "Atch", "Who", "Who" };
    private String[] answers = { "Turnip the heat, it's cold in here!",
                                 "I didn't know you could yodel!",
                                 "Bless you!",
                                 "Is there an owl in here?",
                                 "Is there an echo in here?" };
 
    public String processInput(String theInput) {
        String theOutput = null;
 
        if (state == WAITING) {
            theOutput = "Knock! Knock!";
            state = SENTKNOCKKNOCK;
        } else if (state == SENTKNOCKKNOCK) {
            if (theInput.equalsIgnoreCase("Who's there?")) {
                theOutput = clues[currentJoke];
                state = SENTCLUE;
            } else {
                theOutput = "You're supposed to say \"Who's there?\"! " +
                "Try again. Knock! Knock!";
            }
        } else if (state == SENTCLUE) {
            if (theInput.equalsIgnoreCase(clues[currentJoke] + " who?")) {
                theOutput = answers[currentJoke] + " Want another? (y/n)";
                state = ANOTHER;
            } else {
                theOutput = "You're supposed to say \"" + 
                clues[currentJoke] + 
                " who?\"" + 
                "! Try again. Knock! Knock!";
                state = SENTKNOCKKNOCK;
            }
        } else if (state == ANOTHER) {
            if (theInput.equalsIgnoreCase("y")) {
                theOutput = "Knock! Knock!";
                if (currentJoke == (NUMJOKES - 1))
                    currentJoke = 0;
                else
                    currentJoke++;
                state = SENTKNOCKKNOCK;
            } else {
                theOutput = "Bye.";
                state = WAITING;
            }
        }
        return theOutput;
    }
}


/**
 * Hello world!
 *
 */
public class App {

    public static int port = 42069;
    public static void main(String[] args) {
        // System.out.println("name : Vladimir Koroteev");
        // System.out.println("git : https://github.com/ws117z5/151_repo1");


        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {

                try {
                    String name = JOptionPane.showInputDialog("enter your name");

                    MainController.init(port, name);
                } catch (InterruptedException e) {
                    System.out.println("thread interupted: " + e.getMessage());
                } catch (ExecutionException e) {
                    System.out.println("Exec failed: " + e.getMessage());
                } catch (RejectedExecutionException e) {
                    System.out.println("Exec failed: " + e.getMessage());
                } finally {
                    System.out.println("Server socket opened, ips scanned");
                }
            }
        });

        

        
            
        //final JFrame frame = new JFrame ( " Multicolored cube" );
        //frame.getContentPane().add( list );
        //frame.getContentPane().add( glcanvas );
        //frame.setSize( frame.getContentPane().getPreferredSize() );
        //frame.setVisible( true );
    }
}
