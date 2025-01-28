package org.example.finalproject;

public class NGon extends Polygon implements RegularPolygon{
    private double sideLength;

    private int numSides;

    public NGon(Point center, double sideLength) {
        super(new Point[]{});
        super.setCenter(center);
        this.sideLength = sideLength;
    }

    public void setNumSides(int numSides) {
        if (numSides < 3) {
            throw new IllegalArgumentException();
        }
        this.numSides = numSides;
        calcPoints();
    }

    @Override
    public int getNumSides() {
        return numSides;
    }

    @Override
    public double getSideLength() {
        return sideLength;
    }

    @Override
    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
        calcPoints();
    }

    private void calcPoints() {
        Point[] vertices = new Point[getNumSides()];
        double radius = getSideLength() / (2 * Math.sin(Math.PI / getNumSides()));
        double angleIncrement = 2 * Math.PI / getNumSides();
        for (int i = 0; i < numSides; i++) {
            double angle = i * angleIncrement;
            vertices[i] = new Point(getCenter().getX() + radius * Math.cos(angle), getCenter().getY() + radius * Math.sin(angle));
        }
        setPoints(vertices);
    }
}
