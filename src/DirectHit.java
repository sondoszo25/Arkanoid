// 207789140 Sondos Zoabi.
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Direct hit class, first level.
 */
public class DirectHit implements LevelInformation {
    /**
     * @return number of balls.
     */
    public int numberOfBalls() {
        return 1;
    }

    /**
     * @return initial balls velocities.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(0, -4));
        return velocities;
    }

    /**
     * @return paddle's speed.
     */
    public int paddleSpeed() {
        return 8;
    }

    /**
     * paddle's width.
     * @return width.
     */
    public int paddleWidth() {
        return 150;
    }

    /**
     * @return level's name.
     */
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * @return game's background.
     */
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point(15, 15), 800, 600, Color.black));
    }

    /**
     * @return list of blocks.
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block(new Rectangle(new Point(400, 200), 30, 30, Color.red)));
        return blocks;
    }

    /**
     * @return number of blocks to remove.
     */
    public int numberOfBlocksToRemove() {
        return 1;
    }

}