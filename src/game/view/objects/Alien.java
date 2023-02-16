package game.view.objects;

import game.model.Renderable;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Alien extends DrawableBase {
    private static Alien instance;

    private Alien() {}

    @Override
    public void draw(Graphics2D g2d, Renderable gameObject, int offsetX, int offsetY, int scale) {
        Rectangle2D.Double bounds = gameObject.getBounds();
        game.model.Alien alien = (game.model.Alien) gameObject;
        Color color = switch (alien.getPosition()) {
            case TOP -> Color.PINK;
            case MIDDLE -> Color.BLUE;
            default -> Color.WHITE;
        };
        g2d.setColor(color);
        g2d.fillRect(
                toPixel(scale, bounds.x) + offsetX,
                toPixel(scale, bounds.y) + offsetY,
                toPixel(scale, bounds.width),
                toPixel(scale, bounds.height)
        );
    }

    public static Alien getInstance() {
        if (instance == null) {
            instance = new Alien();
        }
        return instance;
    }
    // m 575.91,2434.93 v 71.53 h -72.183 v 95.07 h 143.164 v 147.11 h -75.485 v 91 h -69.468 v 73.57 h -92.911 v 69.66 h -73.234 -0.559 -73.238 v -69.66 h -92.918 v -73.57 H 99.6094 v -91 H 24.1016 V 2601.53 H 167.281 v -95.07 H 95.0977 v -71.53 H 0 v -95.49 h 95.4727 v 95.49 h 72.3633 v 70.71 h 94.918 v 95.89 h 72.48 0.559 72.445 v -95.89 h 94.926 v -70.71 h 72.387 v -95.49 h 95.465 v 95.49 z m -313.156,237.93 h -95.473 v 99.39 h 95.473 z m 145.484,0 v 99.39 h 95.489 v -99.39 h -95.489
}
