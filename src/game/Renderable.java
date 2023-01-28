package game;

import java.awt.*;

/**
 * Defines an interface for objects which can be rendered
 * by GameView.
 */
public interface Renderable {
    /**
     * Draws ths GameObject.
     * @param g2d AWT 2D graphics object.
     */
    void draw(Graphics2D g2d);

    /**
     * Updates the GameObject's status.
     * @param timeDelta Milliseconds passed since last frame.
     */
    void update(int timeDelta);

}
