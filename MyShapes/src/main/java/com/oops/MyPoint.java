package com.oops;

/**
 * MyPoint class with related methods
 * Represents a coordinate on a canvas
 */
public class MyPoint {
    
    private double x = 0;
    private double y = 0;

    /**
     * Constructor with individual points of a coordinate
     */
    public MyPoint(double x, double y) {
        this.x = x; 
        this.y = y;
    }

    /**
     * Returns X point of a coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Sets X point of a coordinate
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Returns Y point of a coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * Sets Y point of a coordinate
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Sets X and Y point of a coordinate
     */
    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * String representation of MyPoint 
     */
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    /**
     * Calculates the hash code of MyPoint
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Checked the current MyPoint with another.
     * Returns true if both are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MyPoint other = (MyPoint) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
            return false;
        if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
            return false;
        return true;
    }
}