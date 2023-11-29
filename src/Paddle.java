// 207789140 Sondos Zoabi

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * creating the paddle to prevent ball touching the ground.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rec;
    private int speed;

    /**
     * constructor of paddle.
     *
     * @param keyboard gets the key which pressed by user.
     * @param speed    paddle's speed.
     * @param width    paddle's width.
     */
    public Paddle(biuoop.KeyboardSensor keyboard, int width, int speed) {
        this.keyboard = keyboard;
        this.rec = new Rectangle(new Point(400 - (width / 2), 580), width, 20, Color.yellow);
        this.speed = speed;
    }

    /**
     * to move the paddle left if left arrow key is pressed.
     */
    public void moveLeft() {
        double x = this.rec.getUpperLeft().getX() - speed;
        if (x - 17 <= 0) {
            x = 17;
        }
        double y = this.rec.getUpperLeft().getY();
        double w = this.rec.getWidth();
        double h = this.rec.getHeight();
        this.rec = new Rectangle(new Point(x, y), w, h, this.rec.getColor());
    }

    /**
     * to move the paddle right if right arrow key is pressed.
     */
    public void moveRight() {
        double x = this.rec.getUpperLeft().getX() + speed;
        double y = this.rec.getUpperLeft().getY();
        double w = this.rec.getWidth();
        double h = this.rec.getHeight();
        if (x + w + 17 >= 800) {
            x = 800 - w - 17;
        }
        this.rec = new Rectangle(new Point(x, y), w, h, this.rec.getColor());
    }

    /**
     * the paddle is in sprite collection.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * drawing the paddle in the surface that passed as parameter to the function.
     *
     * @param d the surface to draw on.
     */
    public void drawOn(DrawSurface d) {
        int x = (int) this.rec.getUpperLeft().getX();
        int y = (int) this.rec.getUpperLeft().getY();
        int w = (int) this.rec.getWidth();
        int h = (int) this.rec.getHeight();
        d.setColor(this.rec.getColor());
        d.setColor(Color.black);
        d.fillRectangle(x - 2, y - 2, w + 4, h + 2);
        d.setColor(Color.yellow);
        d.fillRectangle(x, y, w, h);
    }

    /**
     * the sizes of the paddle.
     *
     * @return the rectangle which represent the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    /**
     * changing the ball velocity after collision.
     *
     * @param collisionPoint  where the ball collides with the block.
     * @param currentVelocity velocity before collision.
     * @return the new velocity of the ball after hitting the paddle.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double x = collisionPoint.getX();
        double y = collisionPoint.getY();
        double w = this.rec.getWidth();
        double h = this.rec.getHeight();
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double speed = Math.sqrt((dx * dx + dy * dy));
        int angle = 30;
        Velocity v = currentVelocity;
        if (y == this.rec.getUpperLeft().getY()) {
            if (x >= this.rec.getUpperLeft().getX() && x <= this.rec.getUpperLeft().getX() + (w / 5)) {
                // region 1
                v = currentVelocity.fromAngleAndSpeed(5 * angle, speed);
            }
            if (x >= this.rec.getUpperLeft().getX() + (w / 5) && x <= this.rec.getUpperLeft().getX() + 2 * (w / 5)) {
                //region 2
                v = currentVelocity.fromAngleAndSpeed(4 * angle, speed);
            }
            if (x >= this.rec.getUpperLeft().getX() + 2 * (w / 5)
                    && x <= this.rec.getUpperLeft().getX() + 3 * (w / 5)) {
                //region 3
                v = currentVelocity.fromAngleAndSpeed(3 * angle, speed);
            }
            if (x >= this.rec.getUpperLeft().getX() + 3 * (w / 5)
                    && x <= this.rec.getUpperLeft().getX() + 4 * (w / 5)) {
                //region 4
                v = currentVelocity.fromAngleAndSpeed(2 * angle, speed);
            }
            if (x >= this.rec.getUpperLeft().getX() + 4 * (w / 5) && x <= this.rec.getUpperLeft().getX() + w) {
                //region 5
                v = currentVelocity.fromAngleAndSpeed(angle, speed);
            }
        }
        return v;
    }

    /**
     * Add this paddle to the game.
     *
     * @param g the game to add the paddle to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}