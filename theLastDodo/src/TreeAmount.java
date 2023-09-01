/**
 * Enum with Tree Level\Amount
 */
public enum TreeAmount {

    LEVEL1(20),
    LEVEL2(40),
    LEVEL3(60),
    LEVEL4(80),
    LEVEL5(100);

    private final int treeAmount;

    TreeAmount(int treeAmount) {
        this.treeAmount = treeAmount;
    }

    public int getTreeAmount() {
        return treeAmount;
    }
}
