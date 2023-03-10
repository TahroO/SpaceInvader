package game.objects;

import game.GameObject;
import game.GameView;

import java.awt.*;

/**
 * Represents a spaceship.
 */
public class Spaceship extends GameObject {
    public static final int SHIP_WIDTH = 80;
    public static final int SHIP_HEIGHT = 30;
    private final int dir;
    private final int vx;

    /**
     * Creates a new SpaceShip instance.
     * @param x
     * @param y
     * @param vx
     * @param dir
     */
    public Spaceship(int x, int y, int vx, int dir) {
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
        int y = 35;
        int vx = 133;
        int x = -Spaceship.SHIP_WIDTH;
        if (dir < 0) {
            x = GameView.WIDTH + Spaceship.SHIP_WIDTH;
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
        int t = (int) Math.ceil((vx / 1000d) * timeDelta);
        x += dir * t;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.CYAN);
        g2d.fillOval(x, y, SHIP_WIDTH, SHIP_HEIGHT);
    }

}
