// 207789140 Sondos Zoabi
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * score indicator.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * sets the counter variable.
     *
     * @param c counter to set.
     */
    void setCounter(Counter c) {
        this.score = c;
    }

    /**
     * showing the score on the screen.
     * @param d surface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 30);
        d.setColor(Color.black);
        d.drawText(350, 27, "Score: " + score.getValue(), 20);
    }


    /**
     * timepassed unused.
     */
    public void timePassed() {
    }
}
