// 207789140 Sondos Zoabi.

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * level 3 class.
 */
public class Green3 implements LevelInformation {
    /**
     * @return num of balls.
     */
    public int numberOfBalls() {
        return 2;
    }

    /**
     * @return initial balls velocities.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < numberOfBalls(); i++) {
            Velocity v = new Velocity(0, 5);
            velocities.add(v);
        }
        return velocities;
    }

    /**
     * @return paddle's speed.
     */
    public int paddleSpeed() {
        return 8;
    }

    /**
     * @return paddle's width.
     */
    public int paddleWidth() {
        return 150;
    }

    /**
     * @return level's name.
     */
    public String levelName() {
        return "Green 3";
    }

    /**
     * @return level's background.
     */
    public Sprite getBackground() {
        return new Block(new Rectangle(new Point(0, 0), 800, 600, Color.green));
    }

    /**
     * @return list of blocks in the game.
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        int widthOfBlock = 35, heightOfBlock = 15;
        int startX = 748;
        int startY = 150;
        Rectangle rec;
        Random rand = new Random();
        for (int j = 0; j < 5; j++) {
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            Color color = new Color(r, g, b);
            for (int i = 0; i < 10 - j; i++) {
                rec = new Rectangle(new Point(startX - i * (widthOfBlock + 2), startY + j * (heightOfBlock + 1)),
                        widthOfBlock, heightOfBlock, color);
                blocks.add(new Block(rec));
            }
        }
        return blocks;
    }

    /**
     * @return num of blocks.
     */
    public int numberOfBlocksToRemove() {
        return 40;
    }

}