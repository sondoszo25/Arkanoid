// 207789140 Sondos Zoabi

/**
 * Collidable interface for blocks and paddle.
 */
public interface Collidable {
    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * @param collisionPoint where the ball collides with the block.
     * @param currentVelocity velocity before collision.
     * @param hitter ball who hit the collidable.
     *@return is the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}