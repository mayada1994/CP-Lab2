package com.chnu.crossplatfromprogramming.ServerSide;

import com.chnu.crossplatfromprogramming.paint.ClientIF;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerIF extends Remote {

    void registerClient(ClientIF client, String name) throws RemoteException;

    String[] listChatter() throws RemoteException;

    void postMessage(int x1, int y1, int x2, int y2, String type, String s) throws RemoteException;
}