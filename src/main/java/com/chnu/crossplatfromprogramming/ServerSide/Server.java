package com.chnu.crossplatfromprogramming.ServerSide;

import com.chnu.crossplatfromprogramming.paint.ClientIF;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class Server extends UnicastRemoteObject implements ServerIF {
    private Vector<Talker> chatters = new Vector<>();

    Server() throws RemoteException {
        super();
        System.out.println("Server initialization");
    }

    @Override
    synchronized public void registerClient(ClientIF client, String name) {
        chatters.addElement(new Talker(client, name));
    }

    @Override
    public String[] listChatter() {
        String[] list = new String[chatters.size()];
        Talker c;
        for (int i = 0; i < list.length; i++) {
            c = chatters.elementAt(i);
            list[i] = c.getChatterName();
        }
        return list;
    }

    @Override
    synchronized public void postMessage(int x1, int y1, int x2, int y2, String type, String c) {
        Talker t;
        for (int i = 0; i < chatters.size(); i++) {
            t = chatters.elementAt(i);
            t.addMessage(x1, y1, x2, y2, type, c);
        }
    }
}