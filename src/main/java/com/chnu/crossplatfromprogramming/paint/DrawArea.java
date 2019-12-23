package com.chnu.crossplatfromprogramming.paint;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DrawArea extends Canvas implements ActionListener, ChangeListener, MouseListener, MouseMotionListener {
    private int a;
    private int b;
    private int c;
    private int d;
    private Color h;
    private int b1;

    private static int LINE = 1;
    private static int ERASER = 2;
    private static int RECT = 3;
    private static int ROUND = 4;
    private static int PENCIL = 5;
    private static int currentTool = 0;
    private int mouseX;
    private int mouseY;
    private int eX;
    private int eY;
    private int neweX;
    private int neweY;
    private int pX;
    private int pY;
    private int newpX;
    private int newpY;
    private PaintToolBar mPaintToolBar;
    TextArea ta;
    private static Color currentColor = Color.BLACK;

    DrawArea(PaintToolBar ptb, TextArea ta) {
        this.ta = ta;
        addMouseListener(this);
        addMouseMotionListener(this);
        mPaintToolBar = ptb;
        this.setSize(500, 500);
        this.setBackground(Color.WHITE);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (getTool() == LINE) {
            g2.setColor(h);
            g2.setStroke(new BasicStroke(1));
            g2.drawLine(a, b, c, d);
            if (b1 == 2)
                ClientDriver.passMessage(a, b, c, d, "line");
        } else if (getTool() == RECT) {
            g2.setColor(h);
            g2.setStroke(new BasicStroke(1));
            g2.drawRect(a, b, c, d);
            if (b1 == 2)
                ClientDriver.passMessage(a, b, c, d, "rectangle");
        } else if (getTool() == ROUND) {
            g2.setColor(h);
            g2.setStroke(new BasicStroke(1));
            g2.drawOval(a, b, c, d);
            if (b1 == 2)
                ClientDriver.passMessage(a, b, c, d, "oval");
        } else if (getTool() == PENCIL) {
            g2.setColor(h);
            g2.setStroke(new BasicStroke(5));
            g2.drawLine(a, b, c, d);
            if (b1 == 2)
                ClientDriver.passMessage(a, b, c, d, "pencil");
        } else if (getTool() == ERASER) {
            g2.setColor(Color.white);
            g2.setStroke(new BasicStroke(10));
            g2.draw(new Line2D.Double(a, b, c, d));
            if (b1 == 2)
                ClientDriver.passMessage(a, b, c, d, "eraser");
        } else {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 500, 500);
        }
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("CLEAR")) {
            this.setBackground(Color.WHITE);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        newpX = e.getX();
        newpY = e.getY();
        neweX = e.getX();
        neweY = e.getY();
        if (mPaintToolBar.getTool() == PaintToolBar.ERASER) {
            b1 = 2;
            setTool(ERASER);
            a = eX;
            b = eY;
            c = neweX;
            d = neweY;
            h = Color.WHITE;
            repaint();
            eX = neweX;
            eY = neweY;
        } else if (mPaintToolBar.getTool() == PaintToolBar.PENCIL) {
            b1 = 2;
            setTool(PENCIL);
            a = pX;
            b = pY;
            c = newpX;
            d = newpY;
            h = currentColor;
            repaint();
            pX = newpX;
            pY = newpY;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        b1 = 2;
        mouseX = e.getX();
        mouseY = e.getY();
        eX = e.getX();
        eY = e.getY();
        pX = e.getX();
        pY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        b1 = 2;
        neweX = e.getX();
        neweY = e.getY();
        newpX = e.getX();
        newpY = e.getY();
        int newmouseX = e.getX();
        int newmouseY = e.getY();
        int lastX = -mouseX + newmouseX;
        int lastY = -mouseY + newmouseY;
        if (mPaintToolBar.getTool() == PaintToolBar.LINE) {
            b1 = 2;
            setTool(LINE);
            a = mouseX;
            b = mouseY;
            c = newmouseX;
            d = newmouseY;
            h = currentColor;
            repaint();
        } else if (mPaintToolBar.getTool() == PaintToolBar.RECTANGLE) {
            b1 = 2;
            setTool(RECT);
            a = mouseX;
            b = mouseY;
            c = lastX;
            d = lastY;
            h = currentColor;
            repaint();
        } else if (mPaintToolBar.getTool() == PaintToolBar.ELLIPSE) {
            b1 = 2;
            setTool(ROUND);
            a = mouseX;
            b = mouseY;
            c = lastX;
            d = lastY;
            h = currentColor;
            repaint();
        } else if (mPaintToolBar.getTool() == PaintToolBar.ERASER) {
            b1 = 2;
            setTool(ERASER);
            a = eX;
            b = eY;
            c = neweX;
            d = neweY;
            h = Color.WHITE;
            repaint();
        } else if (mPaintToolBar.getTool() == PaintToolBar.PENCIL) {
            b1 = 2;
            setTool(PENCIL);
            a = pX;
            b = pY;
            c = newpX;
            d = newpY;
            h = currentColor;
            repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    void takeRectangle(int x1, int y1, int x2, int y2, int bl) {
        b1 = 1;
        setTool(RECT);
        a = x1;
        b = y1;
        c = x2;
        d = y2;
        repaint();
    }


    void takeLine(int x1, int y1, int x2, int y2, int bl) {
        b1 = 1;
        setTool(LINE);
        a = x1;
        b = y1;
        c = x2;
        d = y2;
        repaint();
    }

    void takeOval(int x1, int y1, int x2, int y2, int bl) {
        b1 = 1;
        setTool(ROUND);
        a = x1;
        b = y1;
        c = x2;
        d = y2;
        repaint();
    }

    void takeEraser(int x1, int y1, int x2, int y2, int bl) {
        b1 = 1;
        setTool(ERASER);
        a = x1;
        b = y1;
        c = x2;
        d = y2;
        repaint();
    }

    void takePencil(int x1, int y1, int x2, int y2, int bl) {
        b1 = 1;
        setTool(PENCIL);
        a = x1;
        b = y1;
        c = x2;
        d = y2;
        repaint();
    }

    void setBc() {
        this.setTool(0);
        repaint();
    }

    private int getTool() {
        return currentTool;
    }

    private void setTool(int z) {
        currentTool = z;
    }
}