package game.view.overlays;

import java.awt.*;
import java.awt.geom.AffineTransform;

abstract public class Overlay {

    Font uniFont;
    Font largeFont;
    Font boldFont;

    public Overlay(Font uniFont) {
        this.uniFont = uniFont;
        // TODO font sizes must depend on game view dimension.
        largeFont = uniFont.deriveFont(50f);
        boldFont = uniFont.deriveFont(Font.BOLD, 30f);
    }

    public void draw(Graphics2D g2d, int offsetX, int offsetY, int gameSize) {
        drawBackground(g2d, offsetX, offsetY, gameSize);
    }

    protected void drawBackground(Graphics2D g2d, int offsetX, int offsetY, int gameSize) {
        g2d.setColor(Color.GREEN);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
        g2d.fillRect(offsetX, offsetY, gameSize, gameSize);
    }

    protected void drawString(Graphics2D g2d, String screenText, int posX, int posY, int offsetX, int offsetY, int gameSize) {
        AffineTransform tx1 = new AffineTransform();
        double scaleFactor = gameSize / 800d;
        double translationX = offsetX * (1d / scaleFactor - 1d);
        double translationY = offsetY * (1d / scaleFactor - 1d);
        tx1.scale(scaleFactor, scaleFactor);
        tx1.translate(translationX, translationY);
        AffineTransform oldTransform = g2d.getTransform();
        g2d.setTransform(tx1);
        g2d.setColor(Color.CYAN);
        g2d.drawString(screenText, posX + offsetX, posY + offsetY);
        g2d.setTransform(oldTransform);
    }

   protected void drawStringCenter(Graphics2D g2d, String screenText, int posY, int offsetX, int offsetY, int gameSize) {
        int length = g2d.getFontMetrics().stringWidth(screenText);
        int posX = (int) Math.round((800 / 2d) - (length / 2d));
        drawString(g2d, screenText, posX, posY, offsetX, offsetY, gameSize);
    }
}
