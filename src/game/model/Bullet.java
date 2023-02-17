package game.model;

/**
 * Represents a bullet game object.
 */
public class Bullet extends ModelBase {
    public static final double BULLET_WITH = 1 / 200d;
    public static final double BULLET_HEIGHT = 1 / 60d;
    private double vy = 0.375;
    private int dirY;

    /**
     * Creates a new Bullet object at the given position.
     * @param x X-position (in px) on panel.
     * @param y Y-position (in px) on panel.
     */
    public Bullet(double x, double y) {
        this(x, y, -1);
    }

    public Bullet(double x, double y, int direction) {
        super(x, y, BULLET_WITH, BULLET_HEIGHT);
        dirY = direction;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    @Override
    public void update(int timeDelta) {
        posY += vy / 1000d * timeDelta * dirY;
    }

}
