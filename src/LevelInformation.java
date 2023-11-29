// 207789140 Sondos Zoabi.
import java.util.List;

/**
 * Level Information interface.
 */
public interface LevelInformation {
    /**
     * @return number of balls.
     */
    int numberOfBalls();

    /**
     * @return initial velocities of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return paddle's speed.
     */
    int paddleSpeed();

    /**
     * @return paddle's width.
     */
    int paddleWidth();

    /**
     * @return the level name.
     */
    String levelName();

    /**
     * @return  Returns a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * @return The Blocks that make up this level, each block contains
     * its size, color and location.
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * @return num of blocks.
     */
    int numberOfBlocksToRemove();
}