package com.chnu.crossplatfromprogramming.paint;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.*;

public class Test extends UnicastRemoteObject {
    static String name = null;
    private static Option start = null;

    private Test() throws RemoteException {
        String lf = UIManager.getSystemLookAndFeelClassName();
        try {
            UIManager.setLookAndFeel(lf);
        } catch (Exception e) {
            System.out.println(" " + e);
        }
        start = new Option();
        start.setSize(600, 300);
        start.setLocation(100, 150);
        start.show();
        start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    static void call(int choice, String nameClient) throws IOException, NotBoundException {
        if (choice == 1) {
            Test.name = nameClient;
            start.setVisible(false);
            Frame mf = new Frame(nameClient);
            mf.setSize(900, 600);
            mf.show();
        } else {
            System.exit(0);
        }

        ClientDriver cd = new ClientDriver(nameClient);
        cd.startClient();
    }

    public static void main(String[] args) throws RemoteException {
        new Test();
    }
}