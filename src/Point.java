// 207789140 Sondos Zoabi

/**
 * identifying object point.
 */
public class Point {
    private double x, y;

    /**
     * constructor of object point.
     * @param x the value of x coordinate.
     * @param y the value of y coordinate.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * calculating distance between to points.
     * @param other the point to caculate the distance with.
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt((dx * dx + dy * dy));
    }

    /**
     * checks whether two points are equal.
     * @param other type point.
     * @return true is the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return (this.x == other.getX() && this.y == other.getY()) ? true : false;
    }
    // Return the x and y values of this point

    /**
     * @return the x value of the point.
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y value of the point.
     */
    public double getY() {
        return y;
    }
}