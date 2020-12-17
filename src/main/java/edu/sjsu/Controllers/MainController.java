package edu.sjsu.Controllers;

import java.util.concurrent.ExecutionException;

import javax.swing.DefaultListModel;
import javax.swing.JTextField;

import edu.sjsu.*;
import edu.sjsu.Models.Message;
import edu.sjsu.Models.User;
import edu.sjsu.Views.*;

public class MainController {

    private static UsersController uc = null;
    private static MessageController mc = null;
    private static User user = null;

    
    /** 
     * @param port
     * @param name
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static void init(int port, String name) throws InterruptedException, ExecutionException {


        //DefaultListModel<JTextField>listModel = new DefaultListModel<>();
        
        RequestTypes.setPort(port);

        Server.startServer();

        MainController.user = new User("127.0.0.1", name);
        
        MainController.uc = new UsersController(name);
        MainController.mc = new MessageController(user);

        MainView.createAndShowGUI(uc, mc);

        RequestTypes.scanIps(uc, port);

        uc.setCurrentUser(uc.getUserByIndex(0));
        //mc.setUser(uc.getUserByIndex(0));


        mc.addItem(new Message(MainController.user, "test message"));

    }

    
    /** 
     * @return User
     */
    public static User getUser() {
        return MainController.user;
    }

    
    /** 
     * @return UsersController
     */
    public static UsersController getUsersController() {
        return uc;
    }

    
    /** 
     * @return MessageController
     */
    public static MessageController getMessageController() {
        return mc;
    }

    
    /** 
     * @param user
     * @return MessageController
     */
    public static MessageController getMessageController(User user) {
        return uc.getUserByItem(user).getMessageController();
    }

    
    /** 
     * @param uc
     */
    public static void setUsersController(UsersController uc) {
        MainController.uc = uc;
        //MainController.mc = uc.getUser;
    }

    
    /** 
     * @param mc
     */
    public static void setMessageController(MessageController mc) {
        MainController.mc = mc;
    }
}
