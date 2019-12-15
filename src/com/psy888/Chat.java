package com.psy888;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

public class Chat extends JFrame {

    private JList clientsList;
    private JButton btnSend;
    private JTextField msgInput;
    private JTextPane chatField;
    private JPanel mainPanel;
    private ConnectionThread ct;
    private JDialog login;


    {
        setSize(800, 600);
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setListeners();
    }

    public Chat(ConnectionThread ct) {
        this.ct = ct;
        login = new Login(ct);
        login.setVisible(true);
    }

    private void setListeners() {
        btnSend.addActionListener(e -> {
            try {
                ct.sendMsg(msgInput.getText());
                msgInput.setText("");
            } catch (IOException ex) {
                addMsg("ERROR: сообщение не отправлено");
            }
        });

        clientsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String recipientName = (String) clientsList.getSelectedValue();
                msgInput.setText("-p " + recipientName + ":" + msgInput.getText());
//                ct.sendPrivateMsg(recipientName, msgInput.getText());
                clientsList.clearSelection();
            }
        });


    }

    public void addMsg(String msg) {
        chatField.setText(chatField.getText() + "\n" + msg);
    }


    public void updateUsersList(String[] users) {
        DefaultListModel<String> list = new DefaultListModel();
        for (int i = 0; i < users.length; i++) {
            list.addElement(users[i]);
        }
        clientsList.setModel(list);
    }
}
