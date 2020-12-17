package edu.sjsu.Views;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class MessageTest {
    public static void createAndShowGUI() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        //Controller.init();

        Container pane = frame.getContentPane();


        //list = new JList(data)
        //add( list )
        //pane.add(compsToExperiment, BorderLayout.CENTER);
        //pane.add(controls, BorderLayout.SOUTH); ;

        final JTextField textField = new JTextField("Enter text here...");
        textField.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                textField.setText(textField.getText() + ": Clicked");
            }
        });

        frame.add(textField);
        frame.pack();
        frame.setVisible(true);
    }
}
