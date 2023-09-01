import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Tree images and mechanics
 */
public class Tree implements Runnable{

    private int level;
    private final Map<Integer, Picture> treesUp = Collections.synchronizedMap(new ConcurrentHashMap<>());
    private final Map<Integer, Picture> treesDown = Collections.synchronizedMap(new ConcurrentHashMap<>());
    private final Map<Integer, Picture> treesSideCover = Collections.synchronizedMap(new ConcurrentHashMap<>());

    public void initTrees() {

        int counter = 0;

        for (int i = 0; i < treeAmount(level); i++) {
            if (i % 2 == 0) {
                treesUp.put(counter, TreeFactory.getNewPicture());
            } else {
                treesDown.put(counter, TreeFactory.getNewPicture());
                counter++;
            }
        }

        if(treesSideCover.isEmpty()) {
            treesSideCover.put(0, new Picture(Grid.PADDING,Grid.PADDING,"left.png"));
            treesSideCover.put(1, new Picture(Grid.columnToX(85),Grid.PADDING,"right.png"));
        }

        for (int i = 0; i < treeAmount(level) / 2; i++) {
            treesUp.get(i).draw();
            treesDown.get(i).draw();
        }

        treesSideCover.get(0).draw();
        treesSideCover.get(1).draw();
    }

    public void clear() {

        for (int i = 0; i < treesUp.size(); i++) {
            treesUp.get(i).delete();
            treesDown.get(i).delete();
        }

        treesUp.clear();
        treesDown.clear();

        treesSideCover.get(0).delete();
        treesSideCover.get(1).delete();
    }

    public int treeAmount(int level) {

        int treeAmount = 0;

        switch (level) {

            case 1: {
                treeAmount = TreeAmount.LEVEL1.getTreeAmount();
                break;
            }
            case 2: {
                treeAmount = TreeAmount.LEVEL2.getTreeAmount();
                break;
            }
            case 3: {
                treeAmount = TreeAmount.LEVEL3.getTreeAmount();
                break;
            }
            case 4: {
                treeAmount = TreeAmount.LEVEL4.getTreeAmount();
                break;
            }
            case 5: {
                treeAmount = TreeAmount.LEVEL5.getTreeAmount();
                break;
            }
        }
        return treeAmount;
    }

    public int treeSpeed(int level) {

        int treeSpeed = 0;

        switch (level) {

            case 1: {
                treeSpeed = TreeSpeed.LEVEL1.getTreeSpeed();
                break;
            }
            case 2: {
                treeSpeed = TreeSpeed.LEVEL2.getTreeSpeed();
                break;
            }
            case 3: {
                treeSpeed = TreeSpeed.LEVEL3.getTreeSpeed();
                break;
            }
            case 4: {
                treeSpeed = TreeSpeed.LEVEL4.getTreeSpeed();
                break;
            }
            case 5: {
                treeSpeed = TreeSpeed.LEVEL5.getTreeSpeed();
                break;
            }
        }
        return treeSpeed;
    }

    public synchronized Map<Integer, Picture> getTreesUp() {
        return treesUp;
    }

    public synchronized Map<Integer, Picture> getTreesDown() {
        return treesDown;
    }

    public synchronized Picture getUpTree(int index) {
        return treesUp.get(index);
    }

    public synchronized Picture getDownTree(int index) {
        return treesDown.get(index);
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void threadTree() {

        try {
            Thread.sleep(treeSpeed(level));
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void run() {

        for (int i = 0; i < treesUp.size(); i++) {

            while ((getUpTree(i).getX() > Grid.PADDING) && !Game.isGameOver && !Game.isLevelCompleted) {

                try {

                    getUpTree(i).translate(-10, 0);
                    getDownTree(i).translate(-10, 0);

                } catch (Exception ex) {
                    System.out.println();
                }

                if ((getDownTree(i).getX() < Grid.columnToX(20)) && (i < treesDown.size() - 1)) {

                    try {

                        getUpTree(i + 1).translate(-10, 0);
                        getDownTree(i + 1).translate(-10, 0);

                    } catch (Exception ex) {
                        System.out.println();
                    }
                }

                threadTree();
            }
        }
    }
}
