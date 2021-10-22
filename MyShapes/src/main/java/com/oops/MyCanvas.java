package com.oops;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.Stage;

/**
 * JavaFX application that launches the UI
 * and displays different shapes
 */
@SuppressWarnings("restriction")
public class MyCanvas extends Application {


    // Default Canvas size
    double canvasWidth = 1000;
    double canvasLength = 600;


    /**
     * Default Constructor 
     * defines a default canvas size 
     */

    public MyCanvas() {
        this.canvasWidth = 1000;
        this.canvasLength = 600;
    }

    /**
     * Launches the application and UI
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes the UI components
     */
    @Override
    public void start(Stage stage) {

        identifyCanvasSize();

        stage.setTitle("My Canvas with My Shapes");

        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas canvas = new Canvas(this.canvasWidth, this.canvasLength);
        GraphicsContext graphicsCtx = canvas.getGraphicsContext2D();

        drawMyShapes(graphicsCtx);

        root.getChildren().add(canvas);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method prompts user for below 2 and set the canvas size.
     * 1. Checks if user wants a default canvas 
     * 2. If not default, prompts user to select size from the options 
     */
    private void identifyCanvasSize() {

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alertYesNo = new Alert(AlertType.CONFIRMATION,
                "Do you want to change the size of canvas ?", yes, no);

        alertYesNo.setTitle("My Canvas with My Shapes");
        alertYesNo.showAndWait();
        ButtonType result = alertYesNo.getResult();

        if (result == yes) {

            // Prompts different sizes of canvas, User will need to select the size
            String[] options = { "333 x 200", "666 x 400", "1000 x 600", "1333 x 800" };
            ChoiceDialog dialog = new ChoiceDialog(options[0], options);
            dialog.setHeaderText("My Canvas with My Shapes");
            dialog.setContentText("Select Canvas Size");
            dialog.showAndWait();
            Object selection  = dialog.getSelectedItem();

            if (selection!=null) {
                String strSize = selection.toString();               
                String[] sizes = strSize.split("x");

                // Selected canvas size
                this.canvasWidth = Double.parseDouble(sizes[0]);
                this.canvasLength = Double.parseDouble(sizes[1]);
            }
        }
    }

    /**
     * Draws custom shapes with different size as per the instruction
     */
    private void drawMyShapes(GraphicsContext graphicsCtx) {

        double canvasWidth = graphicsCtx.getCanvas().getWidth();
        double canvasLength = graphicsCtx.getCanvas().getHeight();


        // Draw MyShape
        MyPoint center = new MyPoint(canvasWidth/2, canvasLength/2);
        MyShape myShape = new MyShape(center, MyColor.WHITE);
        myShape.draw(graphicsCtx);

        double width1 = 750.0/1000;
        double length1 = 450.0/600;

        // Draws first outer rectangle
        MyRectangle myRectangle1 = new MyRectangle(center, canvasWidth * width1, canvasLength * length1, MyColor.RED);
        myRectangle1.draw(graphicsCtx);

        // Draws first oval inside the outer rectangle
        MyOval myOval1 = new MyOval(center, canvasWidth * width1, canvasLength * length1, MyColor.CYAN);
        myOval1.draw(graphicsCtx);


        double width2 = 525.0/1000;
        double length2 = 320.0/600;

        // Draws 2nd rectangle inside the oval
        MyRectangle myRectangle2 = new MyRectangle(center, canvasWidth * width2, canvasLength * length2, MyColor.GREEN);
        myRectangle2.draw(graphicsCtx);

        // Draws oval insize the 2nd rectangle
        MyOval myOval2 = new MyOval(center, canvasWidth * width2, canvasLength * length2, MyColor.MAGENTA);
        myOval2.draw(graphicsCtx);


        double width3 = 375.0/1000;
        double length3 = 225.0/600;

        // Draws 3rd rectangle inside the 2nd oval
        MyRectangle myRectangle3 = new MyRectangle(center, canvasWidth * width3, canvasLength * length3, MyColor.BLUE);
        myRectangle3.draw(graphicsCtx);

        // Draws 3rd oval inside the 3rd rectangle
        MyOval myOval3 = new MyOval(center, canvasWidth * width3, canvasLength * length3, MyColor.YELLOW);
        myOval3.draw(graphicsCtx);

        // Draws diagonal line (top left to bottom right)
        MyLine myLine = new MyLine(0,  0, (int)canvasWidth,  (int)canvasLength, MyColor.BLACK);
        myLine.draw(graphicsCtx);

    }
}
