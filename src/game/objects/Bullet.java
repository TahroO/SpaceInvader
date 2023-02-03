package game.objects;

import game.GameObject;
import java.awt.*;

/**
 * Represents a bullet game object.
 */
public class Bullet extends GameObject {
    public static final int BULLET_WITH = 4;
    public static final int BULLET_HEIGHT = 10;
    private int vy = 300;

    /**
     * Creates a new Bullet object at the given position.
     * @param x X-position (in px) on panel.
     * @param y Y-position (in px) on panel.
     */
    public Bullet(int x, int y) {
        super(x, y, BULLET_WITH, BULLET_HEIGHT);
    }

    @Override
    public void update(int timeDelta) {
        int t = (int) Math.round((vy / 1000d) * timeDelta);
        y -= t;
    }

    @Override
    public void draw(Graphics2D g2d, int canvasWidth, int canvasHeight) {
        g2d.setColor(Color.PINK);
        g2d.fillRect(x, y, size.width, size.height);
    }

}
