import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Background images
 */
public class Background implements Runnable{
    private final Map<Integer, Picture> pics = Collections.synchronizedMap(new ConcurrentHashMap<>());

    public void drawBackground() {

        pics.put(0, new Picture(Grid.PADDING, Grid.PADDING, "Sky960x540.png"));
        pics.get(0).draw();
        pics.put(1, new Picture(Grid.PADDING, Grid.PADDING, "Clouds960x540.png"));
        pics.get(1).draw();
        pics.put(2, new Picture(Grid.PADDING, Grid.PADDING, "Buildings960x540.png"));
        pics.get(2).draw();
        pics.put(3, new Picture(Grid.PADDING, Grid.PADDING, "Trees960x540.png"));
        pics.get(3).draw();
        pics.put(4, new Picture(Grid.PADDING, Grid.PADDING, "Grass960x540.png"));
        pics.get(4).draw();
    }

    public void menuScreen() {

        pics.put(5, new Picture(Grid.PADDING, Grid.PADDING, "startMenu.png"));
        getPics(5).draw();
    }

    public void hideMenuScreen() {

        getPics(5).delete();
    }

    public void pressSpace() {

        pics.put(6, new Picture(Grid.PADDING, Grid.PADDING, "pressSpace.png"));
        getPics(6).draw();
    }

    public void hidePressSpace() {

        getPics(6).delete();
    }

    public void gameOver() {

        pics.put(7, new Picture(Grid.columnToX(18), Grid.rowToY(7), "gameOver.png"));
        getPics(7).draw();
    }

    public void hideGameOver() {

        getPics(7).delete();
    }

    public void levelEnd() {

        pics.put(8, new Picture(Grid.columnToX(18), Grid.rowToY(7), "nextLevel.png"));
        getPics(8).draw();
    }

    public void hideLevelEnd() {

        getPics(8).delete();
    }

    public void gameEnd() {

        pics.put(9, new Picture(Grid.columnToX(18), Grid.rowToY(7), "lastLevel.png"));
        getPics(9).draw();
    }

    public synchronized Picture getPics(int index) {
        return pics.get(index);
    }

    /**
     * Plane
     */
    private final Map<Integer, Picture> plane = Collections.synchronizedMap(new ConcurrentHashMap<>());

    public void initPlane() {

        plane.put(0, new Picture(111, 50, "plane.png"));

        plane.get(0).draw();
        plane.get(0).grow(-10, -10);
    }

    public void planeClear() {
        plane.get(0).delete();
        plane.clear();
    }

    public synchronized Picture getPlane(int index) {
        return plane.get(index);
    }

    public int planeSpeed(int level) {

        int planeSpeed = 0;

        switch (level) {

            case 1: {
                planeSpeed = PlaneSpeed.LEVEL1.getPlaneSpeed();
                break;
            }
            case 2: {
                planeSpeed = PlaneSpeed.LEVEL2.getPlaneSpeed();
                break;
            }
            case 3: {
                planeSpeed = PlaneSpeed.LEVEL3.getPlaneSpeed();
                break;
            }
            case 4: {
                planeSpeed = PlaneSpeed.LEVEL4.getPlaneSpeed();
                break;
            }
            case 5: {
                planeSpeed = PlaneSpeed.LEVEL5.getPlaneSpeed();
                break;
            }
        }
        return planeSpeed;
    }

    public void threadPlane() {

        try {
            Thread.sleep(planeSpeed(Game.LEVEL));
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void run() {

        while (!Game.isGameOver && !Game.isLevelCompleted) {

            if (getPlane(0).getMaxX() < 860) {

                try {

                    getPlane(0).translate(0.80, 0);

                    threadPlane();

                } catch (Exception ex) {
                    System.out.println();
                }
            }
        }
    }
}