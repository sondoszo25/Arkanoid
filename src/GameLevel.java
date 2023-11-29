// 207789140 Sondos Zoabi

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * creating the game,adding balls,Paddle ,& blocks.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private biuoop.KeyboardSensor keyboard;
    private Counter blockCounter = new Counter();
    private BlockRemover blockRemover = new BlockRemover(this, blockCounter);
    private Counter ballCounter = new Counter();
    private BallRemover ballRemover = new BallRemover(this, ballCounter);
    private List<Ball> balls = new ArrayList<>();
    private Block deathRegion = new Block(new Rectangle(new Point(0, 590), 800, 10, Color.black));
    private Counter score;
    private ScoreIndicator scoreIndicator;
    private ScoreTrackingListener sL;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation level;
    private Counter lives;

    /**
     * constructor of gameLevel.
     * @param levelInfo
     * @param keyboardSensor
     * @param animationRunner
     * @param scoreIndicator
     * @param sL
     * @param score
     * @param lives
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor keyboardSensor, AnimationRunner animationRunner,
                     ScoreIndicator scoreIndicator, ScoreTrackingListener sL, Counter score, Counter lives) {
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.level = levelInfo;
        this.keyboard = keyboardSensor;
        this.runner = animationRunner;
        this.scoreIndicator = scoreIndicator;
        this.score = score;
        this.sL = sL;
        this.addSprite(this.scoreIndicator);
        this.lives = lives;
    }

    /**
     * adding the collidables(blocks&paddle) to the game.
     *
     * @param c a collidable to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * adding the paramater s to the sprites collection.
     *
     * @param s sprite to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * creating blocks as borders to prevent the ball to go outside.
     */
    public void createBorders() {
        Block roof = new Block(new Rectangle(new Point(0, 30), 800, 15, Color.gray));
        Block leftSide = new Block(new Rectangle(new Point(0, 30), 15, 600, Color.gray));
        Block rightSide = new Block(new Rectangle(new Point(785, 30), 15, 600, Color.gray));
        leftSide.addToGame(this);
        rightSide.addToGame(this);
        roof.addToGame(this);
    }

    /**
     * create paddle.
     */
    public void createPaddle() {
        int width = level.paddleWidth();
        int speed = level.paddleSpeed();
        Paddle paddle = new Paddle(keyboard, width, speed);
        paddle.addToGame(this);
    }

    /**
     * create balls.
     */
    public void createBalls() {
        List<Point> centers = new ArrayList<>();
        if (level.levelName().equals("Final Four")) {

            for (int i = 0; i < level.numberOfBalls(); i++) {
                if (i % 2 == 0) {
                    Point p = new Point(350 + i * 75, 500);
                    centers.add(p);
                } else {
                    Point p = new Point(400, 450);
                    centers.add(p);
                }
            }
        } else if (level.levelName().equals("Green 3")) {
            for (int i = 0; i < level.numberOfBalls(); i++) {
                Point p = new Point(400 + 50 * i, 400);
                centers.add(p);
            }
        } else if (level.levelName().equals("Direct Hit")) {
            centers.add(new Point(415, 570));
        } else if (level.levelName().equals("Wide Easy")) {
            for (int i = 0; i < level.numberOfBalls(); i++) {
                centers.add(new Point(400, 500));
            }
        }
        int numOfBalls = level.numberOfBalls();
        List<Velocity> velocities = level.initialBallVelocities();
        for (int i = 0; i < numOfBalls; i++) {
            Ball ball = new Ball(centers.get(i), 5, Color.white);
            balls.add(ball);
            balls.get(i).setVelocity(velocities.get(i));
            balls.get(i).addToGame(this);
            ballCounter.increase(1);
            balls.get(i).setGameEnvironment(environment);
        }
    }

    /**
     * create blocks.
     */
    public void createBlocks() {
        List<Block> blocks = level.blocks();
        for (int i = 0; i < level.numberOfBlocksToRemove(); i++) {
            blocks.get(i).addToGame(this);
            blocks.get(i).addHitListener(sL);
            blocks.get(i).addHitListener(blockRemover);
            blockCounter.increase(1);
        }
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        createBorders();
        createPaddle();
        createBalls();
        createBlocks();
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, sprites, true, this, level));
        this.running = true;
        if (getBalls() == 0) {
            return;
        }
        this.runner.run(this);
    }

    /**
     * removing collidable from list.
     *
     * @param c
     */
    public void removeCollidable(Collidable c) {
        environment.removeFromList(c);
    }

    /**
     * removing sprite from list.
     *
     * @param s
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * when all the balls are gine it returns 0.
     *
     * @return int
     */
    public int ballIsGone() {
        for (int i = 0; i < balls.size(); i++) {
            if (balls.get(i).getCenter().getY() >= deathRegion.getCollisionRectangle().getUpperLeft().getY()) {
                ballRemover.hitEvent(deathRegion, balls.get(i));
                balls.remove(balls.get(i));
            }
        }
        return ballCounter.getValue();
    }

    /**
     * if the game should stop.
     * @return boolean value.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * drawing background.
     * @param d
     */
    public void firstLevelFrame(DrawSurface d) {
        d.setColor(Color.blue);
        d.fillCircle(415, 215, 80);
        d.setColor(Color.black);
        d.fillCircle(415, 215, 79);
        d.setColor(Color.blue);
        d.fillCircle(415, 215, 60);
        d.setColor(Color.black);
        d.fillCircle(415, 215, 59);
        d.setColor(Color.blue);
        d.fillCircle(415, 215, 40);
        d.setColor(Color.black);
        d.fillCircle(415, 215, 39);
        d.setColor(Color.blue);
        d.drawLine(415, 120, 415, 195);
        d.drawLine(415, 235, 415, 310);
        d.drawLine(325, 215, 395, 215);
        d.drawLine(435, 215, 510, 215);
    }

    /**
     * drawing level 2 background.
     * @param d
     */
    public void secondLevelFrame(DrawSurface d) {
        float r = 1;
        float g = 1;
        float b = 0.75F;
        Color yellow = new Color(r, g, b);
        d.setColor(Color.yellow);
        for (int i = 0; i < 90; i++) {
            d.drawLine(100, 90, 50 + 10 * i, 200);
        }
        d.setColor(yellow);
        d.fillCircle(100, 100, 50);
        b = 0F;
        yellow = new Color(r, g, b);
        d.setColor(yellow);
        d.fillCircle(100, 100, 40);
        d.setColor(Color.yellow);
        d.fillCircle(100, 100, 30);
    }

    /**
     * drawing level 3 background.
     * @param d
     */
    public void thirdLevelFrame(DrawSurface d) {
        int rectangleWidth = 15;
        int rectangleHeight = 35;
        d.setColor(Color.black);
        d.fillRectangle(100, 400, 100, 200);
        d.setColor(Color.white);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(103 + i * (rectangleWidth + 5), 405 + j * (rectangleHeight + 5),
                        rectangleWidth, rectangleHeight);
            }
        }
        d.setColor(Color.darkGray);
        d.fillRectangle(135, 350, 30, 50);
        d.setColor(Color.gray);
        d.fillRectangle(145, 150, 10, 200);
        d.setColor(Color.orange);
        d.fillCircle(150, 140, 15);
        d.setColor(Color.red);
        d.fillCircle(150, 140, 10);
        d.setColor(Color.white);
        d.fillCircle(150, 140, 5);
    }

    /**
     * drawing level 4 background.
     * @param d
     */
    public void fourthLevelFrame(DrawSurface d) {
        d.setColor(Color.white);
        for (int i = 0; i < 10; i++) {
            d.drawLine(120 + 10 * i, 400, 120 + 9 * i, 600);
        }
        d.setColor(Color.gray.brighter());
        d.fillCircle(120, 400, 25);
        d.fillCircle(140, 410, 25);
        d.setColor(Color.gray);
        d.fillCircle(140, 380, 30);
        d.setColor(Color.gray);
        d.fillCircle(190, 380, 30);
        d.fillCircle(180, 400, 30);
        d.setColor(Color.white);
        for (int i = 0; i < 10; i++) {
            d.drawLine(620 + 10 * i, 500, 620 + 9 * i, 600);
        }
        d.setColor(Color.gray.brighter());
        d.fillCircle(620, 500, 25);
        d.fillCircle(640, 510, 25);
        d.setColor(Color.gray);
        d.fillCircle(640, 480, 30);
        d.setColor(Color.gray);
        d.fillCircle(690, 480, 30);
        d.fillCircle(680, 500, 30);
    }

    /**
     * drawing one frame .
     * @param d drawsurface.
     */
    public void doOneFrame(DrawSurface d) {
        ballIsGone();
        level.getBackground().drawOn(d);
        if (level.levelName().equals("Direct Hit")) {
            firstLevelFrame(d);
        } else if (level.levelName().equals("Green 3")) {
            thirdLevelFrame(d);
        } else if (level.levelName().equals("Final Four")) {
            fourthLevelFrame(d);
        } else if (level.levelName().equals("Wide Easy")) {
            secondLevelFrame(d);
        }
        this.sprites.drawAllOn(d);
        d.setColor(Color.black);
        d.drawText(550, 27, "Level Name: " + level.levelName(), 20);
        d.drawText(50, 27, "Lives: " + lives.getValue(), 20);
        this.sprites.notifyAllTimePassed();
        Animation a1 = new PauseScreen(keyboard);
        Animation a1k = new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, a1);
        if (keyboard.isPressed("p")) {
            runner.run(a1k);
        }
        if (a1k.shouldStop()) {
            this.runner.run(this);
        }
        if (getBlocks() == 0) {
            this.running = false;
        }
        if (getBalls() == 0) {
            this.running = false;
        }
    }

    /**
     * balls amount.
     * @return num of balls.
     */
    public int getBalls() {
        return ballCounter.getValue();
    }

    /**
     * blocks in the game.
     * @return num of blocks.
     */
    public int getBlocks() {
        return blockCounter.getValue();
    }

    /**
     * lives of player.
     * @return num of lives.
     */
    public int getLives() {
        return lives.getValue();
    }
}
