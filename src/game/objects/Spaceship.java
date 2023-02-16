package game.objects;

import java.awt.*;

/**
 * Represents a spaceship.
 */
public class Spaceship extends GameObject {
    public static final double SHIP_WIDTH = 0.1;
    public static final double SHIP_HEIGHT = 0.0375;
    private final int dir;
    private final double vx;

    /**
     * Creates a new SpaceShip instance.
     * @param x Object x-position.
     * @param y Object y-position.
     * @param vx Horizontal velocity.
     * @param dir Direction of movement (1 ltr, -1 rtl).
     */
    public Spaceship(double x, double y, double vx, int dir) {
        super(x, y, SHIP_WIDTH, SHIP_HEIGHT);
        this.vx = vx;
        this.dir = dir;
    }

    /**
     * Returns a new SpaceShip instance.
     * @param dir Direction (either -1 for rtl or 1 for ltr).
     * @return A SpaceShip object.
     */
    public static Spaceship startShip(int dir) {
        double y = 0.04375;
        double vx = 0.16625;
        double x = -Spaceship.SHIP_WIDTH;
        if (dir < 0) {
            x = 1d + Spaceship.SHIP_WIDTH;
        }
        return new Spaceship(x, y, vx, dir);
    }

    /**
     * Gets spaceship's current direction.
     * @return Either -1 when direction is right to left or 1 for left to right.
     */
    public int getDirection() {
        return dir;
    }

    @Override
    public void update(int timeDelta) {
        posX += dir * vx / 1000d * timeDelta;
    }

}
