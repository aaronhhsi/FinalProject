package org.example.finalproject;

/** an equilateral triangle
 * @author Aaron Hsi
 */
public class EquilateralTriangle extends Triangle implements RegularPolygon {

    /** the length of a side of the equilateral triangle */
    private double length;

    /**
     * Creates an equilateral triangle with the given length centered at the given point
     * @param center the center point of the triangle
     * @param length the length of the sides
     */
    public EquilateralTriangle(Point center, double length) {
        super(new Point(length / Math.sqrt(3.0) + center.getX(), center.getY()), new Point(length / Math.sqrt(3.0) * Math.cos(2 * Math.PI / 3) + center.getX(), length / Math.sqrt(3.0) * Math.sin(2 * Math.PI / 3) + center.getY()), new Point(length / Math.sqrt(3.0) * Math.cos(-2 * Math.PI / 3) + center.getX(), length / Math.sqrt(3.0) * Math.sin(-2 * Math.PI / 3) + center.getY()));
        this.length = length;
    }

    /**
     * Returns the side length
     * @return the side length
     */
    @Override
    public double getSideLength() {
        return length;
    }

    /**
     * Sets the side length to the given
     * @param length the new side length
     */
    @Override
    public void setSideLength(double length) {
        setPoints(new Point[]{new Point(length / Math.sqrt(3.0) + getCenter().getX(), getCenter().getY()), new Point(length / Math.sqrt(3.0) * Math.cos(2 * Math.PI / 3) + getCenter().getX(), length / Math.sqrt(3.0) * Math.sin(2 * Math.PI / 3) + getCenter().getY()), new Point(length / Math.sqrt(3.0) * Math.cos(-2 * Math.PI / 3) + getCenter().getX(), length / Math.sqrt(3.0) * Math.sin(-2 * Math.PI / 3) + getCenter().getY())});
        this.length = length;
    }

    /**
     * Returns the number of sides (overriding interface NGon)
     * @return the number of sides
     */
    @Override
    public int getNumSides() {
        return 3;
    }
}
