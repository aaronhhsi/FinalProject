package org.example.finalproject;

/** a polygon
 * @author Aaron Hsi
 */
public abstract class Polygon{

    /** the center point of the shape */
    private Point center;

    /** the angle offset of the shape (used to calculate the rotation of the shape) */
    private double angleOffset = 0;

    /** the points that make up the shape */
    private Point[] points;

    /** the points that make up the shape rotated */
    private Point[] rotatedPoints;

    /** the lines that make up the shape */
    private Line[] lines;

    /**
     * Create a polygon with the given points as vertices
     * @param points the vertices of the polygon
     */
    public Polygon(Point[] points) {
        setPoints(points);
    }

    /**
     * Set the vertices of the polygon to the given points
     * @param points the new vertices of the polygon
     */
    public void setPoints(Point[] points) {

        this.points = points;
        rotatedPoints = points;
    }

    /**
     * Returns the center point of the shape
     * @return the center point of the shape
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Sets the center point of the shape to the given
     * @param center the new center point of the shape
     */
    public void setCenter(Point center) {
        if (this.center != null) {
            double deltaX = center.getX() - getCenter().getX();
            double deltaY = center.getY() - getCenter().getY();

            /** adjusts all points by the difference between the center point and the new center point */
            for (int i = 0; i < points.length; i++) {
                points[i] = new Point(points[i].getX() + deltaX, points[i].getY() + deltaY);
            }

        }
        this.center = center;
        rotate(0.0);
    }

    /**
     * Rotates the shape by the given angle CLOCKWISE
     * @param angle the angle the shape is rotating
     */
    public void rotate(double angle) {
        angleOffset += angle;
        double cos = Math.cos(angleOffset);
        double sin = Math.sin(angleOffset);

        rotatedPoints = points.clone();

        /** formula below based on https://danceswithcode.net/engineeringnotes/rotations_in_2d/rotations_in_2d.html */
        for (int i = 0; i < rotatedPoints.length; i++) {
            rotatedPoints[i] = new Point(Math.round((((rotatedPoints[i].getX() - getCenter().getX()) * cos - (rotatedPoints[i].getY() - getCenter().getY()) * sin) + getCenter().getX()) * 100.0) / 100.0,Math.round((((rotatedPoints[i].getX() - getCenter().getX()) * sin + (rotatedPoints[i].getY() - getCenter().getY()) * cos) + getCenter().getY()) * 100.0) / 100.0);
        }
        calcLines(rotatedPoints);
    }

    /**
     * Returns an array of the points in the shape
     * @return an array of the points in the shape
     */
    public Point[] getPoints() {
        return this.rotatedPoints;
    }

    /**
     * Returns an array of the lines in the shape
     * Precondition: there must be more than 2 points (otherwise it is not a polygon)
     * @return an array of the lines in the shape
     */
    public Line[] getLines() {
        lines = calcLines(rotatedPoints);
        return lines;
    }

    public void setLines(Line[] lines) {
        this.lines = lines;
    }

    public Line[] calcLines(Point[] points) {
        if (points.length > 2) {
            Line[] lines = new Line[points.length];
            for (int i = 0; i < points.length - 1; i++) {
                lines[i] = new Line(points[i], points[i + 1]);
            }
            lines[points.length - 1] = new Line(points[points.length - 1], points[0]);
            return lines;
        }
        return null;
    }
}
