// 207789140 Sondos Zoabi
import biuoop.DrawSurface;

/**
 * creating the sprite interface.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d surface to draw on.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}