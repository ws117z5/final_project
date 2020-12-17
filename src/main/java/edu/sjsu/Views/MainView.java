package edu.sjsu.Views;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.*;
import javax.swing.event.*;

import edu.sjsu.RequestTypes;
import edu.sjsu.Controllers.*;

public class MainView {
    public static JFrame frame = new JFrame();

    /**
     * creates basic gui 
     * @param uc UsersController
     * @param mc MessageController
     */
    public static void createAndShowGUI(UsersController uc, MessageController mc) {

        UsersView.init(uc);
        MessageView.init(mc);

        JScrollPane userPanel = UsersView.getPanel();
        JScrollPane messagesPanel = MessageView.getPanel();

        JPanel chatPanel = new JPanel();
        chatPanel.setPreferredSize(new Dimension(550, 450));
        chatPanel.setSize(550, 450);

        // messagesBox.setBackground(new Color(30, 144, 255));
        // messagesBox.setBounds(0, 0, 450, 500);
        // messagesBox.setSize(450,500);

        JPanel inputBox = new JPanel();
        // inputBox.setPreferredSize(new Dimension(450, 50));
        inputBox.setSize(550, 50);

        JTextArea textArea = new JTextArea(3, 45);
        textArea.setEditable(true);
        JButton sendButton = new JButton("Send");
        // sendButton.setPreferredSize(new Dimension(60, 40));

        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = textArea.getText();
                // RequestTypes.request("message", ip, port, timeout)
                JOptionPane.showMessageDialog(null, message);

                ExecutorService ex = Executors.newSingleThreadExecutor();

                Future<Boolean> future = RequestTypes.sendMessage(ex, uc.getCurrentUser(), message);
                try {
                    future.get();
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (ExecutionException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });

        inputBox.add(textArea, BorderLayout.WEST);
        inputBox.add(sendButton, BorderLayout.EAST);

        chatPanel.add(messagesPanel,  BorderLayout.NORTH);
        chatPanel.add(inputBox,  BorderLayout.SOUTH);
        //panel.add(combo);
        //panel.add(addButton);

        // Add the text area and panel to the frame
        //JFrame frame = new JFrame();
        frame.add(userPanel,  BorderLayout.WEST);
        frame.add(chatPanel,  BorderLayout.CENTER);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setMinimumSize(new Dimension(800, 520));
        frame.pack();
        frame.setVisible(true);
    }
}
