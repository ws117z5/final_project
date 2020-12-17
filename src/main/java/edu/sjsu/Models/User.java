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
     * ip, name constructor
     * @param ip String
     * @param name String
     */
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

    /**
     * ip, uuid, name constructor
     * @param ip String
     * @param uuid String
     * @param name String
     */
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

    
    /** 
     * sets a names
     * @param name String
     */
    public void setName(String name) { 
        this.name = name; 
        this.item.setText(name);
        //this.item.setHorizontalAlignment(JTextField.LEFT);
    }; 

    /**
     * returns an instance
     * @return User
     */
    public User getInstance() { return this; }


    /**
     * returns a message controller
     * @return MessageController
     */
    public MessageController getMessageController() { return this.mc; }

    /**
     * returns uuid
     * @return String
     */
    public String getUUID() { return this.uuid; }

    /**
     * returns user name
     * @return String
     */
    public String getName() {  return this.name; };

    /**
     * returns text field
     * @return JTextField
     */
    public JTextField getItem() {  return this.item; };

    /**
     * returns ip
     * @return String
     */
    public String getIp() { return ip; }

    /**
     * basic to string name
     * @return String
     */
    public String toString() { return name; }
}
