package org.example.finalproject;

import java.util.ArrayList;

public class SnowflakeFractal extends Fractal{

    /**
     * Create a fractal of the base shape with the number of levels
     *
     * @param baseShape the base shape
     * @param numLevels the number of levels
     */
    public SnowflakeFractal(Polygon baseShape, int numLevels) {
        super(baseShape, numLevels);
        if (!(baseShape instanceof RegularPolygon)) {
            throw new IllegalArgumentException();
        }
        setNumLevels(numLevels);
    }

    /**
     * Sets the number of levels of the fractal to the inputted value
     * @param numLevels the new number of levels
     */
    @Override
    public void setNumLevels(int numLevels) {
        setPoints(getBaseShape().getPoints());
        setLines(calcLines(getBaseShape().getPoints()));
        LinkedList<Line> linkedListOfLines = new LinkedList<>();
        linkedListOfLines.setArray(super.getLines());
        for (int i = 0; i < numLevels; i++) {
            for (Line line : super.getLines()) {
                System.out.println(line + "wheeee");
            }
            LLNode<Line> line = linkedListOfLines.getFirstNode();
            while (line != null) {
                // find the base points of the peak
                Point pA = new Point(
                        line.getElement().getFirstPoint().getX() + (line.getElement().getSecondPoint().getX() - line.getElement().getFirstPoint().getX()) / 3.0,
                        line.getElement().getFirstPoint().getY() + (line.getElement().getSecondPoint().getY() - line.getElement().getFirstPoint().getY()) / 3.0
                );
                Point pB = new Point(
                        line.getElement().getFirstPoint().getX() + (line.getElement().getSecondPoint().getX() - line.getElement().getFirstPoint().getX()) * 2.0 / 3.0,
                        line.getElement().getFirstPoint().getY() + (line.getElement().getSecondPoint().getY() - line.getElement().getFirstPoint().getY()) * 2.0 / 3.0
                );

                // find the peak point
                double dx = pB.getX() - pA.getX();
                double dy = pB.getY() - pA.getY();
                Point peak = new Point(
                        ((dx) * Math.cos(-Math.PI / 3) - (dy) * Math.sin(-Math.PI / 3)) + pA.getX(),
                        ((dx) * Math.sin(-Math.PI / 3) + (dy) * Math.cos(-Math.PI / 3)) + pA.getY()
                );


                line.setNext(new LLNode<>(new Line(pA, peak), new LLNode<>(new Line(peak, pB), new LLNode<>(new Line(pB, line.getElement().getSecondPoint()), line.getNext()))));
                line.setElement(new Line(line.getElement().getFirstPoint(), pA));

                line = line.getNext().getNext().getNext().getNext();
            }
            setLines(linkedListOfLines.toArrayList().toArray(new Line[0]));
        }
        ArrayList<Point> listOfPoints = new ArrayList<>();
        for (Line line : linkedListOfLines) {
            listOfPoints.add(line.getFirstPoint());
        }
        setPoints(listOfPoints.toArray(new Point[0]));
        rotate(0);
    }
}
