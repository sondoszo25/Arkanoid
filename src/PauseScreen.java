// 207789140 Sondos Zoabi.

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Pause Screen class.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * constructor of class.
     * @param k
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * doing one frame.
     * @param d drawsurface.
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * tells if should stop.
     * @return boolean value.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}