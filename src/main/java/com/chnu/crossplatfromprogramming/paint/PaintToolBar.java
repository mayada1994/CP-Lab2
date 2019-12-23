package com.chnu.crossplatfromprogramming.paint;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class PaintToolBar extends JToolBar implements ActionListener {
    private ButtonGroup group;
    static final int ERASER = 1;
    static final int PENCIL = 3;
    static final int LINE = 4;
    static final int RECTANGLE = 5;
    static final int ELLIPSE = 7;

    PaintToolBar() throws IOException {
        group = new ButtonGroup();
        setBackground(Color.BLACK);

        JToggleButton eraser = new JToggleButton(new ImageIcon(ImageIO.read(getClass().getResource("/eraser.gif"))));
        eraser.setMargin(new Insets(0, 0, 0, 0));
        eraser.setActionCommand("eraser");
        eraser.addActionListener(this);
        eraser.setToolTipText("Eraser");

        JToggleButton pencil = new JToggleButton(new ImageIcon(ImageIO.read(getClass().getResource("/pencil.gif"))));
        pencil.setMargin(new Insets(0, 0, 0, 0));
        pencil.setActionCommand("pencil");
        pencil.addActionListener(this);
        pencil.setToolTipText("Free Hand Sketch");

        JToggleButton line = new JToggleButton(new ImageIcon(ImageIO.read(getClass().getResource("/line.gif"))));
        line.setMargin(new Insets(0, 0, 0, 0));
        line.setActionCommand("line");
        line.addActionListener(this);
        line.setToolTipText("Line");

        JToggleButton rectangle = new JToggleButton(new ImageIcon(ImageIO.read(getClass().getResource("/rectangle.gif"))));
        rectangle.setMargin(new Insets(0, 0, 0, 0));
        rectangle.setActionCommand("rectangle");
        rectangle.addActionListener(this);
        rectangle.setToolTipText("Rectangle");

        JToggleButton ellipse = new JToggleButton(new ImageIcon(ImageIO.read(getClass().getResource("/ellipse.gif"))));
        ellipse.setMargin(new Insets(0, 0, 0, 0));
        ellipse.setActionCommand("ellipse");
        ellipse.addActionListener(this);
        ellipse.setToolTipText("Ellipse");

        JToggleButton clear = new JToggleButton(new ImageIcon(ImageIO.read(getClass().getResource("/clear.png"))));
        clear.setMargin(new Insets(0, 0, 0, 0));
        clear.setActionCommand("clear");
        clear.addActionListener(this);
        clear.setToolTipText("Clear");

        add(eraser);
        this.addSeparator();
        add(rectangle);
        this.addSeparator();
        add(pencil);
        this.addSeparator();
        add(line);
        this.addSeparator();
        add(ellipse);
        this.addSeparator();
        add(clear);
        this.addSeparator();

        this.setRollover(true);

        group.add(eraser);
        group.add(pencil);
        group.add(line);
        group.add(rectangle);
        group.add(ellipse);
        group.add(clear);

        pencil.setSelected(true);
    }

    int getTool() {
        String selected = group.getSelection().getActionCommand();
        switch (selected) {
            case "eraser":
                return ERASER;
            case "pencil":
                return PENCIL;
            case "line":
                return LINE;
            case "rectangle":
                return RECTANGLE;
            case "ellipse":
                return ELLIPSE;
        }
        return -1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("clear")) {
            Client.setBc();
        }
    }
}