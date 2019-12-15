package com.psy888;

import java.io.*;
import java.nio.charset.Charset;

public class Main {


    public final static String HOST = "172.30.2.178";
    public final static int PORT = 8778;


    public static void main(String[] args) {
        // write your code here

        try {
            ConnectionThread ct = new ConnectionThread();
            UI ui = new UI(ct);
            ui.login();
            ui.setChatWindowVisible();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
