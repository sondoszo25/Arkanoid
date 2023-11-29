// 207789140 Sondos Zoabi
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * Animation runner class.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper = new Sleeper();

    /**
     * constructor of animation runner.
     * @param gui
     * @param framesPerSecond
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
        this.gui = gui;
    }

    /**
     * running the given animation class.
     * @param animation
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

}