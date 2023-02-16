package game.view.objects;

abstract public class DrawableBase implements Drawable {
    /**
     * Scales internal units to game view size.
     * @param scale Scaling factor.
     * @param value Value to be scaled.
     * @return Scaled value.
     */
    protected int toPixel(int scale, double value) {
        return (int) Math.round(scale * value);
    }

}
