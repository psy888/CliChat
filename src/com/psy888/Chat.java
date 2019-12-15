package com.psy888;

import javax.swing.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Chat extends JFrame {

    private JList clientsList;
    private JButton btnSend;
    private JTextField msgInput;
    private JTextPane chatField;
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private ConnectionThread ct;
    private JDialog login;
    private UI ui;


    {
        setContentPane(mainPanel);
        setSize(800, 600);
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setListeners();
        getRootPane().setDefaultButton(btnSend);
    }

    public Chat(ConnectionThread ct, UI ui) {
        this.ct = ct;
        this.ui = ui;
        login = new Login(ct, ui);
    }

    private void setListeners() {
        btnSend.addActionListener(e -> {
            try {
//                System.out.println(System.getProperty("file.encoding"));
                ct.sendMsg(new String(StandardCharsets.UTF_8.encode(msgInput.getText()).array()));
                msgInput.setText("");
            } catch (IOException ex) {
                addMsg("ERROR: сообщение не отправлено");
            }
        });




        clientsList.addListSelectionListener(e -> {
            String recipientName = (String) clientsList.getSelectedValue();
            if(recipientName!=null) {
                msgInput.setText("-p " + recipientName + ":" + msgInput.getText());
//                ct.sendPrivateMsg(recipientName, msgInput.getText());
                clientsList.clearSelection();
                msgInput.requestFocus();
            }
        });


    }

    public void addMsg(String msg) {
        chatField.setText(chatField.getText() + "\n" + msg);
        chatField.setCaretPosition(chatField.getText().trim().length());
//        scrollPane.setViewportView(chatField);
    }


    public void updateUsersList(String[] users) {
        DefaultListModel<String> list = new DefaultListModel();
        for (int i = 0; i < users.length; i++) {
            list.addElement(users[i]);
        }
        clientsList.setModel(list);
    }


    public void logIn(){
        login.setVisible(true);
    }
}
