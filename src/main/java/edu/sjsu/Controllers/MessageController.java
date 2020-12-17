package edu.sjsu.Controllers;

import java.util.ArrayList;
import java.util.Iterator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import edu.sjsu.Models.*;
import edu.sjsu.Views.ListFormatter;
import edu.sjsu.Views.MessageFormatter;
import edu.sjsu.Views.MessageView;

public class MessageController {
   private ArrayList<LineItem> messages;
   private ArrayList<ChangeListener> listeners;
   private User currentUser;

   public MessageController(User user)
   {
      messages = new ArrayList<>();
      listeners = new ArrayList<>();
      currentUser = user;

      MainController.setMessageController(this);
      //this.updateMessages();
   }

   /**
    * Updates messages view
    */
   public void updateMessages() {
      ListFormatter<User> formatter = new MessageFormatter();
      MessageView.textArea.setText(format(formatter));
   }


   /**
    * 
    * @return messages list
    */
   public ArrayList<LineItem> getMessages() {
      return messages;
   }

   /**
    * 
    * @return listeners list
    */
   public ArrayList<ChangeListener> getListeners() {
      return listeners;
   }

   /**
      Adds an item to the invoice.
      @param item the item to add
   */
  public void addItem(LineItem item)
  {
     final JTextField textField = new JTextField(item.toString());

     textField.addMouseListener(new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent e){
              //textField.setText("");
              //todo open chat 
        }
     });

     messages.add(item);
     // Notify all observers of the change to the invoice
     ChangeEvent event = new ChangeEvent(this);
     for (ChangeListener listener : listeners)
        listener.stateChanged(event);
  }

  /**
     Adds a change listener to the invoice.
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
              return current < messages.size();
           }

           public LineItem next()
           {
              return messages.get(current++);
           }

           public void remove()
           {
              throw new UnsupportedOperationException();
           }
        };
  }

  /**
   * 
   * @param formatter a formatter for the list
   * @return String text for the container
   */
  public String format(ListFormatter<User> formatter)
  {
     String r = formatter.formatHeader(currentUser);
     Iterator<LineItem> iter = getItems();
     while (iter.hasNext())
        r += formatter.formatLineItem(iter.next());
     return r + formatter.formatFooter();
  }


}
