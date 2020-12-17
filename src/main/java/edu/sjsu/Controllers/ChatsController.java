package edu.sjsu.Controllers;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.event.ChangeListener;

import edu.sjsu.Models.LineItem;
import edu.sjsu.Models.MessageStorageEl;
import edu.sjsu.Models.User;
import edu.sjsu.Views.MessageView;


public class ChatsController {
    public static HashMap<User, MessageStorageEl> chats = new HashMap<User, MessageStorageEl>();

    ChatsController()
    {
        ChatsController.chats = new HashMap<User, MessageStorageEl>();
    }

    
    /** 
     * Sets a chat
     * @param uuid String
     */
    public static void setChat(String uuid) {
        if(chats.containsKey(uuid)) {
            //MessageView.init(chats.get(uuid));
        }
    }

    
    /** 
     * prepares a storage
     * @param user User
     * @param messages ArrayList of LineItem
     * @param listeners ArrayList of ChangeListener
     */
    public static void addMessageStorage(User user, ArrayList<LineItem> messages, ArrayList<ChangeListener> listeners) {
        MessageStorageEl storage = new MessageStorageEl(messages, listeners);
        ChatsController.chats.put(user, storage);
    }

    
    /** 
     * adds a storage container
     * @param mc MessageController 
     */
    public static void addMessageStorage(MessageController mc) {
        MessageStorageEl storage = new MessageStorageEl(mc.getMessages(), mc.getListeners());
        //ChatsController.chats.put(mc.getUser(), storage);
    }

    
    /** 
     * returns a sorage container
     * @param user User
     * @return MessageStorageEl
     */
    public static MessageStorageEl getMessageStorage(User user) {
        return ChatsController.chats.get(user);
    }

    
    /** 
     * checks if storage exists
     * @param user User
     * @return boolean
     */
    public static boolean exists(User user) {
        return ChatsController.chats.containsKey(user);
    } 


}
