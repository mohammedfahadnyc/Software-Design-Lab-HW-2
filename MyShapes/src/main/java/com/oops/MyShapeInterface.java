package com.oops;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

@SuppressWarnings("restriction")
public interface MyShapeInterface {

    final int ZERO = 0;
    final double ONE = 1;
    
    /**
     * Returns all MyPoints lying within the MyShape
     */
    Set<MyPoint> getMyPoints();
    
    /**
     * Returns the bounding rectangle of the MyShape
     */
    abstract MyRectangle getMyBoundingRectangle();
    
    /**
     * Returns true if MyPoint p is located within
     * or on boundary of the shape
     */
    abstract boolean pointInMyShape(MyPoint p);

    /**
     * Returns all MyPoints of intersecting area of two MyShapes
     * Returns null in case of two MyShapes are not overlapping
     */
    public static Set<MyPoint> intersectMyShapes(MyShapeInterface myShape1, MyShapeInterface myShape2){
        
        // Any shape null
        if (myShape1 == null || myShape2 == null) {
            return null;
        }
        
        // Get area of two shapes
        Set<MyPoint> s1Points= myShape1.getMyPoints();
        Set<MyPoint> s2Points = myShape2.getMyPoints();

        if (s1Points.isEmpty() || s2Points.isEmpty()) {
            return null;
        }

        
        // Find intersection of two shapes
        Set<MyPoint> intersection = new HashSet<>();

        for (MyPoint myPoint : s2Points) {
            if (s1Points.contains(myPoint)) {
                intersection.add(myPoint);
            }
        }

        // If they share nothing, return null
        if (intersection.isEmpty()) {
            return null;
        }

        return intersection;
    }
    
    /**
     * Returns the canvas with a area of intersection in specified color 
     */
    default Canvas drawIntersectMyShape(MyShape shape2, MyColor myColor, GraphicsContext graphicsCtx) {
        Set<MyPoint> intersection = null;

        intersection = intersectMyShapes(this, shape2);
        
        if (intersection == null) {
            graphicsCtx.getCanvas();
        }

        graphicsCtx.setFill(myColor.getMyColor());
        for (MyPoint myPoint : intersection) {
            graphicsCtx.fillOval(myPoint.getX(), myPoint.getY(), 1, 1);
        }
        
        return graphicsCtx.getCanvas();
    }
}
