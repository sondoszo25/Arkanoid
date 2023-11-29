// 207789140 Sondos Zoabi.

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Winning animation class.
 */
public class YouWinAnimation implements Animation {
    private Counter score;

    /**
     * class's constructor.
     * @param score
     */
    public YouWinAnimation(Counter score) {
        this.score = score;
    }

    /**
     * doing one frame.
     *
     * @param d drawsurface.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.black);
        d.drawText(250, 250, "You Win!", 50);
        d.drawText(200, 350, "Score is: " + score.getValue(), 50);
    }

    /**
     * if should stop.
     * @return boolean value.
     */
    public boolean shouldStop() {
        return true;
    }
}