package game.model;

import java.awt.geom.Rectangle2D;

/**
 * Defines an interface for objects which can be rendered
 * by GameView.
 */
public interface Model {
    /**
     * Updates the GameObject's status.
     * @param timeDelta Milliseconds passed since last frame.
     */
    void update(int timeDelta);

    /**
     * Gets this game object's bounds.
     * @return Game object's bounds.
     */
    Rectangle2D.Double getBounds();

    boolean isDead();

    boolean isAlive();

    int getPoints();

    enum State {
        DEAD,
        DYING,
        ALIVE
    }
}
