package org.example.finalproject;

/** a line
 * @author Aaron Hsi
 */
public class Line {

    /** the first point of the line */
    private Point firstPoint;

    /** the second point of the line */
    private Point secondPoint;

    /**
     * Create a line with two given points
     * @param firstPoint the first point
     * @param secondPoint the second point
     */
    public Line(Point firstPoint, Point secondPoint) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    /**
     * Create a line with the coordinates of two points
     * @param firstX the x coordinate of the first point
     * @param firstY the y coordinate of the first point
     * @param secondX the x coordinate of the second point
     * @param secondY the y coordinate of the second point
     */
    public Line(double firstX, double firstY, double secondX, double secondY) {
        firstPoint = new Point(firstX, firstY);
        secondPoint = new Point(secondX, secondY);
    }

    /**
     * Returns the first point of the line
     * @return the first point of the line
     */
    public Point getFirstPoint() {
        return firstPoint;
    }

    /**
     * Sets the first point of the line to the given
     * @param firstPoint the new first point of the line
     */
    public void setFirstPoint(Point firstPoint) {
        this.firstPoint = firstPoint;
    }

    /**
     * Sets the second point of the line to the given
     * @param secondPoint the new second point of the line
     */
    public void setSecondPoint(Point secondPoint) {
        this.secondPoint = secondPoint;
    }

    /**
     * Returns the second point of the line
     * @return the second point of the line
     */
    public Point getSecondPoint() {
        return secondPoint;
    }

    /**
     * Returns an array containing all Line types that make up the line
     * @return an array containing all Line types that make up the line
     */
    public Line[] getLines() {
        return new Line[]{this};
    }

    public String toString() {
        return getFirstPoint() + " to " + getSecondPoint();
    }
}
