package edu.sjsu.Models;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;

public class MessageStorageEl {
    private ArrayList<LineItem> messages;
    private ArrayList<ChangeListener> listeners;

    /**
     * creates a storage element
     * @param messages ArrayList of LineItem
     * @param listeners ArrayList of ChangeListener
     */
    public MessageStorageEl(ArrayList<LineItem> messages, ArrayList<ChangeListener> listeners) {
        this.messages = messages;
        this.listeners = listeners;
    }

    
    /** 
     * returns message list
     * @return ArrayList of LineItem
     */
    public ArrayList<LineItem> getMessages() {
        return this.messages;
    }

    
    /** 
     * returns listeners list
     * @return ArrayList of ChangeListener
     */
    public ArrayList<ChangeListener> getListeners() {
        return this.listeners;
    }
}