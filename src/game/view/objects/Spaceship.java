package game.view.objects;

import game.model.Model;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Spaceship extends DrawableBase {

    private static Spaceship instance;

    private Spaceship() {
    }

    private int[][] pattern = {
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };

    @Override
    public void draw(Graphics2D g2d, Model gameObject, int offsetX, int offsetY, int scale) {
        Rectangle2D.Double bounds = gameObject.getBounds();
        game.model.Spaceship spaceship = (game.model.Spaceship) gameObject;
        g2d.setColor(Color.red);
        int width = toPixel(scale, bounds.width);
        int height = toPixel(scale, bounds.height);
        drawMatrix(g2d, pattern, width, height, toPixel(scale, bounds.x) + offsetX, toPixel(scale, bounds.y) + offsetY);

    }

    public static Spaceship getInstance() {
        if (instance == null) {
            instance = new Spaceship();
        }
        return instance;
    }
}
