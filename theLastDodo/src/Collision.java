import Sound.Fail;
import Sound.Hit;
import Sound.LevelComplete;

/**
 * Collision system
 */
public class Collision implements Runnable{

    private final Tree tree;
    private final Bird bird;

    private final Hit hit = new Hit();
    private final Fail fail = new Fail();
    private final LevelComplete levelComplete = new LevelComplete();

    public Collision(Tree tree, Bird bird) {
        this.tree = tree;
        this.bird = bird;
    }

    @Override
    public void run() {

        while (!Game.isGameOver && !Game.isLevelCompleted) {

            if ((bird.getBirdY() <= Grid.PADDING + 10) || (bird.getBirdMaxY() >= 540)) {

                Game.gameOver();
                hit.start();
                fail.start();
            }

            for (int i = 0; i < tree.getTreesUp().size(); i++) {

                if ((bird.getBirdMaxX() >= tree.getTreesUp().get(i).getX() - 5) && (bird.getBirdX() <= tree.getTreesUp().get(i).getMaxX() - 60)) {

                    if (bird.getBirdY() <= tree.getTreesUp().get(i).getMaxY()) {

                        if ((tree.getTreesUp().get(i).getX() > 200) && (tree.getTreesUp().get(i).getX() < 400)) {

                            Game.gameOver();
                            hit.start();
                            fail.start();
                        }
                    }
                }
            }

            for (int i = 0; i < tree.getTreesDown().size(); i++) {

                if ((bird.getBirdMaxX() >= tree.getTreesDown().get(i).getX() - 5) && (bird.getBirdX() <= tree.getTreesDown().get(i).getMaxX() - 20)) {

                    if (bird.getBirdMaxY() >= tree.getTreesDown().get(i).getY()) {

                        if ((tree.getTreesDown().get(i).getX() > 200) && (tree.getTreesDown().get(i).getX() < 400)) {

                            Game.gameOver();
                            hit.start();
                            fail.start();
                        }
                    }
                }
            }

            if (tree.getTreesUp().get(tree.getTreesUp().size() - 1).getMaxX() < 220) {

                Game.levelCompleted();
                levelComplete.start();
            }
        }
    }
}
