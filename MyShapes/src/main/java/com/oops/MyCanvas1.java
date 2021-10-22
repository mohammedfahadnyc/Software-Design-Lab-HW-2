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
public class MyCanvas1 extends Application {

    // Default Canvas size
    double canvasWidth = 600; 
    double canvasLength = 600;

    /**
     * Default Constructor 
     * defines a default canvas size 
     */

    public MyCanvas1() {
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

        int radius1 = (int)(canvasLength/2) - 25;

        // Draws first outer circle
        MyCircle myCircle1 = new MyCircle(center, radius1, MyColor.RED);
        myCircle1.draw(graphicsCtx);
        
        // Draws first polygon inside the outer circle
        MyPolygon myPolygon1 = new MyPolygon(center, 5, radius1, MyColor.LIME);
        myPolygon1.draw(graphicsCtx);

        int radius2 = (int) (myPolygon1.getApothem());

        // Draws second circle inside the polgon
        MyCircle myCircle2 = new MyCircle(center, radius2, MyColor.BLUE);
        myCircle2.draw(graphicsCtx);

        // Draws second polygon inside the second circle
        MyPolygon myPolygon2 = new MyPolygon(center, 5, radius2, MyColor.YELLOW);
        myPolygon2.draw(graphicsCtx);

        int radius3 = (int) (myPolygon2.getApothem());

        // Draws third circle inside the 2nd polgon
        MyCircle myCircle3 = new MyCircle(center, radius3, MyColor.CYAN);
        myCircle3.draw(graphicsCtx);

        // Draws 3rd polygon inside the 3rd circle
        MyPolygon myPolygon3 = new MyPolygon(center, 5, radius3, MyColor.MAGENTA);
        myPolygon3.draw(graphicsCtx);

        int radius4 = (int) (myPolygon3.getApothem());

        // Draws fourth circle inside the third polgon
        MyCircle myCircle4 = new MyCircle(center, radius4, MyColor.SILVER);
        myCircle4.draw(graphicsCtx);
        
        // Draws diagonal line (top left to bottom right)
        MyLine myLine = new MyLine(0,  0, (int)canvasWidth,  (int)canvasLength, MyColor.BLACK);
        myLine.draw(graphicsCtx);
 
        // Draws diagonal line (top right to bottom left)
        MyLine myLine2 = new MyLine(0,  (int)canvasLength, (int)canvasWidth, 0, MyColor.BLACK);
        myLine2.draw(graphicsCtx);
    }
    
}
