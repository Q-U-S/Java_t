/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new CompareSlope();       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    public static class CompareSlope implements Comparator<Point>{

	    public int compare(Point p1, Point p2){
		if((p1.y - y)/( p1.x - x) < (p2.y - y)/( p2.x - x))return 1; 
		
		if((p1.y - y)/( p1.x - x) > (p2.y - y)/( p2.x - x))return -1;
		
		return 0;
		
	    }
	    boolean equal(Point p1, Point p2){
		if((p1.y - y)/( p1.x - x) == (p2.y - y)/( p2.x - x))return true;
		return false;
	    }
    }
    

    
    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
	double dSlope = 0.0;
	
	dSlope = (that.y - this.y)/(that.x - this.x);
	
	if(dSlope == -0.0) dSlope = 0.0;
	
	return dSlope;
        /* YOUR CODE HERE */
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
	if(that.y < this.y) return 1;
	if(that.y > this.y) return -1;
	if(that.y == this.y && that.x > this.x) return -1;
	return 0;
        /* YOUR CODE HERE */
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}
