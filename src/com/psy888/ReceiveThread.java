package com.psy888;

import java.io.BufferedReader;
import java.io.IOException;

public class ReceiveThread extends Thread {
    private static final String DIVIDER = "|";
    public static final String COMMAND_SERVER_INFO = "-server_info";


    BufferedReader bufferedReader;
    UIThread uiThread;

    public ReceiveThread(BufferedReader bufferedReader, UIThread ui) {
        this.bufferedReader = bufferedReader;
        this.uiThread = ui;
    }

    @Override
    public void run() {
        try {
            do {

                String msg = bufferedReader.readLine();
                if (msg == null) break;

                if(msg.contains(COMMAND_SERVER_INFO)){
                    uiThread.updateUsersList(getNames(msg));
                    continue;
                }

                uiThread.addMsgToChat(msg);

            } while (true);
        } catch (IOException e) {
            uiThread.addMsgToChat("ERROR: Связь с сервером потеряна.");

        }finally {
            try {
                bufferedReader.close();

            } catch (IOException e) {
//                e.printStackTrace();
            }
            System.out.println("Входящий поток закрыт");
        }
    }

    private String[] getNames(String string){
        string = string.substring(COMMAND_SERVER_INFO.length());
        String[] names = string.split(DIVIDER);
        return names;
    }
}
