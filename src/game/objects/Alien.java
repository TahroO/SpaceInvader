package game.objects;

import java.awt.*;

/**
 * Represents an Alien entity.
 */
public class Alien extends GameObject {
    public static final double ALIEN_WIDTH = 0.053488372;
    public static final double ALIEN_HEIGHT = 0.043023256;

    private int dir = 1;
    private final Color COLOR = Color.decode("#ffffff");
    // PX per second.
    private double vx;
    private double minX;
    private double maxX;
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
    public Alien(double x, double y, double minX, double maxX, double vx, double stepsPerSecond) {
        super(x, y, ALIEN_WIDTH, ALIEN_HEIGHT);
        this.maxX = maxX;
        this.minX = minX;
        this.vx = vx;
        this.stepsPerSecond = stepsPerSecond;

    }

    @Override
    public void update(int timeDelta) {
        timePassed += timeDelta;
        if (timePassed >= 1000d / stepsPerSecond) {
            double stride = vx / stepsPerSecond;
            if (dir == 1 && posX + stride > maxX) {
                dir = -1;
                posY += ALIEN_HEIGHT;
            } else if (dir == -1 && posX - stride < minX) {
                dir = 1;
                posY += ALIEN_HEIGHT;
            } else {
                posX += dir * stride;
            }

            timePassed = (int) (timePassed - 1000d / stepsPerSecond);
        }
    }

    @Override
    public void draw(Graphics2D g2d, int canvasWidth, int canvasHeight) {
        g2d.setColor(COLOR);
        g2d.fillRect(
                toPixel(canvasWidth, posX),
                toPixel(canvasWidth, posY),
                toPixel(canvasWidth, width),
                toPixel(canvasWidth, height)
        );

    }
}
