package org.example.finalproject;

/** a point
 * @author Aaron Hsi
 */
public class Point {

    /** the x coordinate of the point */
    private double x;

    /** the y coordinate of the point */
    private double y;

    /**
     * Create a point with an x and y coordinate
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x coordinate of the point
     * @return the x coordinate of the point
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y coordinate of the point
     * @return the y coordinate of the point
     */
    public double getY() {
        return y;
    }

    /**
     * Change the x coordinate of the point
     * @param x the x coordinate of the point
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Change the y coordinate of the point
     * @param y the y coordinate of the point
     */
    public void setY(double y) {
        this.y = y;
    }

    public void rotateAbout(Point center, double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double temp = Math.round((((this.getX() - center.getX()) * cos - (this.getY() - center.getY()) * sin) + center.getX()) * 100.0) / 100.0;
        setY(Math.round((((this.getX() - center.getX()) * sin + (this.getY() - center.getY()) * cos) + center.getY()) * 100.0) / 100.0);
        setX(temp);
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
