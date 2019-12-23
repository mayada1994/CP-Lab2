package com.chnu.crossplatfromprogramming.paint;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Option extends JFrame implements ActionListener {
    private static final int width = 500;
    private static final int height = 500;
    private JButton OkButton;
    private JTextField textField;

    Option() {
        setSize(width, height);
        setTitle("Sign in");
        Container contentPane = getContentPane();

        JLabel label = new JLabel("Input your name: ");
        OkButton = new JButton("Ok");
        JButton cancelButton = new JButton("Cancel");
        textField = new JTextField(30);

        this.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setSize(500, 40);
        panel.add(label);
        panel.add(textField);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.setSize(500, 40);
        panel2.add(OkButton);
        panel2.add(cancelButton);
        OkButton.addActionListener(this);
        cancelButton.addActionListener(this);

        Box vbox = Box.createVerticalBox();
        vbox.add(Box.createVerticalStrut(100));
        vbox.add(panel);
        vbox.add(Box.createVerticalStrut(50));
        vbox.add(panel2);

        panel.setBackground(Color.cyan);
        panel2.setBackground(Color.cyan);
        contentPane.setBackground(Color.cyan);
        add(vbox);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int option;
        if (e.getSource().equals(OkButton)) {
            option = 1;
        } else {
            option = 2;
        }
        try {
            Test.call(option, textField.getText());
        } catch (Exception ignored) {
        }
    }
}