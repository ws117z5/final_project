package edu.sjsu.Models;

import java.util.Date;

import javax.swing.JTextField;

public class Message implements LineItem {

    private User user = null;
    private Date time = null;
    private JTextField item = null;
    
    /**
     * Constructs a message.
     * @param user User
     * @param message String
     */
    public Message(User user, String message)
    {
        this.time = new Date();
        this.user = user;
        this.item = new JTextField(message);
        this.item.setHorizontalAlignment(JTextField.LEFT);

    }

    /**
     * returns text field object for view
     * @return JTextField
     */
    public JTextField getItem() {
        return this.item;
    }

    /**
     * returns user of the message
     * @return User
     */
    public User getUser() {
        return this.user;
    }

    /**
     * returns this
     * @return Message
     */
    public Message getInstance() { return this; }

    /**
     * toString
     * @return String
     */
    public String toString() {
        return this.item.getText();
    }
}
