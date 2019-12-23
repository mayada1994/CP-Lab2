package com.chnu.crossplatfromprogramming.paint;

import com.chnu.crossplatfromprogramming.ServerSide.ServerIF;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

class ClientDriver {
    private String name;
    private static ServerTalker serverTalker = null;

    ClientDriver(String name) {
        this.name = name;
    }

    void startClient() throws NotBoundException, MalformedURLException, RemoteException {
        String url = "rmi://localhost:8080/server";
        ServerIF server = (ServerIF) Naming.lookup(url);
        try {
            ClientIF c = new Client();
            UnicastRemoteObject.exportObject(c, 0);
            System.out.println("User " + name + " " + "login to server");
            server.registerClient(c, name);
            serverTalker = new ServerTalker(server);
        } catch (Exception ex) {
            System.out.println("stack trace : " + ex);
        }
    }

    static void passMessage(int x1, int y1, int x2, int y2, String s) {
        serverTalker.addMessage(x1, y1, x2, y2, s);
    }

    static void clearMessage() {
        serverTalker.clearAllMessage();
    }
}