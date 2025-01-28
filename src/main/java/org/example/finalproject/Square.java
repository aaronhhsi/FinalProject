package org.example.finalproject;

/** a square
 * @author Aaron Hsi
 */
public class Square extends Rectangle implements RegularPolygon {

    /**
     * Create a square with the given center point and side length
     * @param center the center point
     * @param length the side length
     */
    public Square(Point center, double length) {
        super(center, length, length);
    }

    /**
     * Returns the side length
     * @return the side length
     */
    @Override
    public double getSideLength() {
        return super.getWidth();
    }

    /**
     * Sets the side length
     * @param width the new side length
     */
    @Override
    public void setSideLength(double width) {
        setWidth(width);
    }

    /**
     * Set the width of the square to the given
     * @param width the width of the square
     */
    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    /**
     * Set the height of the square to the given
     * @param height the height of the square
     */
    @Override
    public void setHeight(double height) {
        super.setWidth(height);
        super.setHeight(height);
    }

    /**
     * Returns the number of sides (overriding interface NGon)
     * @return the number of sides
     */
    @Override
    public int getNumSides() {
        return 4;
    }
}
