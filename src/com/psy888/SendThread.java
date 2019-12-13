package com.psy888;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

public class SendThread implements Runnable {

    BufferedWriter bufferedWriter;

    public SendThread(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        do {

            String msg = scanner.nextLine();
            msg += "\r";

            try {
                bufferedWriter.write(msg);
                bufferedWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (msg.contains("-exit")) break;
        } while (true);

        try {
            bufferedWriter.close();
        } catch (IOException e) {
//            e.printStackTrace();
            System.out.println("исходящий поток закрыт");
        }
    }
}
