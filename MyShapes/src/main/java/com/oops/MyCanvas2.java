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
public class MyCanvas2 extends Application {


    // Default Canvas size
    double canvasWidth = 600;
    double canvasLength = 600;

    /**
     * Default Constructor 
     * defines a default canvas size 
     */

    public MyCanvas2() {
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
     * Draws custom shapes intersecting with each other
     */
    private void drawMyShapes(GraphicsContext graphicsCtx) {

        double canvasWidth = graphicsCtx.getCanvas().getWidth();
        double canvasLength = graphicsCtx.getCanvas().getHeight();

        // Draw MyShape
        MyPoint center = new MyPoint(canvasWidth/2, canvasLength/2);
        MyShape myShape = new MyShape(center, MyColor.WHITE);
        myShape.draw(graphicsCtx);

        // Two rectangles
        MyPoint pointRectangle1 = new MyPoint(canvasWidth/8, canvasLength/6);
        MyRectangle myRectangle1 = new MyRectangle(pointRectangle1, canvasWidth/6, canvasLength/5, MyColor.RED);
        myRectangle1.draw(graphicsCtx);

        MyPoint pointRectangle2 = new MyPoint(canvasWidth*2/8, canvasLength*2/8);
        MyRectangle myRectangle2 = new MyRectangle(pointRectangle2, canvasWidth/4, canvasLength/4, MyColor.LIME);
        myRectangle2.draw(graphicsCtx);
        
        myRectangle1.drawIntersectMyShape(myRectangle2, MyColor.BLUE, graphicsCtx);
        
        
        // One rectangle and one circle
        int radius1 = (int)(canvasLength/4) - 20;
        MyPoint pointCircle1 = new MyPoint(canvasWidth*3/4, canvasLength/4);
        MyCircle myCircle1 = new MyCircle(pointCircle1, radius1, MyColor.YELLOW);
        myCircle1.draw(graphicsCtx);        
        
        MyPoint pointRectangle3 = new MyPoint(canvasWidth*7/8, canvasLength*2/7);
        MyRectangle myRectangle3 = new MyRectangle(pointRectangle3, canvasWidth/5, canvasLength/4, MyColor.CYAN);
        myRectangle3.draw(graphicsCtx);
        
        myRectangle3.drawIntersectMyShape(myCircle1, MyColor.MAGENTA, graphicsCtx);
        
        
        // One rectangle and one polygon
        MyPoint pointRectangle4 = new MyPoint(canvasWidth*3/8, canvasLength*6/8);
        MyRectangle myRectangle4 = new MyRectangle(pointRectangle4, canvasWidth/5, canvasLength/5, MyColor.GRAY);
        myRectangle4.draw(graphicsCtx);
        
        // Draws second circle inside the polygon
        int radius2 = (int)(canvasLength/4) - 20;
        MyPoint pointPolygon1 = new MyPoint(canvasWidth*4/8, canvasLength*6/8);
        MyPolygon myPolygon1 = new MyPolygon(pointPolygon1, 5, radius2, MyColor.MAROON);
        myPolygon1.draw(graphicsCtx);
        
        myPolygon1.drawIntersectMyShape(myRectangle4, MyColor.OLIVE, graphicsCtx);
    }
    
}
