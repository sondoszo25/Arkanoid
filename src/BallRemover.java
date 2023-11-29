// 207789140 Sondos Zoabi

/**
 * class ballremover.
 */
public class BallRemover implements HitListener {
    private Counter ballsCounter;
    private GameLevel game;

    /**
     * constructor of ball remover.
     *
     * @param g game
     * @param b counter
     */
    public BallRemover(GameLevel g, Counter b) {
        this.ballsCounter = b;
        this.game = g;
    }

    /**
     * when there is a hit it removes the ball from the game.
     *
     * @param beingHit block that being hit in the death region.
     * @param hitter   ball that falls of the screen
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        game.removeSprite(hitter);
        ballsCounter.decrease(1);
    }
}