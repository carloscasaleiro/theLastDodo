/**
 * Enum with Tree Level\Speed
 */
public enum TreeSpeed {

    LEVEL1(30),
    LEVEL2(27),
    LEVEL3(24),
    LEVEL4(21),
    LEVEL5(18);

    private final int treeSpeed;

    TreeSpeed(int treeSpeed) {
        this.treeSpeed = treeSpeed;
    }

    public int getTreeSpeed() {
        return treeSpeed;
    }
}
