package org.example.finalproject;

public abstract class Fractal extends Polygon {
    /** the base shape */
    private final Polygon baseShape;

    /** the number of levels */
    private int numLevels;

    /**
     * Create a fractal of the base shape with the number of levels
     * @param baseShape the base shape
     * @param numLevels the number of levels
     */
    public Fractal(Polygon baseShape, int numLevels) {
        super(baseShape.getPoints());
        this.baseShape = baseShape;
        this.numLevels = numLevels;

        // default to the center at the origin
        super.setCenter(new Point(0.0,0.0));
    }

    /**
     * Returns the base shape
     * @return the base shape
     */
    public Polygon getBaseShape() {
        return baseShape;
    }

    /**
     * Returns the number of levels
     * @return the number of levels
     */
    public int getNumLevels() {
        return numLevels;
    }

    /**
     * Sets the number of levels to the input
     * @param numLevels the new number of levels
     */
    public void setNumLevels(int numLevels) {
        this.numLevels = numLevels;
    }
}