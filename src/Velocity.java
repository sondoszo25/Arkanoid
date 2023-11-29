// 207789140 Sondos Zoabi
/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    // constructor
    private double dx;
    private double dy;

    /**
     * Velocity constructor.
     * @param dx change in the position on the x coordinate.
     * @param dy change in the position on the y coordinate.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p the point to apply the change on it.
     * @return new value of x & y .
     */
    public Point applyToPoint(Point p) {
        double x = p.getX();
        double y = p.getY();
        Point d = new Point(x + this.dx, y + this.dy);
        return d;
    }

    /**
     * @return the change in the position on the x coordinate.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return change in the position on the y coordinate.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * converts the velocity from (angle and speed) to the change in position on the `x` and the `y` axes.
     * @param angle direction in degrees.
     * @param speed units.
     * @return velocity.
     */
    public  Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = Math.toRadians(angle);
        return new Velocity((speed * Math.cos(angle)), -1 * (speed * Math.sin(angle)));
    }
}