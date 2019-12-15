package com.psy888;

import javax.swing.*;

public class UI {

    private JFrame chatWindow;
    private ConnectionThread ct;

    public UI(ConnectionThread ct) {
        this.ct = ct;
        this.chatWindow = new Chat(ct, this);
    }

    public void setChatWindowVisible() {
        chatWindow.setVisible(true);
    }

    public void addMsgToChat(String msg) {
        Chat chat =  (Chat) chatWindow;
        chat.addMsg(msg);
    }

    public void updateUsersList(String[] users){
        ((Chat) chatWindow).updateUsersList(users);
    }

    public void login() {
        ((Chat) chatWindow).logIn();
    }

    public void setTitle(String userName) {
        ((Chat) chatWindow).setTitle(userName);
    }
}
