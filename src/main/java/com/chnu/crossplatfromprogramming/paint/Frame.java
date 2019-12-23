package com.chnu.crossplatfromprogramming.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.rmi.RemoteException;
import javax.swing.border.EtchedBorder;

public class Frame extends JFrame implements MouseMotionListener {
    private static final int DEFAULT_WIDTH = 900;
    private static final int DEFAULT_HEIGHT = 600;
    private Color selectedColor = Color.BLACK;
    private JTextField textField;
    private TextArea textArea;

    Frame(String nameClient) throws IOException {
        JPanel contentPane = (JPanel) getContentPane();
        JPanel pane = new JPanel();

        setTitle("Graphic chat - " + nameClient);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        EtchedBorder etched = new EtchedBorder(Color.gray.brighter(), Color.gray.darker());
        pane.setBorder(etched);

        contentPane.setBorder(etched);
        contentPane.setLayout(new BorderLayout());

        pane.setLayout(new BorderLayout());

        PaintToolBar paintToolBar = new PaintToolBar();
        paintToolBar.setRollover(true);
        paintToolBar.addMouseMotionListener(this);
        paintToolBar.setFloatable(false);
        paintToolBar.setVisible(true);

        Panel colorPanel = new Panel();
        colorPanel.setSize(20, 20);
        colorPanel.setLayout(new BorderLayout());

        textArea = new TextArea(50, 25);
        textArea.setEditable(false);
        textArea.setBackground(Color.LIGHT_GRAY);
        DrawArea area = new DrawArea(paintToolBar, textArea);

        Client.drawArea = area;

        JPanel up = new JPanel();
        up.setBackground(Color.WHITE);
        JButton logout = new JButton("Log out");
        logout.addActionListener(event -> System.exit(0));
        up.add(paintToolBar);
        up.add(logout);
        JPanel down = new JPanel();
        down.add(area);
        contentPane.add(up, BorderLayout.NORTH);
        contentPane.add(area, BorderLayout.CENTER);

        Panel textPanel = new Panel();
        textPanel.setBackground(Color.PINK);
        textPanel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Message :");
        textField = new JTextField(40);
        JButton send = new JButton("Send ");
        send.addActionListener(event -> ClientDriver.passMessage(0, 0, 0, 0, textField.getText().trim()));
        JButton erase = new JButton("Clear");
        erase.addActionListener(event -> textField.setText(""));
        JButton getList = new JButton("Connected users");
        getList.addActionListener(event -> {
            textArea.setText("");
            String[] names = new String[0];
            try {
                names = ServerTalker.server.listChatter();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            for (String name : names) {
                textArea.append(name);
                textArea.append("\n");
            }
        });

        JButton eraseList = new JButton("Clear chat");
        eraseList.addActionListener(event -> {
            textArea.setText("");
            ClientDriver.clearMessage();
        });

        textPanel.add(label);
        textPanel.add(textField);
        textPanel.add(send);
        textPanel.add(erase);
        textPanel.add(getList);
        textPanel.add(eraseList);

        contentPane.add(textPanel, BorderLayout.SOUTH);

        Panel tapanel = new Panel();
        tapanel.setLayout(new BorderLayout());
        tapanel.add(textArea);

        contentPane.add(tapanel, BorderLayout.EAST);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(selectedColor);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}