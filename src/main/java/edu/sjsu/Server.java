package edu.sjsu;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.sjsu.Models.*;
import edu.sjsu.Controllers.*;

/**
 * Client connection handler
 */
class ClientTask implements Runnable {
    private final Socket clientSocket;

    public ClientTask(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        System.out.println("Got a client !");

        try {
            DataInputStream dIn = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dOut = new DataOutputStream(clientSocket.getOutputStream());

            boolean done = false;
            while(!done) {

                try {
                    byte messageType = dIn.readByte();

                    switch(messageType) {
                        case 1: // Type A
                            User currentUser = MainController.getUser();
                            dOut.writeUTF(currentUser.getUUID());
                            dOut.writeUTF(currentUser.getName());
                            //System.out.println("Message A: " + dIn.readUTF());
                            break;
                        case 2: // Type B
                            String uuid = dIn.readUTF();
                            String message = dIn.readUTF();
                            User user = MainController.getUsersController().getUserByUUID(uuid);
                            MainController.getMessageController().addItem(new Message(user, message));
                            //System.out.println("Message B: " + dIn.readUTF());
                            break;
                        case 3: // Type C
                            /*
                            System.out.println("Message C [1]: " + dIn.readUTF());
                            System.out.println("Message C [2]: " + dIn.readUTF());
                            */
                            break;
                        default:
                            done = true;
                    }
                } catch(EOFException e) {
                    done = true;
                    continue;
                }

                
        } 



        dIn.close();
        dOut.close();
        clientSocket.close();

            /*
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // Do whatever required to process the client's request

            String inputLine, outputLine;

            // Initiate conversation with client
            //KnockKnockProtocol kkp = new KnockKnockProtocol();
            //outputLine = kkp.processInput(null);
            //out.println("Connected");

            while ((inputLine = in.readLine()) != null) {
                if (inputLine.equalsIgnoreCase("id")) {
                    User currentUser = UsersController.getCurrentUser();
                    out.println(currentUser.getUUID() + "|" + currentUser.getName());
                } else {
                    String[] data = inputLine.split("\\|", 0);

                    if(data[0] == "msg") {
                        
                    }
                }
                if(inputLine.) {

                }
                //outputLine = kkp.processInput(inputLine);
                //out.println(outputLine);
                //if (outputLine.equals("Bye."))
                //    break;
            }
            out.close();
            in.close();
            clientSocket.close();

        */
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}

public class Server {
    public static int port = 42069;
    public static Thread serverThread = null;
    public static final ExecutorService clientProcessingPool = Executors.newFixedThreadPool(10);

    /**
     * Starts a server in
     */
    public static void startServer() {

        Runnable serverTask = new Runnable() {
            @Override
            public void run() {
                ServerSocket serverSocket = null;
                try {
                    serverSocket = new ServerSocket(port);
                    System.out.println("Waiting for clients to connect...");
                    while (true) {
                        Socket clientSocket = serverSocket.accept();
                        Server.clientProcessingPool.submit(new ClientTask(clientSocket));
                    }
                } catch (IOException e) {
                    System.err.println("Unable to process client request");
                    e.printStackTrace();
                } finally {
                    if(serverSocket != null && !serverSocket.isClosed()) {
                        try {
                            serverSocket.close();
                        } catch(IOException e) {
                            System.out.println(e.getMessage());
                        }
                        
                    }
                }
            }
        };
        Server.serverThread = new Thread(serverTask);
        serverThread.start();
    }

    /**
     * Stops the server thread
     */
    public static void killServer() {
        serverThread.interrupt();
    }
}
