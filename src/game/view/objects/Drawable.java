package game.view.objects;

import game.model.Model;

import java.awt.*;

public interface Drawable {
    /**
     * Draws a GameObject.
     * @param g2d AWT 2D graphics object.
     */
    void draw(Graphics2D g2d, Model gameObject, int offsetX, int offsetY, int scale);

}
