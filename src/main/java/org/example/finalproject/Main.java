package org.example.finalproject;

public class Main {
    public static void main(String[] args) {
//        Triangle triangle = new Triangle(new Point(0.0,0.0), new Point(400.0,0.0), new Point(200.0,300.0));
        NGon nGon = new NGon(new Point(0.0,0.0),300);
        nGon.setNumSides(3);

        SnowflakeFractal snowflakeFractal = new SnowflakeFractal(nGon,2);
        for (Line line : snowflakeFractal.getLines()) {
            System.out.println(line);
        }

        System.out.println("Now setting num levels");

        snowflakeFractal.setNumLevels(5);
        snowflakeFractal.rotate(Math.PI / 2);
        snowflakeFractal.setNumLevels(3);

//        DrawingPad pad = new DrawingPad(800, 800);
//
//        pad.draw(snowflakeFractal);

        for (Line line : snowflakeFractal.getLines()) {
            System.out.println(line + "ug");
        }
    }
}