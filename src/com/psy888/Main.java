package com.psy888;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class Main {


    public final static String HOST = "172.30.2.178";
    public final static int PORT = 8778;



    public static void main(String[] args) {
        // write your code here

        ConnectionThread ct = new ConnectionThread();
        ct.start();
        UIThread ui = new UIThread(ct);
        ui.start();


    }


}
