// 207789140 Sondos Zoabi.

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * level 4 class.
 */
public class FinalFour implements LevelInformation {
    /**
     * @return num of balls.
     */
    public int numberOfBalls() {
        return 3;
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
        return "Final Four";
    }

    /**
     * @return level's background.
     */
    public Sprite getBackground() {
        float r = (float) 0.05;
        float g = 0.6F;
        float b = 1;
        Color color = new Color(r, g, b);
        return new Block(new Rectangle(new Point(0, 0), 800, 600, color));
    }

    /**
     * @return list of blocks.
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        int widthOfBlock = 49, heightOfBlock = 15;
        int startX = 18;
        int startY = 150;
        Rectangle rec;
        Random rand = new Random();
        for (int i = 0; i < 7; i++) {
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            Color color = new Color(r, g, b);
            for (int j = 0; j < 15; j++) {
                rec = new Rectangle(new Point(startX + j * (widthOfBlock + 2), startY + i * (heightOfBlock + 2)),
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
        return 105;
    }

}