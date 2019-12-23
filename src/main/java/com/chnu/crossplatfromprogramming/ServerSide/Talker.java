package com.chnu.crossplatfromprogramming.ServerSide;

import com.chnu.crossplatfromprogramming.paint.ClientIF;

import java.util.Vector;

public class Talker extends Thread {
    private Vector<Integer> messages1 = new Vector<>();
    private Vector<Integer> messages2 = new Vector<>();
    private Vector<Integer> messages3 = new Vector<>();
    private Vector<Integer> messages4 = new Vector<>();
    private Vector<String> messages5 = new Vector<>();
    private Vector<String> messages6 = new Vector<>();
    private ClientIF clientIF;
    private String name;

    Talker(ClientIF clientIF, String name) {
        this.clientIF = clientIF;
        this.name = name;
        this.start();
    }

    void addMessage(int x1, int y1, int x2, int y2, String type, String c) {
        checkAccess();
        messages1.addElement(x1);
        messages2.addElement(y1);
        messages3.addElement(x2);
        messages4.addElement(y2);
        messages5.addElement(type);
        messages6.addElement(c);
    }

    public void run() {
        while (true) {
            try {
                if (messages1.isEmpty()) {
                    checkAccess();
                }
                int a = messages1.elementAt(0);
                int b = messages2.elementAt(0);
                int d = messages3.elementAt(0);
                int f = messages4.elementAt(0);
                String g = messages5.elementAt(0);
                String h = messages6.elementAt(0);
                clientIF.notifyMe(a, b, d, f, g, h);
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

    String getChatterName() {
        return name;
    }
}