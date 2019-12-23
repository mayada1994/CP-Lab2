package com.chnu.crossplatfromprogramming.paint;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientIF extends Remote {

    void notifyMe(int x1, int y1, int x2, int y2, String type, String c) throws RemoteException;
}