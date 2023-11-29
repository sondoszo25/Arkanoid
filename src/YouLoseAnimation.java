// 207789140 Sondos Zoabi.
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Losing animation class.
 */
public class YouLoseAnimation implements Animation {
    private Counter score;

    /**
     * class's constructor.
     * @param s
     */
    public YouLoseAnimation(Counter s) {
        this.score = s;
    }

    /**
     * doing one frame.
     * @param d drawsurface.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.white);
        d.drawText(250, 250, "Game Over", 50);
        d.drawText(200, 350, "Your Score is: " + score.getValue(), 50);
    }

    /**
     * if should stop.
     * @return boolean value.
     */
    public boolean shouldStop() {
        return true;
    }
}