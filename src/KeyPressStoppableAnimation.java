// 207789140 Sondos Zoabi.
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Key Press Stoppable Animation class.
 */
public class KeyPressStoppableAnimation implements Animation {

    private boolean isAlreadyPressed;
    private KeyboardSensor keyboardSensor;
    private Animation animation;
    private String key;
    private boolean first;

    /**
     * class's Constructor.
     * @param sensor
     * @param key
     * @param animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboardSensor = sensor;
        this.animation = animation;
        this.key = key;
        this.isAlreadyPressed = true;
    }

    /**
     * doing one frame.
     * @param d drawsurface.
     */
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (keyboardSensor.isPressed(key) && !isAlreadyPressed) {
            first = true;
        }
        if (!(keyboardSensor.isPressed(key))) {
            isAlreadyPressed = false;
        }
    }

    /**
     * stopping the animation if true.
     * @return boolean value.
     */
    public boolean shouldStop() {
        if (first) {
            first = false;
            return !first;
        }
        return first;
    }

}