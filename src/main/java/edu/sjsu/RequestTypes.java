package edu.sjsu;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.awt.TextField;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JTextField;

import edu.sjsu.Controllers.MainController;
import edu.sjsu.Controllers.UsersController;
import edu.sjsu.Models.User;

//import edu.sjsu.UI.*;

public class RequestTypes {

    static ExecutorService es = Executors.newFixedThreadPool(20);
    static int port;
    static int defaultTimeout = 100;
   
/**
     * Future that checks if a network device port is opened
     * 
     * @param es      ExecutorService
     * @param ip      String
     * @param port    int
     * @param timeout int ms
     * @return Future<Boolean>
     */
    public static Future<Boolean> portIsOpen(final String ip) {
        return es.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() {
                try {
                    Socket socket = getSocket(ip);
                    socket.close();
                    return true;
                } catch (IOException ignored) {
                    return false;
                }
            }
        });
    }

    
    /** 
     * @param user
     * @return Socket
     * @throws IOException
     */
    public static Socket getSocket(User user) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(user.getIp(), port), defaultTimeout);

        return socket;
    }
    
    
    /** 
     * @param ip
     * @return Socket
     * @throws IOException
     */
    public static Socket getSocket(String ip) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(ip, port), defaultTimeout);

        return socket;
    }
    
    /** 
     * @param port
     */
    public static void setPort(int port) {
        RequestTypes.port = port;
    }

    
    /** 
     * @param es
     * @param user
     * @param message
     * @return Future<Boolean>
     */
    public static Future<Boolean> sendMessage (ExecutorService es, User user, String message) {
        return es.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() {
                String response = "";
                try {

                    Socket socket = getSocket(user);
                    //TODO ChatController->getRemoteUser 

                    DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
                    DataInputStream dIn = new DataInputStream(socket.getInputStream());

                    // Send the second message
                    dOut.writeByte(2);
                    dOut.writeUTF(user.getUUID());
                    dOut.writeUTF(message);
                    dOut.flush(); // Send off the data

                    // Send the exit message
                    dOut.writeByte(-1);
                    dOut.flush();

                    dOut.close();
                    dIn.close();

                } catch(IOException e) {
                    System.out.println(e.getMessage());
                    return false;
                } finally {

                }

                return true;
            }
        });
    }

    
    /** 
     * @param ip
     * @return Future<String[]>
     */
    public static Future<String[]> resolveClient (String ip) {
        return es.submit(new Callable<String[]>() {
            @Override
            public String[] call() {
                String[] response = new String[2];
                try {

                    Socket socket = new Socket();
                    socket.connect(new InetSocketAddress(ip, port), defaultTimeout);
                    //todo ChatController->getRemoteUser 
                    //socket.connect(new InetSocketAddress(ip, port), defaultTimeout);

                    DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
                    DataInputStream dIn = new DataInputStream(socket.getInputStream());

                    // Send first message
                    
                    dOut.writeByte(1);
                    response[0] = dIn.readUTF();
                    response[1] = dIn.readUTF();
                    dOut.flush(); // Send off the data

                    // Send the exit message
                    dOut.writeByte(-1);
                    dOut.flush();

                    dOut.close();
                    dIn.close();
                    socket.close();

                } catch(IOException e) {
                    System.out.println(e.getMessage());
                } finally {

                }

                return response;
            }
        });
    }

    
    /** 
     * @param request
     * @param ip
     * @param port
     * @param timeout
     * @return Future<String>
     */
    public static Future<String> request(final String request, final String ip, final int port, final int timeout) {
        return es.submit(new Callable<String>() {
            @Override
            public String call() {
                String response = "";
                try {
                    Socket socket = new Socket();
                    socket.connect(new InetSocketAddress(ip, port), timeout);

                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    String inputLine;

                    out.println(request);
                    inputLine = in.readLine();
                    if(inputLine != null) {
                        response = inputLine;
                    }
                    
                    out.close();
                    in.close();
                    socket.close();

                    return response;
                } catch (Exception ex) {
                    return response;
                }
            }
        });
    }


    /**
     * 
     */
    public static void scanIps(UsersController usersController, int port) throws InterruptedException, ExecutionException {
        try {
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();

            HashMap<String, Future<Boolean>> ips = new HashMap<>();
            HashMap<String, Future<String[]>> ipNames = new HashMap<>();

            HashMap<String, String> resolvedNames = new HashMap<>();


            final int timeout = 200;
            
            while (nis.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) nis.nextElement();
                Enumeration ias = ni.getInetAddresses();

                while (ias.hasMoreElements()) {
                    InetAddress ia = (InetAddress) ias.nextElement();

                    if (ia instanceof Inet4Address) {
                        final String ip = ia.getHostAddress();
    
                        String sip = ip.substring(0, ip.indexOf('.', ip.indexOf('.', ip.indexOf('.') + 1) + 1) + 1);
                        if(sip.equals("127.0.0.") || sip.equals("10.0.0.")) {
                            continue;
                        }

                        for (int it = 1; it <= 255; it++) {
                            String ipToTest = sip + it;
                            ips.put(ipToTest, RequestTypes.portIsOpen(ipToTest));
                        }
                    }

                }

            }

            es.shutdown();
            es = Executors.newFixedThreadPool(20);

            for (Map.Entry<String, Future<Boolean>> e : ips.entrySet()) {
                if(e.getValue().get()) {
                    System.out.println(e.getKey() + " port opened");
                    ipNames.put(e.getKey(), RequestTypes.resolveClient(e.getKey()));
                }
            }

            es.shutdown();

            for (Map.Entry<String, Future<String[]>> e : ipNames.entrySet()) {
                if(e.getValue().get()[0] != "") {
                    System.out.println(e.getKey() + " name resolved online");
                    resolvedNames.put(e.getKey(), e.getValue().get()[1]);

                    String[] info = e.getValue().get();

                    if(info.length < 2) {
                        continue;
                    }

                    User user = new User(e.getKey(), info[0], info[1]);
                    usersController.addItem(user);
                }
            }
        } catch (SocketException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     
}