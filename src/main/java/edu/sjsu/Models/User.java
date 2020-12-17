package edu.sjsu.Models;

import java.util.UUID;
import java.util.function.Predicate;

import javax.swing.JTextField;

import edu.sjsu.Controllers.MessageController;

public class User implements LineItem {

    private String name = "undefined";
    private String ip = "127.0.0.1";
    private String uuid;

    private JTextField item = null;
    private boolean online = false;
    private MessageController mc = null;
    
    /**
     Constructs a user.
    @param description the description
    @param price the price
    */

    public User(String ip)
    {
        this.name = "undefined";
        this.ip = ip;
        this.item = new JTextField("undefined user");
        this.item.setHorizontalAlignment(JTextField.LEFT);
        this.online = true;
        this.uuid = UUID.randomUUID().toString();
        this.mc = new MessageController(this);
    }

    public User(String ip, String name)
    {
        this.name = name;
        this.ip = ip;
        this.item = new JTextField(name);
        this.item.setHorizontalAlignment(JTextField.LEFT);
        this.online = true;
        this.uuid = UUID.randomUUID().toString();
        this.mc = new MessageController(this);
    }

    public User(String ip, String uuid, String name)
    {
        this.uuid = uuid;
        this.name = name;
        this.ip = ip;
        this.item = new JTextField(name);
        this.item.setHorizontalAlignment(JTextField.LEFT);
        this.online = true;
        this.mc = new MessageController(this);
    }

    public void setName(String name) { 
        this.name = name; 
        this.item.setText(name);
        //this.item.setHorizontalAlignment(JTextField.LEFT);
    }; 

    public User getInstance() { return this; }
    public MessageController getMessageController() { return this.mc; }
    public String getUUID() { return this.uuid; }
    public String getName() {  return this.name; };
    public JTextField getItem() {  return this.item; };
    public String getIp() { return ip; }
    public String toString() { return name; }
}
