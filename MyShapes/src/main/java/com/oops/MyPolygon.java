package com.oops;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.scene.canvas.GraphicsContext;

@SuppressWarnings("restriction")
public class MyPolygon extends MyShape {

    // Setting variables
    private int sides; // Number of the polygon’s equal side lengths and equal interior angles
    private int radius; // Radius
    
    public MyPolygon(MyPoint point, int sides, int radius, MyColor color) {
        super(point, color);
        this.sides = sides;
        this.radius = radius;
    }
    
    /**
     * Returns the radius of a polygon
     */
    public int getRadius() {
        return this.radius;
    }
    
    /**
     * Returns the no of sides of a polygon
     */
    public int getSides() {
        return this.sides;
    }
    
    /**
     * Returns the center point of a polygon
     */
    public MyPoint getCenter() {
        return this.point;
    }

    /**
     * Calculates and returns the angle (in degree) of a polygon
     */
    public double getAngle() { 
        return (180 * (getSides() - 2))/getSides(); 
    }

    /**
     * Calculates and returns the length of a side of a polygon
     */
    public double getSide() { 
        return (2 * getRadius()) * Math.sin(Math.PI/getSides()); 
    }
    
    /**
     * Calculates and returns the area of a polygon 
     */
    @Override
    public double getArea () { 
        return (Math.pow(getSide(), 2) * getSides()) / (4 * Math.tan(Math.PI / getSides())); 
    }
    
    /**
     * Calculates and returns the perimeter of a polygon 
     */
    @Override
    public double getPerimeter() { 
        return sides*getSide(); 
    }

    /**
     * Calculates and returns the apothem of a polygon 
     */
    public double getApothem() { 
        
        return this.getRadius() * (Math.cos(Math.PI/getSides()));
    }
    
    /**
     * String representation of MyOval 
     */
    @Override
    public String toString() {
        return 
            "["+getClass().getName() +
            " : Coordinates:" + point + 
            "; Color:"+color +  
            "; Radius:"+getRadius() + 
            "; Sides:"+getSides() + 
            "; Angle:"+getAngle() + 
            "; Side Length:"+getSide() +
            "; Apothem:"+getApothem() +
            "; Area:"+getArea() +  
            "; Perimeter:"+getPerimeter() +
            "]";
    }
    
    
    /**
     * Draws a polygon on a canvas
     */
    @Override
    @SuppressWarnings("restriction")
    public void draw(GraphicsContext graphicsCtx) {
        
        double[] xPoints = new double[sides];
        double[] yPoints = new double[sides];
        
        double angle = Math.toRadians(getAngle()) * sides + Math.PI/2; // get the angle to go to the next value
        
        for(int i = 0; i < sides; i++){
            xPoints[i] = getRadius() * Math.cos(angle) + getPoint().getX(); // get the x value of the point
            yPoints[i] = getRadius() * Math.sin(angle) + getPoint().getY(); // get the y value of the point
            angle += 2*(Math.PI/sides); // get next angle
        }
        
        graphicsCtx.setFill(color.getMyColor());
        graphicsCtx.fillPolygon(xPoints, yPoints, sides); // fill polygon
        
        System.out.println("Drawing "+this.toString());
    }
  
    /**
     * Returns the bounding rectangle of the MyShape
     */
   @Override
   public MyRectangle getMyBoundingRectangle() {
       MyRectangle output = new MyRectangle(getPoint(), 2* getRadius(), 2*getRadius(), getColor());  
       return output;
   }
    
    private List<MyPoint> getVertices(double r) {
        
        List<MyPoint> vertices = new ArrayList<>(getSides());
        
        double angle = (2 * Math.PI) / getSides();
        for (int index = 0; index < sides; ++index) {
            vertices.add(
                new MyPoint(point.getX() + r * -Math.sin((index + 1) * angle), point.getY() + r * -Math.cos((index + 1) * angle))
            );
        }
        
        return vertices;
    }
    
    /**
     * Returns all MyPoints lying within the MyShape
     */
    public Set<MyPoint> getMyPoints() {
        
        Set<MyLine> lines = new HashSet<>((int)(sides * getRadius()));
        
        List<MyPoint> vertices;
        for (double r = 0; r < getRadius(); ++r) {
            vertices = getVertices(r);
            for (int i = 0; i < sides; ++i) {
                lines.add(new MyLine(vertices.get(i), vertices.get((i + 1) % sides), getColor()));
            }
            vertices.clear();
        }
        
        Set<MyPoint> points = new HashSet<>();
        for (MyLine line : lines) {
            points.addAll(line.getMyPoints());
        }
        
        return points;
    }
}
