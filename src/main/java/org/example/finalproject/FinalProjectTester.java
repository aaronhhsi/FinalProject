package org.example.finalproject;

import org.junit.Test;

import static org.junit.Assert.*;
import java.util.Arrays;

public class FinalProjectTester {

    /**
     * Test the initialization of SnowflakeFractal with a base shape and number of levels.
     */
    @Test
    public void testInitialization() {
        // Create a SnowflakeFractal instance with an EquilateralTriangle as the base shape
        EquilateralTriangle triangle = new EquilateralTriangle(new Point(0, 0), 100); // Side length 100
        SnowflakeFractal fractal = new SnowflakeFractal(triangle, 3); // 3 levels

        // Verify that the base shape and number of levels are set correctly
        assertEquals(triangle, fractal.getBaseShape());
        assertEquals(3, fractal.getNumLevels());
    }

    /**
     * Test setNumLevels and ensure fractal updates correctly.
     */
    @Test
    public void testSetNumLevels() {
        EquilateralTriangle triangle = new EquilateralTriangle(new Point(0, 0), 100);
        SnowflakeFractal fractal = new SnowflakeFractal(triangle, 0);

        // Set levels to 2 and verify the update
        fractal.setNumLevels(2);
        assertEquals(2, fractal.getNumLevels());
    }

    /**
     * Test the getCenter and setCenter methods.
     */
    @Test
    public void testSetAndGetCenter() {
        EquilateralTriangle triangle = new EquilateralTriangle(new Point(0, 0), 100);
        SnowflakeFractal fractal = new SnowflakeFractal(triangle, 0);

        // Verify initial center
        assertEquals(new Point(0, 0), fractal.getCenter());

        // Move the fractal to a new center
        fractal.setCenter(new Point(50, 50));
        assertEquals(new Point(50, 50), fractal.getCenter());
    }

    /**
     * Test the rotate method.
     */
    @Test
    public void testRotate() {
        EquilateralTriangle triangle = new EquilateralTriangle(new Point(0, 0), 100);
        SnowflakeFractal fractal = new SnowflakeFractal(triangle, 0);

        // Rotate the fractal by 60 degrees (π/3 radians)
        fractal.rotate(Math.PI / 3);

        // Check that the fractal's points have been rotated correctly
        // (Here we assume the fractal class implements appropriate rotation logic for points.)
        Point[] rotatedPoints = fractal.getPoints();
        // Add assertions based on expected rotated points (requires implementation details)
    }

    /**
     * Test getPoints for level 0 (base shape points).
     */
    @Test
    public void testGetPointsLevel0() {
        EquilateralTriangle triangle = new EquilateralTriangle(new Point(0, 0), 100);
        SnowflakeFractal fractal = new SnowflakeFractal(triangle, 0);

        // Verify points are same as the base shape's points
        Point[] basePoints = triangle.getPoints();
        for (int i = 0; i < basePoints.length; i++) {
            assertEquals(basePoints[i].getX(), fractal.getPoints()[i].getX(),0.1);
            assertEquals(basePoints[i].getY(), fractal.getPoints()[i].getY(),0.1);
        }
    }

    /**
     * Test getLines for fractal level 1.
     */
    @Test
    public void testGetLinesLevel1() {
        EquilateralTriangle triangle = new EquilateralTriangle(new Point(0, 0), 100);
        SnowflakeFractal fractal = new SnowflakeFractal(triangle, 1);

        // Compute expected lines for fractal level 1
        Line[] baseLines = triangle.getLines();
        for (Line line : baseLines) {
            System.out.println(line);
        }
        Line[] expectedLines = new Line[]{};

        // Verify the generated lines match the expected lines
        assertArrayEquals(expectedLines, fractal.getLines());
    }

    /**
     * Test recursive fractal generation for multiple levels.
     */
    @Test
    public void testFractalGeneration() {
        EquilateralTriangle triangle = new EquilateralTriangle(new Point(0, 0), 100);
        SnowflakeFractal fractal = new SnowflakeFractal(triangle, 2);

        // Compute expected points/lines for level 2
        Line[] baseLines = triangle.getLines();
        Line[] expectedLines = computeFractalLines(baseLines, 2); // Helper method for recursion

        // Verify the generated lines
        assertArrayEquals(expectedLines, fractal.getLines());
    }

    /**
     * Helper method to compute fractal lines for a given level.
     */
    private Line[] computeFractalLines(Line[] baseLines, int level) {
        if (level == 0) return baseLines;

        // Create _/\_ pattern for each line recursively
        // Split lines and rotate segments as specified
        return Arrays.stream(baseLines)
                .flatMap(line -> Arrays.stream(createSnowflakeSegments(line))) // Split line into _/\_
                .toArray(Line[]::new);
    }

    /**
     * Helper method to create _/\_ pattern from a single line.
     */
    private Line[] createSnowflakeSegments(Line line) {
        Point p1 = line.getFirstPoint();
        Point p2 = line.getSecondPoint();

        // Compute 1/3 and 2/3 points
        Point pA = interpolate(p1, p2, 1.0 / 3.0); // 1/3 point
        Point pB = interpolate(p1, p2, 2.0 / 3.0); // 2/3 point

        // Compute peak of /\ by rotating middle segment 60 degrees
        Point peak = rotatePoint(pA, pB, Math.PI / 3);

        // Create 4 smaller lines forming _/\_
        return new Line[] {
                new Line(p1, pA),
                new Line(pA, peak),
                new Line(peak, pB),
                new Line(pB, p2)
        };
    }

    /**
     * Helper method to interpolate between two points.
     */
    private Point interpolate(Point p1, Point p2, double fraction) {
        int x = (int) (p1.getX() + fraction * (p2.getX() - p1.getX()));
        int y = (int) (p1.getY() + fraction * (p2.getY() - p1.getY()));
        return new Point(x, y);
    }

    /**
     * Helper method to rotate a point around another point.
     */
    private Point rotatePoint(Point origin, Point target, double angle) {
        double dx = target.getX() - origin.getX();
        double dy = target.getY() - origin.getY();

        double xRotated = origin.getX() + (dx * Math.cos(angle) - dy * Math.sin(angle));
        double yRotated = origin.getY() + (dx * Math.sin(angle) + dy * Math.cos(angle));

        return new Point((int) xRotated, (int) yRotated);
    }
}
