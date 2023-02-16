package game.model;

import java.awt.geom.Rectangle2D;

/**
 * Base class for all game objects.
 */
abstract public class ModelBase implements Model {
    /**
     * Object's current x and y position.
     */
    protected double posX, posY;
    /**
     * Object dimension aka object's width and height.
     */
    protected double width, height;
    protected Rectangle2D.Double bounds;
    private State state = State.ALIVE;

    /**
     * Creates a new GameObject instance.
     *
     * @param x      Object x-position.
     * @param y      Object y-position.
     * @param width  Object width.
     * @param height Object height.
     */
    public ModelBase(double x, double y, double width, double height) {
        posX = x;
        posY = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle2D.Double();
    }

    /**
     * Gets object's x-position.
     *
     * @return Game object's x-position.
     */
    public double getX() {
        return posX;
    }

    /**
     * Gets object's y-position.
     *
     * @return Game object y-position.
     */
    public double getY() {
        return posY;
    }

    @Override
    public Rectangle2D.Double getBounds() {
        bounds.setRect(posX, posY, width, height);
        return bounds;
    }

    public void setDead() {
        state = State.DEAD;
    }

    public boolean isDead() {
        return state == State.DEAD;
    }

    /**
     * Checks if this GameObject collides with another one.
     *
     * @param other The other GameObject.
     * @return True, if collision has been detected. False otherwise.
     */
    public boolean detectCollision(ModelBase other) {
        return this.getBounds().intersects(other.getBounds());
    }

    /**
     * Scales internal units to game view size.
     *
     * @param scale Scaling factor.
     * @param value Value to be scaled.
     * @return Scaled value.
     */
    protected int toPixel(int scale, double value) {
        return (int) Math.round(scale * value);
    }

    public enum State {
        ALIVE,
        DEAD
    }

}
