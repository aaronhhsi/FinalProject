package org.example.finalproject;

/** a rectangle
 * @author Aaron Hsi
 */
public class Rectangle extends Polygon {

    /** the height of the rectangle */
    private double height;

    /** the width of the rectangle */
    private double width;

    /**
     * Creates a rectangle centered at the given point with the given height and width
     * @param center the center of the rectangle
     * @param height the height of the rectangle
     * @param width the width of the rectangle
     */
    public Rectangle(Point center, double height, double width) {
        super(new Point[]{new Point(center.getX() + width / 2, center.getY() + height / 2), new Point(center.getX() - width / 2, center.getY() + height / 2), new Point(center.getX() - width / 2, center.getY() - height / 2), new Point(center.getX() + width / 2, center.getY() - height / 2)});
        super.setCenter(center);
        this.height = height;
        this.width = width;
    }

    /**
     * Returns the height of the rectangle
     * @return the height of the rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     * Returns the width of the rectangle
     * @return the width of the rectangle
     */
    public double getWidth() {
        return width;
    }

    /**
     * Set the height of the rectangle to the given
     * @param height the height of the rectangle
     */
    public void setHeight(double height) {
        this.height = height;
        setPoints(new Point[]{new Point(getCenter().getX() + width / 2, getCenter().getY() + height / 2), new Point(getCenter().getX() - width / 2, getCenter().getY() + height / 2), new Point(getCenter().getX() - width / 2, getCenter().getY() - height / 2), new Point(getCenter().getX() + width / 2, getCenter().getY() - height / 2)});
        rotate(0.0);
    }

    /**
     * Set the width of the rectangle to the given
     * @param width the width of the rectangle
     */
    public void setWidth(double width) {
        this.width = width;
        setPoints(new Point[]{new Point(getCenter().getX() - width / 2, getCenter().getY() + height / 2), new Point(getCenter().getX() + width / 2, getCenter().getY() + height / 2), new Point(getCenter().getX() + width / 2, getCenter().getY() - height / 2), new Point(getCenter().getX() - width / 2, getCenter().getY() - height / 2)});
        rotate(0.0);
    }
}
