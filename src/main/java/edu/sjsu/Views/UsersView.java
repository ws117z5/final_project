package edu.sjsu.Views;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import edu.sjsu.Models.*;
import edu.sjsu.Views.*;
import edu.sjsu.Controllers.*;


public class UsersView {
    public static DefaultListModel<User> listModel = null;
    public static JScrollPane userPanel = null;
    //public static JTextArea textArea = null;
    //public static 
    public static JList<User> list = null; //data has type Object[]

    public static void init(final UsersController uc) {

        listModel = new DefaultListModel<>();
        list = new JList<>(listModel); //data has type Object[]

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);


        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
               if (me.getClickCount() == 1) {
                  JList target = (JList)me.getSource();
                  int index = target.locationToIndex(me.getPoint());
                  if (index >= 0) {
                    User clickedUser = (User) target.getModel().getElementAt(index);

                    uc.setCurrentUser(clickedUser);
                    MainController.setMessageController(uc.getCurrentUser().getMessageController());

                    ListFormatter<User> formatter = new MessageFormatter();
                    MessageView.textArea.setText(MainController.getMessageController().format(formatter));
                    
                    /*
                    if(ChatsController.exists(clickedUser)) {
                        MainController.setUsersController(uc);
                        //MessageStorageEl ms = ChatsController.getMessageStorage(clickedUser);
                        //mc.updateMessages(clickedUser, ms.getMessages(), ms.getListeners());
                    } else {
                        JOptionPane.showMessageDialog(null, "error, message controller wasn't initiated");
                    }
                    */

                     //JOptionPane.showMessageDialog(null, clickedUser.toString());
                  }
               }
            }
         });

        userPanel = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        userPanel.setPreferredSize(new Dimension(250, 500));


        /*
        ListFormatter formatter = new UserFormatter();

        UsersView.textArea = new JTextArea(20, 40);
        UsersView.textArea.setEditable(false);

        uc.addChangeListener(event -> {
            UsersView.textArea.setText(uc.format(formatter));
        });
        */

        uc.setListModel(listModel);
        uc.addItem(new User("127.0.0.1", "test user"));

        //userPanel = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //textArea.add(comp)
        //userPanel.setPreferredSize(new Dimension(250, 500));
    }

    public static JScrollPane getPanel() {
        return UsersView.userPanel;
    }

}
