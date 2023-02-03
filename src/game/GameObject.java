package game;

import java.awt.*;

/**
 * Base class for all game objects.
 */
abstract public class GameObject implements Renderable {
    /**
     * Object's current x and y position.
     */
    protected int x, y;
    protected double posX, posY;
    /**
     * Object dimension aka object's width and height.
     */
    protected double width, height;
    protected Dimension size;
    /**
     * Sprite.
     */
    protected Image sprite;

    public GameObject(double x, double y, double width, double height) {
        posX = x;
        posY = y;
        this.width = width;
        this.height = height;
    }
    /**
     * Constructs a new GameObject instance with a given
     * initial position.
     * @param x Initial x-position.
     * @param y Initial y-position.
     */
    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.size = new Dimension(width, height);
    }

    /**
     * Gets this GameObject's x-position.
     * @return GameObject's x-position.
     */
    public double getX() {
        return posX;
    }

    /**
     * Gets this GameObject's y-position.
     * @return GameObject's y-position.
     */
    public double getY() {
        return posY;
    }

    /**
     * Gets this game object's bounds.
     * @return Game object's bounds.
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, size.width, size.height);
    }


    /**
     * Checks if this GameObject collides with another one.
     * @param other The other GameObject.
     * @return True, if collision has been detected. False otherwise.
     */
    public boolean detectCollision(GameObject other) {
        return this.getBounds().intersects(other.getBounds());
    }

    protected int toPixel(int maxPx, double fract) {
        return (int) Math.round(maxPx * fract);
    }
}
