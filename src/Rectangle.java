// 207789140 Sondos Zoabi
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * creating rectangle.
 */
public class Rectangle {
    // Create a new rectangle with location and width/height.
    private Point upperLeft;
    private double width;
    private double height;
    private Color color;

    /**
     * constructor of rectangle.
     * @param upperLeft the x & y axes of the upperleft point of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of rectangle.
     * @param color the color of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height, Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     * @param line to check if it intersects with the rectangle.
     * @return list of intersection points if there is.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> list = new ArrayList<Point>();
        Line[] sides = createSides();
        for (int i = 0; i < sides.length; i++) {
            if (sides[i].isIntersecting(line)) {
                Point isIntersect = sides[i].intersectionWith(line);
                list.add(isIntersect);
            }
        }
        return list;
    }

    /**
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * @return the color of the rectangle.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * calculate the line that represent the place of the rectangle's sides.
     * @return array of line that represent the rectangle's sides.
     */
    public Line[] createSides() {
        Line[] sides = new Line[4];
        int i = 0;
        Point downLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Line leftSide = new Line(upperLeft, downLeft);
        sides[i] = leftSide;
        i++;
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Line upperSide = new Line(upperLeft, upperRight);
        sides[i] = upperSide;
        i++;
        Point downRight = new Point(upperRight.getX(), downLeft.getY());
        Line rightSide = new Line(upperRight, downRight);
        sides[i] = rightSide;
        i++;
        Line downSide = new Line(downLeft, downRight);
        sides[i] = downSide;
        return sides;
    }
}