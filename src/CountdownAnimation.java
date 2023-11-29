// 207789140 Sondos Zoabi.
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * Countdown Animation class.
 */
public class CountdownAnimation implements Animation {

    private boolean running;
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private int copyCount;
    private GameLevel level;
    private LevelInformation levelInformation;

    /**
     * constructor of countdown animation class.
     * @param numOfSeconds
     * @param countFrom
     * @param gameScreen
     * @param running
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, boolean running) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.running = running;
        this.copyCount = countFrom;
    }

    /**
     * constructor of count down animation class.
     * @param numOfSeconds
     * @param countFrom
     * @param gameScreen
     * @param running
     * @param level
     * @param levelInformation
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, boolean running,
                              GameLevel level, LevelInformation levelInformation) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.running = running;
        this.copyCount = countFrom;
        this.level = level;
        this.levelInformation = levelInformation;
    }

    /**
     * doing one frame.
     * @param d drawsurface.
     */
    public void doOneFrame(DrawSurface d) {
        long num = (long) ((numOfSeconds / countFrom) * 1000);
        Sleeper sleeper = new Sleeper();
        levelInformation.getBackground().drawOn(d);
        if (levelInformation.levelName().equals("Direct Hit")) {
            level.firstLevelFrame(d);
        } else if (levelInformation.levelName().equals("Wide Easy")) {
            level.secondLevelFrame(d);
        } else if (levelInformation.levelName().equals("Green 3")) {
            level.thirdLevelFrame(d);
        } else if (levelInformation.levelName().equals("Final Four")) {
            level.fourthLevelFrame(d);
        }
        gameScreen.drawAllOn(d);
        d.setColor(Color.black);
        d.drawText(550, 27, "Level Name: " + levelInformation.levelName(), 20);
        d.drawText(50, 27, "Lives: " + level.getLives(), 20);
        float r = 1;
        float g = (float) 0.0408;
        float b = (float) 0.0408;
        Color color = new Color(r, g, b);
        d.setColor(color);
        d.drawText(400, 300, String.valueOf(copyCount), 32);
        if (copyCount >= 0 && copyCount != 3) {
            sleeper.sleepFor(num);
        }
        if (copyCount <= 0) {
            this.running = false;
        }
        copyCount -= 1;
    }

    /**
     * telling whether the animation should stop.
     * @return boolean value.
     */
    public boolean shouldStop() {
        return !this.running;
    }
}