package com.chnu.crossplatfromprogramming.ServerSide;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerDriver extends UnicastRemoteObject {
    private ServerDriver() throws RemoteException {
    }

    public static void main(String[] args) throws RemoteException {
        ServerDriver serverDriver = new ServerDriver();
        serverDriver.startServer();
    }

    private void startServer() throws RemoteException {
        ServerIF serverIF = new Server();
        Registry registry = java.rmi.registry.LocateRegistry.createRegistry(8080);
        registry.rebind("server", serverIF);
        System.out.println("Server is run");
    }
}