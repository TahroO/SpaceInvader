package game.objects;

import java.awt.*;

/**
 * Represents a bullet game object.
 */
public class Bullet extends GameObject {
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

    @Override
    public void draw(Graphics2D g2d, int canvasWidth, int canvasHeight) {
        g2d.setColor(Color.PINK);
        g2d.fillRect(
                toPixel(canvasWidth, posX),
                toPixel(canvasHeight, posY),
                toPixel(canvasWidth, width),
                toPixel(canvasHeight, height)
        );
    }

}
