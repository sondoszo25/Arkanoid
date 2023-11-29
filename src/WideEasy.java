// 207789140 Sondos Zoabi

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * level 2 class.
 */
public class WideEasy implements LevelInformation {
    /**
     * @return num of balls.
     */
    public int numberOfBalls() {
        return 10;
    }

    /**
     * @return initial velocities.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(1, -6));
        velocities.add(new Velocity(-1, -6));
        velocities.add(new Velocity(2, -5));
        velocities.add(new Velocity(-2, -5));
        velocities.add(new Velocity(-3, -4));
        velocities.add(new Velocity(3, -4));
        velocities.add(new Velocity(-4, -3));
        velocities.add(new Velocity(4, -3));
        velocities.add(new Velocity(-5, -2));
        velocities.add(new Velocity(-5, -2));
        return velocities;
    }

    /**
     * @return paddle's speed.
     */
    public int paddleSpeed() {
        return 8;
    }

    /**
     * @return paddle's speed.
     */
    public int paddleWidth() {
        return 500;
    }

    /**
     * @return level's name.
     */
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * @return level's background.
     */
    public Sprite getBackground() {
        Block b = new Block(new Rectangle(new Point(15, 15), 800, 600, Color.white));
        return b;
    }

    /**
     * @return list of blocks.
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Random rand = new Random();
        Color randomColor = Color.pink;
        double blockWidth = 750 / numberOfBlocksToRemove();
        for (int i = 0; i < numberOfBlocksToRemove(); i++) {
            if (i == 8 || i == 10 || i == 12 || i == 14) {
                randomColor = randomColor;
            } else if (i % 2 == 0 || (i == 9) || (i == 11) || (i == 13)) {
                float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();
                randomColor = new Color(r, g, b);
            }
            Block b = new Block(new Rectangle(new Point((i) * (blockWidth + 1) + 17, 200), blockWidth,
                    30, randomColor));
            blocks.add(b);
        }
        return blocks;
    }

    /**
     * @return num of blocks.
     */
    public int numberOfBlocksToRemove() {
        return 15;
    }

}