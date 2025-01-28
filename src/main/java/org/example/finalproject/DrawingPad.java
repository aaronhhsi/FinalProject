package org.example.finalproject;

import javax.swing.*;
import java.awt.*;

/** a drawing pad
 * @author Aaron Hsi
 */
public class DrawingPad {

    /** the canvas */
    private Canvas canvas;

    /** the graphics */
    private Graphics graphicsContext;

    /** the JPanel */
    private JPanel jPanel;

    /** the shape being drawn */
    private Polygon shape;

    /**
     * Creates a DrawingPad with the given width and height
     * @param width the width of the DrawingPad
     * @param height the height of the DrawingPad
     */
    public DrawingPad(int width, int height) {
        /** Create a JFrame */
        JFrame jFrame = new JFrame("Drawing Pad");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /** Create a Canvas */
        canvas = new Canvas() {
            /** Overrides the paint method in Canvas to include drawing the lines/shape (only way I was able to do it) */
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(Color.black);
                g.drawLine(width / 2, 0, width / 2, height);
                g.drawLine(0,height / 2, width, height / 2);
                if (shape != null && shape.getLines() != null) {
                    try {
                        for (Line line : shape.getLines()) {
                            g.drawLine((int) line.getFirstPoint().getX() + width / 2, height / 2 - (int) line.getFirstPoint().getY(), (int) line.getSecondPoint().getX() + width / 2, height / 2 - (int) line.getSecondPoint().getY());
//                            System.out.println(height / 2  - line.getFirstPoint().getY());
                        }
                    } catch (Exception e) {
                        System.out.println("An error occurred: " + e);
                    }
                }
            }
        };
        /** sets the size of the canvas to the given parameters */
        canvas.setSize(width, height);
        canvas.setBackground(Color.white);

        /** Create a JPanel */
        jPanel = new JPanel();
        jPanel.add(canvas);
        jFrame.add(jPanel);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    /**
     * Returns the graphics
     * @return the graphics
     */
    public Graphics getGraphicsContext() {
        if (graphicsContext == null) {
            graphicsContext = canvas.getGraphics();
        }
        return graphicsContext;
    }

    /**
     * Draws the given shape on the DrawingPad
     * @param shape the shape desired on the DrawingPad
     */
    public void draw(Polygon shape) {
        this.shape = shape;

//        canvas.getGraphics().setColor(Color.black);
//        for (Line line : shape.getLines()) {
//            canvas.getGraphics().drawLine((int)line.getFirstPoint().getX() + canvas.getWidth() / 2, (int)line.getFirstPoint().getY() + canvas.getHeight() / 2, (int)line.getSecondPoint().getX() + canvas.getWidth() / 2, (int)line.getSecondPoint().getY() + canvas.getHeight() / 2);
//        }
        canvas.repaint();
//        jPanel.repaint();
    }

    /** erases the DrawingPad */
    public void erase() {
        this.shape = null;
        canvas.repaint();
    }
}
