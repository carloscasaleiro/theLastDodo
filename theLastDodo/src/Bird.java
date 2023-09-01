import Sound.Jump;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Bird images and mechanics
 */
public class Bird implements KeyboardHandler, Runnable{

    private int birdVelocity;
    private final Jump jumpSound;

    private final Map<Integer, Picture> bird = Collections.synchronizedMap(new ConcurrentHashMap<>());

    public Bird() {
        this.jumpSound = new Jump();
        initKeyboard();
    }

    public void initBird() {

        bird.put(0, new Picture(Grid.columnToX(25), Grid.rowToY(10), "DODOFLY.png"));
        bird.put(1, new Picture(Grid.columnToX(25), Grid.rowToY(10), "DODOJUMP.png"));
        bird.get(0).draw();
    }

    private void initKeyboard() {

        Keyboard keyboard = new Keyboard(this);

        KeyboardEvent spaceBarPress = new KeyboardEvent();
        spaceBarPress.setKey(KeyboardEvent.KEY_SPACE);
        spaceBarPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(spaceBarPress);
    }

    private void firstJump() {

        birdVelocity = -12;
        moveBird();
    }

    private void moveBird() {

        if (getBird(0).getY() + getBird(0).getHeight() < Grid.rowToY(52) || birdVelocity < 0) {
            birdVelocity += 1;
            getBird(0).translate(0, birdVelocity);
            getBird(1).translate(0, birdVelocity);
        } else {
            getBird(0).translate(0, Grid.rowToY(52) - getBird(0).getY() - getBird(0).getHeight());
            birdVelocity = 0;
        }

        if (getBird(0).getY() + getBird(0).getHeight() >= Grid.rowToY(52)) {
            int maxHeight = Grid.rowToY(52) - getBird(0).getHeight();
            getBird(0).translate(0, maxHeight - getBird(0).getY());
            getBird(1).translate(0, maxHeight - getBird(1).getY());
        }

        if (birdVelocity < 0) {
            getBird(0).delete();
            getBird(1).draw();
        } else {
            getBird(1).delete();
            getBird(0).draw();
        }
    }

    public void clear() {
        getBird(0).delete();
        getBird(1).delete();
        bird.clear();

        jumpSound.stop();
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {

            birdVelocity = -12;

            if (Game.isGameStarted) {

                if (!Game.isGameOver && !Game.isLevelCompleted) {
                    jumpSound.start();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    public int getBirdX() {
        return getBird(0).getX();
    }

    public int getBirdY() {
        return getBird(1).getY();
    }

    public int getBirdMaxX() {
        return getBird(0).getMaxX();
    }

    public int getBirdMaxY() {
        return getBird(1).getMaxY();
    }

    public void threadBird() {

        try {
            Thread.sleep(22,5);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public synchronized Picture getBird(int index) {
        return bird.get(index);
    }

    @Override
    public void run() {

        getBird(1).draw();
        getBird(0).grow(-10, -10);
        getBird(1).grow(-5, -5);

        birdVelocity = 0;

        firstJump();
        while (!Game.isGameOver && !Game.isLevelCompleted) {

            try {

            moveBird();

            } catch (Exception ex) {
                System.out.println();
            }

            threadBird();
        }
    }
}
