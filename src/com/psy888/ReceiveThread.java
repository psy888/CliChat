package com.psy888;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;

public class ReceiveThread extends Thread {
    private static final String DIVIDER = "%";
    public static final String COMMAND_SERVER_INFO = "-server_info";


    BufferedReader bufferedReader;
    UI ui;

    public ReceiveThread(BufferedReader bufferedReader, UI ui) {
        this.bufferedReader = bufferedReader;
        this.ui = ui;
    }

    @Override
    public void run() {
        try {
            do {

                String msg = bufferedReader.readLine();
//                System.out.println(msg);
//                System.out.println(new String(Charset.forName("Cp1251").encode(msg).array()));
//                System.out.println(new String(Charset.forName("Cp1252").encode(msg).array()));
//                System.out.println(new String(Charset.forName("Cp866").encode(msg).array()));
//                System.out.println(new String(Charset.forName("windows-1251").encode(msg).array()));
//                System.out.println(new String(Charset.forName("windows-1252").encode(msg).array()));
//                System.out.println(new String(Charset.forName("UTF-8").encode(msg).array()));
//                String msg = new String(Charset.forName("Cp1251").encode(bufferedReader.readLine()).array());

                if (msg == null) break;

//                msg = new String(Charset.forName(System.getProperty("file.encoding")).encode(msg).array());
//                msg = new String(Charset.forName("Cp1252").encode(msg).array());

                if(msg.contains(COMMAND_SERVER_INFO)){
                    ui.updateUsersList(getNames(msg));
                    continue;
                }
                ui.addMsgToChat(msg);

            } while (true);
        } catch (IOException e) {
            ui.addMsgToChat("ERROR: Связь с сервером потеряна.");

        }finally {
            try {
                bufferedReader.close();

            } catch (IOException e) {
//                e.printStackTrace();
            }
            System.out.println("Входящий поток закрыт");
        }
    }

    public String[] getNames(String string){
        string = string.substring(COMMAND_SERVER_INFO.length()+2);
        String[] names = string.split(DIVIDER);
        return names;
    }
}
