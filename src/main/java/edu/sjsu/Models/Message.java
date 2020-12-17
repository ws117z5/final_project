package edu.sjsu.Models;

import java.util.Date;

import javax.swing.JTextField;

public class Message implements LineItem {

    private User user = null;
    private Date time = null;
    private JTextField item = null;
    
    /**
     Constructs a user.
    @param description the description
    @param price the price
    */

    public Message(User user, String message)
    {
        this.time = new Date();
        this.user = user;
        this.item = new JTextField(message);
        this.item.setHorizontalAlignment(JTextField.LEFT);

    }

    public JTextField getItem() {
        return this.item;
    }

    public User getUser() {
        return this.user;
    }

    public Message getInstance() { return this; }

    public String toString() {
        return this.item.getText();
    }
}
