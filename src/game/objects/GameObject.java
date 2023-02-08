package game.objects;

import game.Renderable;

import java.awt.*;
import java.awt.geom.Rectangle2D;

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
    protected Rectangle2D.Double bounds;
    /**
     * Sprite.
     */
    protected Image sprite;

    public GameObject(double x, double y, double width, double height) {
        posX = x;
        posY = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle2D.Double();
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
    public Rectangle2D.Double getBounds() {
        bounds.setRect(posX, posY, width, height);
        return bounds;
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
