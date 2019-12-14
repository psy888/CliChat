package com.psy888;

import java.io.*;
import java.net.Socket;

public class Main {
    public final static String HOST = "172.30.2.178";
    public final static int PORT = 8778;


    public static void main(String[] args) {
        // write your code here
        try {
            Socket socket = new Socket(HOST, PORT);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


            Thread receiveThr = new Thread(new ReceiveThread(bufferedReader));
            Thread sendThr = new Thread(new SendThread(bufferedWriter));
//            receiveThr.setDaemon(true);
            sendThr.setDaemon(true);
            receiveThr.start();
            sendThr.start();
//            try {
//                do {
//                    String msg = bufferedReader.readLine();
//                    System.out.println(msg);
//
//                } while (true);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }


        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
