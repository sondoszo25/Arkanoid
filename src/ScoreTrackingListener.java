// 207789140 Sondos Zoabi
/**
 * score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor of score tracking listener.
     * @param scoreCounter variable counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * when there is a hit.
     * @param beingHit block being hit by the ball.
     * @param hitter ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}