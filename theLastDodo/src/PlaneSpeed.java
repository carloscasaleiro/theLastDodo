/**
 * Enum with Plane Level\Speed
 */
public enum PlaneSpeed {

    LEVEL1(25),
    LEVEL2(45),
    LEVEL3(60),
    LEVEL4(70),
    LEVEL5(75);

    private final int planeSpeed;

    PlaneSpeed(int planeSpeed) {
        this.planeSpeed = planeSpeed;
    }

    public int getPlaneSpeed() {
        return planeSpeed;
    }
}
