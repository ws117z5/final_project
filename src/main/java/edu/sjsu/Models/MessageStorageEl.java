package edu.sjsu.Models;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;

public class MessageStorageEl {
    private ArrayList<LineItem> messages;
    private ArrayList<ChangeListener> listeners;

    public MessageStorageEl(ArrayList<LineItem> messages, ArrayList<ChangeListener> listeners) {
        this.messages = messages;
        this.listeners = listeners;
    }

    public ArrayList<LineItem> getMessages() {
        return this.messages;
    }

    public ArrayList<ChangeListener> getListeners() {
        return this.listeners;
    }
}