package edu.sjsu.Controllers;

import java.util.*;

import javax.swing.JTextField;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import edu.sjsu.Models.LineItem;
import edu.sjsu.Models.User;
import edu.sjsu.Views.*;


/**
   A UserList representation.
*/
public class UsersController {
   private ArrayList<LineItem> users;
   private HashMap<String, User> usersByHash;
   private DefaultListModel<User> listModel;
   private ArrayList<ChangeListener> listeners;

   private User currentUser;

   /**
    * 
      Constructs users controller.
    * @param name String 
    */
   public UsersController(String name)
   {
      usersByHash = new HashMap<String, User>();
      currentUser = null;
      users = new ArrayList<>();
      listeners = new ArrayList<>();
   }

   
   /** 
    * returns current user
    * @return User
    */
   public User getCurrentUser() {
      return currentUser;
   }

   /**
    * changes current user
    * @param user sets user
    */
   public void setCurrentUser(User user) {
      this.currentUser = user;
   }

   /**
    * Sets list model for view
    * @param listModel a list of users
    */
   public void setListModel(DefaultListModel<User> listModel) {
      this.listModel = listModel;
   }
  /**
      Adds an item to the invoice.
      @param item the item to add
   */
   public void addItem(LineItem item)
   {
      //final JTextField textField = new JTextField(item.toString());

      User user = ((User) item);

      /*
      //textfield.requestFocus();
      //textfield.setEnabled(false);

      //textfield.setEditable( false );
      //textfield.getCaret().deinstall( textfield );


      textfield.addMouseListener(new MouseAdapter(){
   
         @Override
         public void mouseClicked(MouseEvent e){
               //textField.setText("");
               //todo open chat 
               System.out.println("Clicked!");
               System.out.println(e);
         }
      });
      

      textfield.addMouseListener(new MouseListener() {
         @Override
         public void mouseClicked(MouseEvent e) {
             System.out.println(e);
         }
         @Override
         public void mousePressed(MouseEvent e) {
            System.out.println(e);
         }
         @Override
         public void mouseReleased(MouseEvent e) {
            System.out.println(e);
         }
         @Override
         public void mouseEntered(MouseEvent e) {
            System.out.println(e);
         }
         @Override
         public void mouseExited(MouseEvent e) {
            System.out.println(e);
         }
      });

      FocusListener fl = new FocusAdapter()
      {
         @Override
         public void focusGained(FocusEvent e)
         {
            JTextField component = (JTextField)e.getSource();
            System.out.println(component);
            //component.setText("");
         }
      };
      textfield.addFocusListener( fl );
      //textfield.add( fl );
      */

      String uuid = user.getUUID();
      //TODO allow current for tests
      if(!usersByHash.containsKey(uuid)) {// && !currentUser.getUUID().equals(key)) {
         users.add(item);
         usersByHash.put(uuid, user);

   
         listModel.addElement(user);
         // Notify all observers of the change to the invoice
         ChangeEvent event = new ChangeEvent(this);
         for (ChangeListener listener : listeners)
            listener.stateChanged(event);
      }
      
   }

   /**
      @param listener the change listener to add
   */
   public void addChangeListener(ChangeListener listener)
   {
      listeners.add(listener);
   }

   /**
      Gets an iterator that iterates through the items.
      @return an iterator for the items
   */
   public Iterator<LineItem> getItems()
   {
      return new
         Iterator<LineItem>()
         {
            private int current = 0;

            public boolean hasNext()
            {
               return current < users.size();
            }

            public LineItem next()
            {
               return users.get(current++);
            }

            public void remove()
            {
               throw new UnsupportedOperationException();
            }
         };
   }

   
   /** 
    * @param idx int
    * @return User
    */
   public User getUserByIndex(int idx) {
      if(idx >= 0 && idx < users.size()) {
         return (User) users.get(idx);
      }
      return null;
   }

   
   /** 
    * @param uuid String
    * @return User
    */
   public User getUserByUUID(String uuid) {
      if(!usersByHash.containsKey(uuid)) {
         return usersByHash.get(uuid);
      }
      return null;
   }

   
   /** 
    * @param ip String
    * @return User
    */
   public User getUserByIp(String ip) {
      for(LineItem item: users) {
         if(item instanceof User) {
            User user = (User) item;
            if( ip.equals(user.getIp())) {
               return user;
            }
         }
      }

      return null;
   }

   
   /** 
    * @param i LineItem
    * @return User
    */
   public User getUserByItem(LineItem i) {
      for(LineItem item: users) {
         if(item instanceof User) {
            User user = (User) item;
            if( i.equals(user)) {
               return user;
            }
         }
      }

      return null;
   }

   
   /** 
    * @param name String
    * @return User
    */
   public User getUserByName(String name) {
      for(LineItem item: users) {
         if(item instanceof User) {
            User user = (User) item;
            if( name.equals(user.getName())) {
               return user;
            }
         }
      }

      return null;
   }

   /*
   public String format(ListFormatter formatter)
   {
      String r = formatter.formatHeader();
      Iterator<LineItem> iter = getItems();
      while (iter.hasNext())
         r += formatter.formatLineItem(iter.next());
      return r + formatter.formatFooter();
   }
   */

}
