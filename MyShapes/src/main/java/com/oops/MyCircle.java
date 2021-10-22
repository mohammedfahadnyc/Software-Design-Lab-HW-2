package com.oops;

import java.util.HashSet;
import java.util.Set;

public class MyCircle extends MyOval {

    public MyCircle(MyPoint point, int radius, MyColor color) {
        super(point, 2*radius, 2*radius, color);
    }
    
    /**
     * Returns the radius of MyCircle
     * @return
     */
    public int getRadius() {
        return (int) (this.rectangle.getWidth() / 2);
    }
    
    /**
     * String representation of MyCircle 
     */
    @Override
    public String toString() {
        return 
            "["+getClass().getName() +
            " : Coordinates:" + point + 
            "; Radius:"+getRadius() + 
            "; Color:"+color +  
            "; Area:"+getArea() +  
            "; Perimeter:"+getPerimeter() +
            "]";
    }
    
    /**
     * Returns all MyPoints lying within the MyShape
     */
    @Override
    public Set<MyPoint> getMyPoints() {
        
        double pointX = getX();
        double pointY = getY();
        
        Set<MyPoint> points = new HashSet<>();
        
        for (double x = (pointX - getRadius()); x <= getRadius() * 2 + pointX; x++) {
            for (double y = (pointY - getRadius()); y <= getRadius() * 2 + pointY; y++) {
                double dx = x - pointX;
                double dy = y - pointY;
                double distanceSquared = Math.pow(dx, 2) + Math.pow(dy, 2);
                if (distanceSquared <= Math.pow(getRadius(), 2)) {
                    points.add(new MyPoint((int)x, (int)y));
                }
            }
        }
        
        return points;
    }    
}
