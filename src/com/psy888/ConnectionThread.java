package com.psy888;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ConnectionThread {
    private final static String ADDED = "ok";
    private final static String LOGIN = "-login";
    private final static String PRIVATE = "-p";
    private final static String HOST = "192.168.1.191";
    private final static int PORT = 8778;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private UI ui;
    private String userName;


    public ConnectionThread() throws IOException {
        socket = new Socket(HOST, PORT);
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream() , "Cp1252"));
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }


    public void sendMsg(String msg) throws IOException {
        //todo send msg
        bufferedWriter.write(msg + "\r");
        bufferedWriter.flush();
    }


    public void sendPrivateMsg(String recipient, String msg) throws IOException {
        //todo send msg
        bufferedWriter.write(PRIVATE + " " + userName + ": " + msg + "\r");
        bufferedWriter.flush();
    }

    public String sendLoginRequest(String name) throws IOException {
        //todo if ok set name
        bufferedWriter.write(LOGIN + " " + name + "\r");
        bufferedWriter.flush();
        String srvRespond = bufferedReader.readLine();
        if (srvRespond.contains(name)) {
            userName = name;

            return srvRespond;
        }
        return null;
    }

    public void startReceiveThr(UI ui, String userList) {
        this.ui = ui; // wtf
        ReceiveThread rt = new ReceiveThread(bufferedReader, this.ui);
        rt.setDaemon(true);
        rt.start();
        this.ui.updateUsersList(rt.getNames(userList));
        ui.setTitle(userName);
    }

    public void closeConnection() {
        try {
            bufferedWriter.close();
            bufferedReader.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Соединение разорвано, потоки закрыты");
            e.printStackTrace();
        }
    }
}
