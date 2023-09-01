import Sound.Music;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Game logic
 */
public class Game implements KeyboardHandler {

    public static boolean loadComplete = false;
    public static boolean canStartGame = false;
    public static boolean isGameStarted = false;
    public static boolean isGameOver = false;
    public static boolean isLevelCompleted = false;
    public static boolean isGameCompleted = false;
    public static int LEVEL = 1;

    private static Music music;
    private static Background background;
    private static Score score;
    private final Grid grid;
    private final Tree tree;
    private final Bird bird;
    private final Collision collision;

    public static ExecutorService es;

    public Game() {

        this.grid = new Grid(96, 54);
        background = new Background();
        this.tree = new Tree();
        this.bird = new Bird();
        this.collision = new Collision(tree, bird);
        music = new Music();
        score = new Score();
    }

    public void loadStart() {

        music.start();

        grid.init();
        background.drawBackground();
        keyboardInit();

        initLevel();

        background.menuScreen();

        music.play();
        loadComplete = true;

    }

    public void initLevel() {

        tree.setLevel(LEVEL);

        background.initPlane();
        background.pressSpace();
        tree.initTrees();
        bird.initBird();
        score.scoreText();

        canStartGame = true;
    }

    public void nextLevel() {

        LEVEL++;

        clearLevel();

        isLevelCompleted = false;

        initLevel();
    }

    public void restart() {

        LEVEL = 1;

        clearLevel();

        score.setScore(0);

        isGameOver = false;
        isLevelCompleted = false;
        isGameCompleted = false;

        initLevel();
    }

    public void clearLevel() {

        tree.clear();
        bird.clear();
        background.planeClear();
        score.clear();
    }

    public static void gameOver() {

        isGameOver = true;

        es.shutdown();

        music.stop();

        background.gameOver();

        score.pauseTimer();
    }

    public static void levelCompleted() {

        isLevelCompleted = true;

        if (LEVEL == 5) {
            isGameCompleted = true;
        }

        es.shutdown();

        music.stop();

        if (!isGameCompleted) {
            background.levelEnd();
        } else {
            background.gameEnd();
        }


        score.pauseTimer();
    }

    private void start() {

        canStartGame = false;

        background.hidePressSpace();

        es = Executors.newCachedThreadPool();

        es.submit(background);
        es.submit(collision);
        es.submit(bird);
        es.submit(tree);
    }

    public void keyboardInit() {

        Keyboard keyboard = new Keyboard(this);

        //SPACE
        KeyboardEvent spacePress = new KeyboardEvent();
        spacePress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        spacePress.setKey(KeyboardEvent.KEY_SPACE);
        keyboard.addEventListener(spacePress);

        //E
        KeyboardEvent ePress = new KeyboardEvent();
        ePress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        ePress.setKey(KeyboardEvent.KEY_E);
        keyboard.addEventListener(ePress);

        //R
        KeyboardEvent rPress = new KeyboardEvent();
        rPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        rPress.setKey(KeyboardEvent.KEY_R);
        keyboard.addEventListener(rPress);

        //C
        KeyboardEvent cPress = new KeyboardEvent();
        cPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        cPress.setKey(KeyboardEvent.KEY_C);
        keyboard.addEventListener(cPress);

        KeyboardEvent enterPress = new KeyboardEvent();
        enterPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        enterPress.setKey(KeyboardEvent.KEY_ENTER);
        keyboard.addEventListener(enterPress);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {

            if (isGameStarted && canStartGame) {

                music.play();
                start();

                if (Score.isScoreStarted) {
                    score.resumeTimer();
                } else score.initScore();
            }
        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_C) {

            if (isLevelCompleted && !isGameCompleted) {

                nextLevel();
                background.hideLevelEnd();
            }
        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_R) {

            if (isGameOver) {

                restart();
                background.hideGameOver();
            }
        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_E) {
            System.exit(0);
        }


        if (keyboardEvent.getKey() == KeyboardEvent.KEY_ENTER) {

            if (loadComplete) {

                background.hideMenuScreen();

                if (!isGameStarted) {
                    canStartGame = true;
                    music.stop();
                }

                isGameStarted = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
