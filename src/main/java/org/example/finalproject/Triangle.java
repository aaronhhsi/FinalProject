package org.example.finalproject;

/** a triangle
 * @author Aaron Hsi
 */
public class Triangle extends Polygon {

    /**
     * Creates a triangle with the given points as vertices
     * @param topPoint top point
     * @param leftPoint left point
     * @param rightPoint right point
     * Since directions are relative, take the parameter names with a grain of salt as they are
     * primarily used to differentiate the points from one another
     */
    public Triangle(Point rightPoint, Point topPoint, Point leftPoint) {
        super(new Point[]{rightPoint, topPoint, leftPoint});

        /** calculates the center of the triangle by averaging the vertices */
        super.setCenter(new Point((topPoint.getX() + leftPoint.getX() + rightPoint.getX()) / 3, (topPoint.getY() + leftPoint.getY() + rightPoint.getY()) / 3));
    }
}
