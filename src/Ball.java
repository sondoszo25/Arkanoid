// 207789140 Sondos Zoabi

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * identifying object Ball by center point, color, velocity.
 */
public class Ball implements Sprite {
    private Point center;
    private double x;
    private double y;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private Point start;
    private Point end;
    private GameEnvironment g;

    /**
     * constructor of object Ball.
     *
     * @param center type Point.
     * @param r      the diameter of the ball.
     * @param color  the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.x = center.getX();
        this.y = center.getY();
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * another constructor of ball, instead of constructing it with a parameter of
     * type Point we can construct it with two variable of type int which represent
     * the coordinates x,y.
     *
     * @param x     x coordinate.
     * @param y     y coordinate.
     * @param r     diameter of the ball.
     * @param color color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.x = x;
        this.y = y;
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * to get the value of x coordinate.
     *
     * @return value of x.
     */
    public int getX() {
        return (int) this.x;
    }

    /**
     * to get the value of y coordinate.
     *
     * @return value of y.
     */
    public int getY() {
        return (int) this.y;
    }

    /**
     * to get the diameter of the ball.
     *
     * @return diameter.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * to get the color of the ball.
     *
     * @return color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface to be able to draw on it.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.black);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), r);
        surface.setColor(Color.white);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), r - 1);
    }

    /**
     * setting the velocity of the ball.
     *
     * @param v type Velocity(class).
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * setting the game environment of the ball.
     *
     * @param g game environment.
     */
    public void setGameEnvironment(GameEnvironment g) {
        this.g = g;
    }

    /**
     * setting the velocity of the ball.
     *
     * @param dx moving the ball in x coordinate.
     * @param dy moving the ball in y coordinate.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * getting the velocity of the ball.
     *
     * @return ball's velocity.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * center of the ball.
     *
     * @return pont of center
     */
    public Point getCenter() {
        return center;
    }

    /**
     * setting the center of ball.
     *
     * @param center
     */
    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * sets the start point to define the legal scope to put the ball in it.
     *
     * @param x value of start point in x-axis.
     * @param y value of start point in y-axis.
     */
    public void setStart(int x, int y) {
        this.start = new Point(x, y);
    }

    /**
     * sets the end point to define the legal scope to put the ball in it.
     *
     * @param x value of end point in x-axis.
     * @param y value of end point in y-axis.
     */
    public void setEnd(int x, int y) {
        this.end = new Point(x, y);
    }

    /**
     * calculating the ball trajectory and checks if there is collision.
     *
     * @return the trajectory.
     */
    public Line findTrajectory() {
        Line trajectory;
        Point point1 = this.center;
        Point point2 = this.getVelocity().applyToPoint(point1);
        double x = 0, y = 0;
        if (point2.getX() > point1.getX()) {
            x = this.center.getX() + getSize();
        } else if (point2.getX() == point1.getX()) {
            x = this.center.getX();
        } else {
            x = this.center.getX() - getSize();
        }
        if (point2.getY() > point1.getY()) {
            y = this.center.getY() + getSize();
        } else if (point2.getY() == point1.getY()) {
            y = this.center.getY();
        } else {
            y = this.center.getY() - getSize();
        }
        point1 = new Point(x, y);
        point2 = getVelocity().applyToPoint(point1);
        trajectory = new Line(point1, point2);
        return trajectory;
    }

    /**
     * makes the ball move one step according to their velocity.
     */
    public void moveOneStep() {
        Line trajectory = findTrajectory();
        CollisionInfo colInfo = g.getClosestCollision(trajectory);
        if (colInfo != null) {
            Point colPoint = colInfo.collisionPoint();
            Collidable colObject = colInfo.collisionObject();
            this.v = colObject.hit(this, colPoint, this.v);
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * to make it easy to apply moveOneStep for multiple balls.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * adding the ball to the sprites collection.
     *
     * @param g game to add the ball to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * removing the ball from the game.
     *
     * @param g
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}
