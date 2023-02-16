package game.model;

/**
 * Represents a bullet game object.
 */
public class Bullet extends ModelBase {
    public static final double BULLET_WITH = 1 / 200d;
    public static final double BULLET_HEIGHT = 1 / 60d;
    private double vy = 0.375;

    /**
     * Creates a new Bullet object at the given position.
     * @param x X-position (in px) on panel.
     * @param y Y-position (in px) on panel.
     */
    public Bullet(double x, double y) {
        super(x, y, BULLET_WITH, BULLET_HEIGHT);
    }

    @Override
    public void update(int timeDelta) {
        posY -= vy / 1000d * timeDelta;
    }

}
