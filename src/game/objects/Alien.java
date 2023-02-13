package game.objects;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents an Alien entity.
 */
public class Alien extends GameObject {
    public static final double ALIEN_WIDTH = 0.053488372;
    public static final double ALIEN_HEIGHT = 0.043023256;
    public static final double ALIEN_SPACING = 0.013953488;

    private int dir = 1;
    private double vx;
    private long timePassed;
    private double stepsPerSecond;

    /**
     *  Creates a new Alien object.
     * @param x X-position (in px) on panel.
     * @param y Y-position (in px) on panel.
     */
    public Alien(double x, double y) {
        super(x, y, ALIEN_WIDTH, ALIEN_HEIGHT);
    }

    /**
     * Sets the number of movements per second an alien makes.
     * @param stepsPerSecond Number of movements per second.
     */
    public void setStepsPerSecond(double stepsPerSecond) {
        this.stepsPerSecond = stepsPerSecond;
    }

    /**
     * Sets horizontal velocity.
     * @param vx Velocity in units per second.
     */
    public void setVelocity(double vx) {
        this.vx = vx;
    }

    /**
     * Gets horizontal movement direction.
     * @return 1 = left to right, -1 = right to left.
     */
    public int getDir() {
        return dir;
    }

    /**
     * Switches direction of movement.
     */
    public void switchDirection() {
        dir *= -1;
        posY += ALIEN_HEIGHT;
    }

    /**
     * Creates a new set of alien objects.
     * @param rows Number of rows to create.
     * @param cols Number of columns to create.
     * @return Collection of newly created alien objects.
     */
    public static Collection<Alien> createAlienGang(int rows, int cols) {
        double margin = 0.136046512;
        double marginTop = 0.261627907;
        double rowHeight = 0.077906977;
        double stride = ALIEN_WIDTH + ALIEN_SPACING;
        Collection<Alien> aliens = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            double y = marginTop + row * rowHeight;
            for (int col = 0; col < cols; col++) {
                double x = margin + col * stride;
                aliens.add(new Alien(x, y));
            }
        }
        return aliens;
    }


    @Override
    public void update(int timeDelta) {
        timePassed += timeDelta;
        if (timePassed >= 1000d / stepsPerSecond) {
            double stride = vx * stepsPerSecond;
            posX += dir * stride;
            timePassed = (int) (timePassed - 1000d / stepsPerSecond);
        }
    }

    @Override
    public void draw(Graphics2D g2d, int canvasWidth, int canvasHeight) {
        g2d.setColor(Color.WHITE);
        g2d.fillRect(
                toPixel(canvasWidth, posX),
                toPixel(canvasWidth, posY),
                toPixel(canvasWidth, width),
                toPixel(canvasWidth, height)
        );
    }

}
