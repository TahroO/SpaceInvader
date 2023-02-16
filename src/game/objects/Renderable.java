package game.objects;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Defines an interface for objects which can be rendered
 * by GameView.
 */
public interface Renderable {
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

}
