// 207789140 Sondos Zoabi

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * identifying the block object.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rec;
    private List<HitListener> hitListeners = new ArrayList<>();

    /**
     * Return the "collision shape" of the object.
     *
     * @param rectangle block is defined by rectangle.
     */
    public Block(Rectangle rectangle) {
        this.rec = rectangle;
    }

    /**
     * @return the block sizes.
     */
    public Rectangle getCollisionRectangle() {
        return rec;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param collisionPoint  where the ball hits the block.
     * @param currentVelocity velocity of the ball.
     * @param hitter          ball who hits.
     * @return new velocity of the ball after hitting the block.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        if (collisionPoint.getX() == rec.getUpperLeft().getX()
                || (collisionPoint.getX()) == rec.getUpperLeft().getX() + rec.getWidth()) {
            dx *= -1;
        }
        if (collisionPoint.getY() == rec.getUpperLeft().getY()
                || collisionPoint.getY() == rec.getUpperLeft().getY() + rec.getHeight()) {
            dy *= -1;
        }
        this.notifyHit(hitter);
        return new Velocity(dx, dy);
    }

    /**
     * drawing the block on the surface passed as parameter to the function.
     *
     * @param d the surface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle((int) rec.getUpperLeft().getX() - 2, (int) rec.getUpperLeft().getY() - 2,
                (int) rec.getWidth() + 3, (int) rec.getHeight() + 3);
        d.setColor(this.rec.getColor());
        d.fillRectangle((int) rec.getUpperLeft().getX(), (int) rec.getUpperLeft().getY(),
                (int) rec.getWidth(), (int) rec.getHeight());
    }

    /**
     * will be used later.
     */
    public void timePassed() {
    }

    /**
     * to add the block to the sprite & collidables collection.
     *
     * @param g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * adding hit listener to the list.
     *
     * @param hl hit listener to add to the list.
     */
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * removing hit listener from the list.
     *
     * @param hl listener.
     */
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * notifying when there is a hit.
     *
     * @param hitter the ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * removing this block when there is a hit.
     *
     * @param game the game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

}
