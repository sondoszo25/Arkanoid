// 207789140 Sondos Zoabi

import java.util.ArrayList;
import java.util.List;

/**
 * identifying object Line.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * constructor of object Line.
     * @param start starting point of the line.
     * @param end   ending point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor of object Line.
     *
     * @param x1 the x coordinate of starting point of the line.
     * @param y1 the y coordinate of starting point of the line.
     * @param x2 the x coordinate of ending point of the line.
     * @param y2 the y coordinate of ending point of the line.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * calculating the length of the line.
     * @return line's length.
     */
    public double length() {
        double dx = start().getX() - end().getX();
        double dy = start().getY() - end().getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * finds the middle point of line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double a = (start().getX() + end().getX()) / 2;
        double b = (start().getY() + end().getY()) / 2;
        Point middle = new Point(a, b);
        return middle;
    }

    /**
     * @return the start point of the line
     */
    public Point start() {
        return start;
    }

    /**
     * @return the end point of the line
     */
    public Point end() {
        return end;
    }

    /**
     * tells whether this line intersects with the "other" line, the passed
     * parameter to this function.
     *
     * @param other type Line.
     * @return true - if they're intersecting, else false.
     */
    public boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) != null) {
            return true;
        }
        return false;
    }

    /**
     * @param other type Line.
     * @return the intersection point if the lines intersect,and null otherwise.
     */
    public Point intersectionWith(Line other) {
        double m1, m2, b1, b2, x, y;
        Point d;
        if ((this.start().getX() == this.end().getX()) && (other.start().getX() != other.end().getX())) {
            x = this.start().getX();
            m2 = (other.start().getY() - other.end().getY()) / (other.start().getX() - other.end().getX());
            b2 = other.start().getY() - m2 * other.start().getX();
            y = m2 * x + b2;
            if (other.start().getY() == other.end().getY()) {
                y = other.start().getY();
            }
        } else if ((this.start().getX() == this.end().getX()) && (other.start().getX() == other.end().getX())) {
            if (this.start().getX() == other.start().getX()) {
                x = start().getX();
                y = start().getX();
            } else {
                return null;
            }
        } else if ((this.start().getX() != this.end().getX()) && (other.start().getX() == other.end().getX())) {
            x = other.start().getX();
            m1 = (this.start().getY() - this.end().getY()) / (this.start().getX() - this.end().getX());
            b1 = this.start().getY() - m1 * this.start().getX();
            y = m1 * x + b1;
            if ((this.start().getY() == this.end().getY())) {
                y = this.start().getY();
            }
        } else {
            m1 = (this.start().getY() - this.end().getY()) / (this.start().getX() - this.end().getX());
            m2 = (other.start().getY() - other.end().getY()) / (other.start().getX() - other.end().getX());
            // y-intercept.
            b1 = this.start().getY() - m1 * this.start().getX();
            b2 = other.start().getY() - m2 * other.start().getX();
            // find the intersection point.
            x = (b2 - b1) / (m1 - m2);
            y = m1 * x + b1;
            if (this.equals(other)) {
                return this.start();
            }
            if ((m1 == m2)) {
                return null; // they're parallel.
            }
        }
        // d is intersection point of two lines
        y = Math.round(y);
        d = new Point(x, y);
        if (((((x >= this.start().getX()) && (x <= this.end().getX()))
                || ((x >= this.end().getX()) && (x <= this.start().getX()))))
                && (((x >= other.start().getX()) && (x <= other.end().getX()))
                || ((x >= other.end().getX()) && (x <= other.start().getX())))
                && (((y >= this.start.getY()) && (y <= this.end.getY()))
                || ((y >= this.end.getY()) && (y <= this.start.getY())))
                && (((y >= other.start.getY()) && (y <= other.end.getY()))
                || ((y >= other.end.getY()) && (y <= other.start.getY())))) {
            return d;
        }
        return null;
    }

    /**
     * equals -- return true is the lines are equal, false otherwise.
     *
     * @param other type Line.
     * @return true/false.
     */
    public boolean equals(Line other) {
        // m1 and m2 are slopes of each line
        double y1 = (start().getY() - end().getY());
        double x1 = (start().getX() - end().getX());
        double m1 = y1 / x1;
        double m2 = (other.start().getY() - other.end().getY()) / (other.start().getX() - other.end().getX());
        // y-intercept
        double b1 = start().getY() - m1 * start().getX();
        double b2 = other.start().getY() - m2 * other.start().getX();
        if ((m1 == m2) && (b1 == b2) && (this.length() == other.length())) {
            return true;
        }
        return false;
    }

    /**
     * finds the closest intersection between the parameter rectangle & this line.
     *
     * @param rect the rectangle
     * @return the closest intersection point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> points = new ArrayList<>();
        points = rect.intersectionPoints(new Line(this.start, this.end));
        if (!(points.isEmpty())) {
            Point save = points.get(0);
            for (Point p : points) {
                if (p.distance(this.start) <= save.distance(this.start)) {
                    save = p;
                }
            }
            return save;
        } else {
            return null;
        }
    }
}
