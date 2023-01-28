package game.objects;

import game.GameObject;
import java.awt.*;

/**
 * Represents an Alien entity.
 */
public class Alien extends GameObject {
    public static final int ALIEN_WIDTH = 20;
    public static final int ALIEN_HEIGHT = 20;

    private int dir = 1;
    // PX per second.
    private int vx = 27;
    private int minX;
    private int maxX;
    private int frame;
    private long timePassed;
    private double stepsPerSecond = 3;

    /**
     *  Creates a new Alien object.
     * @param x X-position (in px) on panel.
     * @param y Y-position (in px) on panel.
     * @param maxX Max x-position (in px) after which alien needs to switch direction.
     * @param vx Velocity in px per second.
     * @param stepsPerSecond ...
     */
    public Alien(int x, int y, int maxX, int vx, double stepsPerSecond) {
        super(x, y, ALIEN_WIDTH, ALIEN_HEIGHT);
        this.maxX = maxX;
        this.minX = x;
        this.vx = vx;
        this.stepsPerSecond = stepsPerSecond;

    }

    @Override
    public void update(int timeDelta) {
        timePassed += timeDelta;
        if (timePassed >= 1000d / stepsPerSecond) {
            int stride = (int) Math.round(vx / stepsPerSecond);
            if (dir == 1 && x + stride > maxX) {
                dir = -1;
                y += ALIEN_HEIGHT;
            } else if (dir == -1 && x - stride < minX) {
                dir = 1;
                y += ALIEN_HEIGHT;
            } else {
                x += dir * stride;
            }

            timePassed = (int) (timePassed - 1000d / stepsPerSecond);
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.fillRect(x, y, ALIEN_WIDTH, ALIEN_HEIGHT);

    }
}
