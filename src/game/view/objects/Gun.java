package game.view.objects;

import game.model.Model;

import java.awt.*;
import java.awt.geom.Rectangle2D;

    public class Gun extends DrawableBase {

        private static game.view.objects.Gun instance;

        private Gun() {
        }

        private int[][] pattern = {
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        };

        @Override
        public void draw(Graphics2D g2d, Model gameObject, int offsetX, int offsetY, int scale) {
            Rectangle2D.Double bounds = gameObject.getBounds();
            game.model.Gun gun = (game.model.Gun) gameObject;
            g2d.setColor(Color.red);
            int width = toPixel(scale, bounds.width);
            int height = toPixel(scale, bounds.height);
            drawMatrix(g2d, pattern, width, height, toPixel(scale, bounds.x) + offsetX, toPixel(scale, bounds.y) + offsetY);

        }

        public static game.view.objects.Gun getInstance() {
            if (instance == null) {
                instance = new game.view.objects.Gun();
            }
            return instance;
        }
    }


