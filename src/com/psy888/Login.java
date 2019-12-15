package com.psy888;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class Login extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nameTextField;
    private JLabel errorMsgField;
    private ConnectionThread connectionThread;

    public Login(ConnectionThread ct) {
        this.connectionThread = ct;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
            String name = nameTextField.getName();
        boolean isOK = false;
        try {
            isOK = connectionThread.sendLoginRequest(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (isOK) {
                dispose();
            } else {
                errorMsgField.setText("Имя уже занято");
            }

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
        System.exit(0);
    }

//    public static void main(String[] args) {
//        Login dialog = new Login();
//        dialog.pack();
//        dialog.setVisible(true);
//        System.exit(0);
//    }
}
