package com.psy888;

import javax.swing.*;

public class UIThread extends Thread {

    private JFrame chatWindow;
    private JDialog loginDialog;
    private ConnectionThread ct;

    public UIThread(ConnectionThread ct) {
        this.ct = ct;
        this.chatWindow = new Chat(ct);
    }

    @Override
    public void run() {
        chatWindow.setVisible(true);
    }

    public void addMsgToChat(String msg) {
        ((Chat) chatWindow).addMsg(msg);
    }

    public void updateUsersList(String[] users){
        ((Chat) chatWindow).updateUsersList(users);
    }

}
