package com.psy888;

import java.io.BufferedReader;
import java.io.IOException;

public class ReceiveThread implements Runnable {
    private static final String DIVIDER = "|";
    public static final String COMMAND_SERVER_INFO = "-server_info";

    BufferedReader bufferedReader;

    public ReceiveThread(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run() {
        try {
            do {

                String msg = bufferedReader.readLine();
                if (msg == null) break;
                System.out.println(msg);

            } while (true);
        } catch (IOException e) {
            System.out.println("Связь с сервером потеряна.");

//            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();

            } catch (IOException e) {
//                e.printStackTrace();
            }
            System.out.println("Входящий поток закрыт");
        }
    }
}
