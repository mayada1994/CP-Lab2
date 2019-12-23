package com.chnu.crossplatfromprogramming.paint;

public class Client implements ClientIF {
    static DrawArea drawArea = null;

    @Override
    public void notifyMe(int x1, int y1, int x2, int y2, String type, String c) {
        if (type.equalsIgnoreCase("pencil")) {
            drawArea.takePencil(x1, y1, x2, y2, 1);
        } else if (type.equalsIgnoreCase("eraser")) {
            drawArea.takeEraser(x1, y1, x2, y2, 1);
        } else if (type.equalsIgnoreCase("line")) {
            drawArea.takeLine(x1, y1, x2, y2, 1);
        } else if (type.equalsIgnoreCase("rectangle")) {
            drawArea.takeRectangle(x1, y1, x2, y2, 1);
        } else if (type.equalsIgnoreCase("oval")) {
            drawArea.takeOval(x1, y1, x2, y2, 1);
        } else {
            drawArea.ta.append("[" + c + "]" + type);
            drawArea.ta.append("\n");
        }
    }

    static void setBc() {
        drawArea.setBc();
    }
}