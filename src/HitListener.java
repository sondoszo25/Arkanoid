// 207789140 Sondos Zoabi
/**
 * interface hit listener.
 */
public interface HitListener {

    /**
     *  This method is called whenever the beingHit object is hit.
     *  The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit block being hit by the ball.
     * @param hitter ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}