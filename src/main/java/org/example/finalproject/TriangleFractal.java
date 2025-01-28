package org.example.finalproject;

public class TriangleFractal extends Fractal{
    /**
     * Create a fractal of the base shape with the number of levels
     *
     * @param baseShape the base shape
     * @param numLevels the number of levels
     */
    public TriangleFractal(Triangle baseShape, int numLevels) {
        super(baseShape, numLevels);
        setNumLevels(numLevels);
    }

    @Override
    public void setNumLevels(int numLevels) {
        LinkedList<Triangle> triangleLinkedList = new LinkedList<>();
        triangleLinkedList.addToFront((Triangle) getBaseShape());
        for (int i = 0; i < numLevels; i++) {
            LLNode<Triangle> triangleNode = triangleLinkedList.getFirstNode();
            while (triangleNode != null) {
                Point topRight = new Point(0.0,0.0);
                triangleLinkedList.addToFront(new Triangle(triangleNode.getElement().getPoints()[0],triangleNode.getElement().getPoints()[1],triangleNode.getElement().getPoints()[2]));
            }
        }
    }
}
