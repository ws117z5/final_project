package edu.sjsu.Views;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import edu.sjsu.Controllers.*;
import edu.sjsu.Models.*;


public class MessageView {
    public static JScrollPane messagePanel = null;
    public static JTextArea textArea = null;
    //public static 
    public static JList list = null; //data has type Object[]

    /**
     * inits a message view gui
     * @param mc MessageController
     */
    public static void init(final MessageController mc) {

        ListFormatter formatter = new MessageFormatter();

        MessageView.textArea = new JTextArea(450, 45);
        MessageView.textArea.setEditable(false);


        mc.addChangeListener(event -> {
            MessageView.textArea.setText(mc.format(formatter));
        });

        MessageView.messagePanel = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //textArea.add(comp);
        MessageView.messagePanel.setPreferredSize(new Dimension(550, 450));
    }

    /**
     * returns a scroll panel gui element
     * @return JScrollPane
     */
    synchronized static JScrollPane getPanel() {
        return MessageView.messagePanel;
    }

    /**
     * message click handler
     * @param e MouseEvent
     * @return MouseListener
     */
    public static MouseListener messageClicked(MouseEvent e) {
        return new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                System.out.println(e);
            }
        
            public void mousePressed(MouseEvent e) {
        
            }
        
            public void mouseReleased(MouseEvent e) {
        
            }
        
            public void mouseEntered(MouseEvent e) {
        
            }
        
            public void mouseExited(MouseEvent e) {
        
            }
        };

        

    }
}
