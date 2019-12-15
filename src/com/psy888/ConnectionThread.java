package com.psy888;

import java.io.*;
import java.net.Socket;

public class ConnectionThread extends Thread {
    private final static String ADDED = "ok";
    private final static String LOGIN = "-login";
    private final static String PRIVATE = "-p";
    private final static String HOST = "192.168.1.191";
    private final static int PORT = 8778;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private UIThread ui;
    private String userName;


    @Override
    public void run() {
        try {
            socket = new Socket(HOST, PORT);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            while (true){

            }

        } catch (IOException io) {
            io.printStackTrace();
        }finally {
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


    public void sendMsg(String msg) throws IOException {
        //todo send msg
        bufferedWriter.write( msg + "\r");
        bufferedWriter.flush();
    }


    public void sendPrivateMsg(String recipient, String msg) throws IOException {
        //todo send msg
        bufferedWriter.write(PRIVATE + " " + userName + ": " + msg + "\r");
        bufferedWriter.flush();
    }

    public boolean sendLoginRequest(String name) throws IOException {
        //todo if ok set name
        bufferedWriter.write(LOGIN + " " + name + "\r");
        bufferedWriter.flush();
        String srvRespond = bufferedReader.readLine();
        if (srvRespond.contentEquals(ADDED)) {
            userName = name;
            startReceiveThr(ui);
            return true;
        }
        return false;
    }

    public void startReceiveThr(UIThread uiThread) {
        ui = uiThread; // wtf
        ReceiveThread rt = new ReceiveThread(bufferedReader, ui);
        rt.setDaemon(true);
        rt.start();
    }
}
