package com.oops;

import java.util.Set;

import javafx.scene.canvas.GraphicsContext;

/**
 * MyShape class with related methods
 */
@SuppressWarnings("restriction")
public class MyShape implements MyShapeInterface {
    
    MyPoint point;
    protected MyColor color;

    /**
     * Constructor with individual points for coordinate and a color
     */
    public MyShape(double x, double y, MyColor color) {
        this( new MyPoint(x, y), color);
    }

    /**
     * Constructor with coordinate and a color
     */
    public MyShape(MyPoint point, MyColor color) {
        this.point = point;
        this.color = color;
    }

    /**
     * Returns color of a shape
     */
    public MyColor getColor() {
        return color;
    }
    
    /**
     * Sets the color of a shape
     */
    public void setColor(MyColor color) {
        this.color=color;
    }
    
    /**
     * Returns the point (x, y)
     */
    public MyPoint getPoint() {
        return point;
    }
    
    /**
     * Sets the point (x, y)
     */
    public void setPoint(MyPoint point) {
        this.point = point;
    }
    
    /**
     * Returns a string representation of this shape.
     */
    public String toString() {
        return 
            "["+getClass().getName() +
            " : Coordinates:" + point +
            "; Color:"+color + 
            "; Area:"+getArea() + 
            "; Perimeter:"+getPerimeter() 
            + "]";
    }
    
    /**
     * Draws a shape on a canvas
     */
    @SuppressWarnings("restriction")
    public void draw(GraphicsContext graphicsCtx) {
        
        graphicsCtx.setFill(color.getMyColor());
        
        graphicsCtx.fillRect(0, 0, point.getX()*2, point.getY()*2);
        
        System.out.println("Drawing "+this.toString());
    }    

    /**
     * Returns Area of shape (ZERO for MyShape) 
     */
    public double getArea() {
        return ZERO;
    }
    
    /**
     * Returns Perimeter of shape (ZERO for MyShape) 
     */
    public double getPerimeter() {
        return ZERO;
    }

    /**
     * Returns the bounding rectangle of the MyShape
     */
    @Override
    public MyRectangle getMyBoundingRectangle() {
        throw new UnsupportedOperationException("Base class (MyShape) doesnt support this function");
    }

    /**
     * Returns true if MyPoint p is located within
     * or on boundary of the shape
     */
    @Override
    public boolean pointInMyShape(MyPoint myPoint) {

        if (myPoint == null) {
            return false;
        }
        
        return (getMyPoints().contains(myPoint));
    }
    
    /**
     * Returns all MyPoints lying within the MyShape
     */
    public Set<MyPoint> getMyPoints() {
        throw new UnsupportedOperationException("Base class (MyShape) doesnt support this function");
    }

}
