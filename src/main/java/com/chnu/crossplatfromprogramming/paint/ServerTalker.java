package com.chnu.crossplatfromprogramming.paint;

import com.chnu.crossplatfromprogramming.ServerSide.ServerIF;

import java.util.Vector;

public class ServerTalker extends Thread {
    static ServerIF server = null;

    private static Vector<Integer> messages1 = new Vector<>();
    private static Vector<Integer> messages2 = new Vector<>();
    private static Vector<Integer> messages3 = new Vector<>();
    private static Vector<Integer> messages4 = new Vector<>();
    private static Vector<String> messages5 = new Vector<>();
    private static Vector<String> messages6 = new Vector<>();

    ServerTalker(ServerIF server) {
        ServerTalker.server = server;
        this.start();
    }

    void addMessage(int x1, int y1, int x2, int y2, String type) {
        checkAccess();
        if (server == null) {
            System.out.println("server is down");
        }
        messages1.addElement(x1);
        messages2.addElement(y1);
        messages3.addElement(x2);
        messages4.addElement(y2);
        messages5.addElement(type);
        messages6.addElement(Test.name);
    }

    void clearAllMessage() {
        checkAccess();
        if (server == null) {
            System.out.println("server is down");
        }
        messages1.clear();
        messages2.clear();
        messages3.clear();
        messages4.clear();
        messages5.clear();
        messages6.clear();
    }

    public void run() {
        while (true) {
            try {
                if (messages1.isEmpty()) {
                    checkAccess();
                }
                server.postMessage(messages1.elementAt(0), messages2.elementAt(0), messages3.elementAt(0), messages4.elementAt(0), messages5.elementAt(0), messages6.elementAt(0));
                messages1.removeElementAt(0);
                messages2.removeElementAt(0);
                messages3.removeElementAt(0);
                messages4.removeElementAt(0);
                messages5.removeElementAt(0);
                messages6.removeElementAt(0);
            } catch (Exception ignored) {
            }
            yield();
        }
    }
}