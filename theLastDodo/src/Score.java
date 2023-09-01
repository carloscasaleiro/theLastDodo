import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Score system
 */
public class Score {
    public static boolean isScoreStarted = false;

    private final Map<Integer, Text> scoreText = Collections.synchronizedMap(new ConcurrentHashMap<>());
    private int score;
    private Timer timer;


    public void initScore() {

        isScoreStarted = true;

        this.score = 0;

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateScore();
            }
        }, 500, 500);
    }

    public void pauseTimer() {
        timer.cancel();
    }

    public void resumeTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateScore();
            }
        }, 500, 500);
    }
    private void updateScore() {
        score += 5;
        getScoreText(0).setText("Score: " + score);
    }

    public void scoreText(){
        scoreText.put(0, new Text(Grid.columnToX(12), Grid.rowToY(0),"Score: " + score));
        scoreText.get(0).delete();
        scoreText.get(0).draw();
        scoreText.get(0).setColor(Color.BLACK);
    }

    public void clear() {
        scoreText.get(0).delete();
        scoreText.clear();
    }

    public void setScore(int score) {
        this.score = score;
    }

    public synchronized Text getScoreText(int index) {
        return scoreText.get(index);
    }
}
