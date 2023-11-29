// 207789140 Sondos Zoabi

import biuoop.GUI;

import java.util.ArrayList;
import java.util.List;

/**
 * Calling the Game class and starts the game.
 */
public class Ass6Game {
    /**
     * main method to start the game.
     * @param args
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
        int correctInput = 0;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "1":
                    levels.add(new DirectHit());
                    correctInput++;
                    break;
                case "2":
                    levels.add(new WideEasy());
                    correctInput++;
                    break;
                case "3":
                    levels.add(new Green3());
                    correctInput++;
                    break;
                case "4":
                    levels.add(new FinalFour());
                    correctInput++;
                    break;
                default:
                    break;
            }
        }
        if (correctInput == 0 || args.length == 0) {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new Green3());
            levels.add(new FinalFour());
        }
        GUI gui = new GUI("Arkanoid", 800, 600);
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        AnimationRunner animationRunner = new AnimationRunner(gui, 60);
        ScoreIndicator scoreIndicator = new ScoreIndicator();
        Counter score = new Counter();
        scoreIndicator.setCounter(score);
        GameFlow game = new GameFlow(animationRunner, keyboard, scoreIndicator, score, gui);
        game.runLevels(levels);
    }
}

