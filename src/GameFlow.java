// 207789140 Sondos Zoabi.
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * the game flow class.
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private ScoreIndicator scoreIndicator;
    private Counter score;
    private ScoreTrackingListener sL;
    private GUI gui;

    /**
     * game flow constructor.
     * @param ar
     * @param ks
     * @param scoreIndicator
     * @param score
     * @param gui
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, ScoreIndicator scoreIndicator, Counter score, GUI gui) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.scoreIndicator = scoreIndicator;
        this.score = score;
        this.sL = new ScoreTrackingListener(score);
        this.gui = gui;
    }

    /**
     * running the list of levels.
     * @param levels
     */
    public void runLevels(List<LevelInformation> levels) {
        Counter lives = new Counter();
        lives.increase(7);
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, scoreIndicator, sL,
                    score, lives);
            level.initialize();
            while (level.getBalls() != 0 && level.getBlocks() != 0) {
                level.run();
            }
            if (level.getBalls() == 0 && level.ballIsGone() == 0) {
                Animation a1 = new YouLoseAnimation(score);
                Animation a1k = new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY, a1);
                animationRunner.run(a1k);
                if (!a1k.shouldStop()) {
                    gui.close();
                }
                break;
            }
            if (level.getBlocks() == 0) {
                score.increase(100);
            }
            if (levelInfo.levelName().equals(levels.get(levels.size() - 1).levelName()) && level.getBlocks() == 0) {
                Animation a2 = new YouWinAnimation(score);
                Animation a2k = new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY, a2);
                animationRunner.run(a2k);
                if (!a2k.shouldStop()) {
                    gui.close();
                }
            }
        }
    }
}