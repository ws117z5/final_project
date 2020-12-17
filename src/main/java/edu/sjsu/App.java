package edu.sjsu;
import java.util.concurrent.*;
import javax.swing.*;


import edu.sjsu.Controllers.MainController;
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
    }
}
