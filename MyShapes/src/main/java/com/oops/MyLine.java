package com.oops;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.canvas.GraphicsContext;

/**
 * MyLine class with related methods
 */
@SuppressWarnings({ "restriction" })
public class MyLine extends MyShape {
    
    MyPoint point2;
    
    /**
     * Constructor with individual points for 2 coordinates and a color
     */
    public MyLine(double x1, double y1, double x2, double y2, MyColor color) {
        this(new MyPoint(x1, y1), new MyPoint(x2, y2), color);
    }

    /**
     * Constructor with 2 coordinates and a color
     */
    public MyLine(MyPoint point1,MyPoint point2, MyColor color) {
        super(point1, color);
        this.point2 = point2;
    }

    /**
     * Returns 2nd coordinate of a line 
     */
    public MyPoint getPoint2() {
        return this.point2;
    }
    
    /**
     * Sets 2nd coordinate of a line 
     */
    public void setPoint2(MyPoint point) {
        this.point2 = point;
    }
    
    /**
     * String representation of MyLine 
     */
    @Override
    public String toString() {
        return 
            "["+getClass().getName() +
            " : Coordinates:" + point + "," +point2 +
            "; Color:"+color +  
            "; Angle:"+getAngle() + 
            "; Length:"+getLength() +
            "; Area:"+getArea() +  
            "; Perimeter:"+getPerimeter() +
            "]";
    }
    
    /**
     * Draws a line on canvas between given coordinates 
     */
    @SuppressWarnings("restriction")
    public void draw(GraphicsContext graphicsCtx) {
        
        graphicsCtx.strokeLine(point.getX() , point.getY() , point2.getX() , point2.getY());
        
        System.out.println("Drawing "+this.toString());
    }    
    
    /**
     * Calculates and returns the length of a line 
     */
    public double getLength() {
        return Math.sqrt(Math.pow(point2.getX() - point.getX() , 2) + Math.pow(point2.getY() - point.getY() , 2));
    }    
    
    /**
     * Calculates and returns the angle (in degree) of a line 
     */
    public double getAngle() {
        double angle;
        double tantheta = (point2.getY() - point.getY()) / (point2.getX() - point.getX());
        angle = Math.atan(tantheta);
        angle = Math.toDegrees(angle);
        return angle;
    }

    /**
     * Returns the bounding rectangle of the MyShape
     */
    @Override
    public  MyRectangle getMyBoundingRectangle() {

        MyPoint myPoint = new MyPoint(point.getX() < point2.getX() ? point.getX() : point2.getX(), point.getY() < point2.getY() ? point.getY() : point2.getY());
        
        return new MyRectangle(myPoint, Math.abs(point2.getX() - point.getX()), Math.abs(point2.getY() - point.getY()), color);
    }

    /**
     * Returns all MyPoints lying within the MyShape
     */
    @Override
    public Set<MyPoint> getMyPoints() {
        
        Set<MyPoint> points = new HashSet<>();
        
        final double slope = (point2.getY() - point.getY()) / (point2.getX() - point.getX());
        final double b = point.getY() - (slope * point.getX());
        
        final MyRectangle myBound = getMyBoundingRectangle();
        
        for (int index1 = (int)myBound.getX(); index1 <= myBound.getX() + myBound.getWidth(); ++index1) {
            for (int index2 = (int)myBound.getY(); index2 <= myBound.getY() + myBound.getHeight(); ++index2) {
                if (Math.abs(index2 - (slope * index1 + b)) < ONE) {
                    points.add(new MyPoint((int)index1, (int)index2));
                }
            }
        }
        
        if (Math.abs(getAngle() - 90) < ONE) {
            for (int index = 0; index < getLength(); index++) {
                points.add(new MyPoint((int)point.getX(), (int)point.getY() + index));
            }
        } else if (Math.abs(getAngle() - 90 * 3) < ONE) {
            for (int index = 0; index < getLength(); index++) {
                points.add(new MyPoint((int)point.getX(), (int)point.getY() - index));
            }
        }
    
        return points;
    }
}
