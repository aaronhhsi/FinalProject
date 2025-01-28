package org.example.finalproject;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Aaron Hsi
 */
public class JavaFXDrawer extends Application {
    /** the number of sides */
    int sides = 3;

    /** the number of levels */
    int levels = 0;

    /** the Snowflake Fractal being drawn */
    SnowflakeFractal snowflakeFractal;

    /** the default side length of the fractal */
    int defaultSize;

    /** the default rotation of the fractal */
    double defaultRotation;

    /**
     * Draws everything onto the stage
     * @param stage the stage
     */
    @Override
    public void start(Stage stage) {
        defaultSize = 200;
        defaultRotation = 0.0;
        BorderPane layout = new BorderPane();
        Scene scene = new Scene(layout);
        Canvas canvas = new Canvas(800,800);

        /** creates the slider for adjusting the side length */
        Slider sideLength = new Slider(10,800,defaultSize);
        sideLength.setOrientation(Orientation.VERTICAL);
        sideLength.setShowTickMarks(true);
        sideLength.setShowTickLabels(true);
        sideLength.setMajorTickUnit(10);
        sideLength.setBlockIncrement(1);
        sideLength.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                ((RegularPolygon) snowflakeFractal.getBaseShape()).setSideLength(t1.doubleValue());
                snowflakeFractal.setNumLevels(levels);         // used to recalculate points after changing side length
                paint(canvas, snowflakeFractal);
            }
        });

        /** creates the slider for adjusting the rotation */
        Slider rotation = new Slider(0,360,defaultRotation);
        rotation.setOrientation(Orientation.VERTICAL);
        rotation.setShowTickMarks(true);
        rotation.setShowTickLabels(true);
        rotation.setMajorTickUnit(10);
        rotation.setBlockIncrement(1);
        rotation.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                double rotate = (t1.doubleValue() - defaultRotation);
                defaultRotation = t1.doubleValue();
                snowflakeFractal.rotate(rotate * Math.PI / 180);
                paint(canvas, snowflakeFractal);
                System.out.println(defaultRotation);
            }
        });

        /** draws the base snowflake fractal */
        snowflakeFractal = new SnowflakeFractal(new EquilateralTriangle(new Point(0.0,0.0), sideLength.getValue()),levels);
        paint(canvas, snowflakeFractal);

        /** adds the buttons to change the number of sides */
        Button decreaseSides = new Button("<<");
        Button sidesCount = new Button("Sides: " + sides);
        Button increaseSides = new Button(">>");
        FlowPane flowSides = new FlowPane(decreaseSides, sidesCount, increaseSides);

        /** changes the buttons behaviors when clicked */
        decreaseSides.setOnAction(actionEvent -> {
            if (sides > 3) {
                sidesCount.setText("Sides: " + --sides);
            }
            if (sides == 3) {
                snowflakeFractal = new SnowflakeFractal(new EquilateralTriangle(new Point(0.0,0.0), sideLength.getValue()),levels);
            } else if (sides == 4) {
                snowflakeFractal = new SnowflakeFractal(new Square(new Point(0.0,0.0), sideLength.getValue()),levels);
            } else {
                NGon nGon = new NGon(new Point(0.0,0.0), sideLength.getValue());
                nGon.setNumSides(sides);
                snowflakeFractal = new SnowflakeFractal(nGon,levels);
            }
            paint(canvas, snowflakeFractal);
        });
        increaseSides.setOnAction(actionEvent -> {
            sidesCount.setText("Sides: " + ++sides);
            if (sides == 4) {
                snowflakeFractal = new SnowflakeFractal(new Square(new Point(0.0,0.0), sideLength.getValue()),levels);
            } else {
                NGon nGon = new NGon(new Point(0.0,0.0), sideLength.getValue());
                nGon.setNumSides(sides);
                snowflakeFractal = new SnowflakeFractal(nGon,levels);
            }
            paint(canvas, snowflakeFractal);
        });

        /** adds the buttons to change the levels of the fractal */
        Button decreaseLevels = new Button("<<");
        Button levelsCount = new Button("Levels: " + levels);
        Button increaseLevels = new Button(">>");
        FlowPane flowLevels = new FlowPane(decreaseLevels, levelsCount, increaseLevels);

        /** changes the buttons behaviors when clicked */
        decreaseLevels.setOnAction(actionEvent -> {
            if (levels > 0) {
                levelsCount.setText("Levels: " + --levels);
                snowflakeFractal.setNumLevels(levels);
                paint(canvas, snowflakeFractal);
            }
        });
        increaseLevels.setOnAction(actionEvent -> {
            levelsCount.setText("Levels: " + ++levels);
            snowflakeFractal.setNumLevels(levels);
            paint(canvas, snowflakeFractal);
        });

        /** create a VBox for the buttons controlling the number of sides and levels */
        VBox vBox = new VBox(flowSides, flowLevels);

        /** lays out everything onto the border pane called "layout" */
        layout.setCenter(canvas);
        layout.setRight(sideLength);
        layout.setBottom(vBox);
        layout.setLeft(rotation);
        flowSides.setAlignment(Pos.BOTTOM_CENTER);
        flowLevels.setAlignment(Pos.BOTTOM_CENTER);



        /** shows everything on a window */
        stage.setTitle("Snowflake Fractal");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Paints the inputted Snowflake Fractal onto the inputted canvas
     * @param canvas the canvas
     * @param snowflakeFractal the Snowflake Fractal
     */
    private void paint(Canvas canvas, SnowflakeFractal snowflakeFractal) {
        canvas.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        canvas.getGraphicsContext2D().strokeLine(canvas.getWidth() / 2, 0, canvas.getWidth() / 2, canvas.getHeight());
        canvas.getGraphicsContext2D().strokeLine(0, canvas.getHeight() / 2, canvas.getWidth(), canvas.getHeight() / 2);
        for (Line line : snowflakeFractal.getLines()) {
            canvas.getGraphicsContext2D().strokeLine(line.getFirstPoint().getX() + canvas.getWidth() / 2, canvas.getHeight() / 2 - line.getFirstPoint().getY(), line.getSecondPoint().getX() + canvas.getWidth() / 2, canvas.getHeight() / 2 - line.getSecondPoint().getY());
        }
    }

    /** launches the program */
    public static void main(String[] args) {
        Application.launch(args);
    }
}