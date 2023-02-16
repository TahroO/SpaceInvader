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

    public void draw(Graphics2D g2d, int offsetX, int offsetY, int scale) {
        drawScreen(g2d, offsetX, offsetY, scale);
    }

    private void drawScreen(Graphics2D g2d, int offsetX, int offsetY, int gameSize) {
        g2d.setColor(Color.black);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
        g2d.fillRect(offsetX, offsetY, gameSize, gameSize);
        String title = "Space Invaders";
        AffineTransform tx1 = new AffineTransform();
        double scaleFactor = gameSize / 800d;
        double translationX = offsetX * (1d / scaleFactor - 1d);
        double translationY = offsetY * (1d / scaleFactor - 1d);
        tx1.scale(scaleFactor, scaleFactor);
        tx1.translate(translationX, translationY);
        g2d.setTransform(tx1);
        g2d.setColor(Color.CYAN);
        g2d.fillRect(offsetX, offsetY,100,100);
        //g2d.drawString(title, 100, 100);
//        int yHalf = offsetY / 2;
//        g2d.setColor(Color.cyan);
//        g2d.setFont(largeFont);
//        AffineTransform tx1 = new AffineTransform();
//        //tx1.translate(110, 20);
//        double scaleFactor = 1d;
//        tx1.scale(scaleFactor, scaleFactor);
//        g2d.setTransform(tx1);
//        drawStringCenter(g2d, title, yHalf, offsetX, scaleFactor);
    }


   protected void drawStringCenter(Graphics2D g2d, String screenText, int posY, int canvasWidth, double scaleFactor) {
        int length = g2d.getFontMetrics().stringWidth(screenText);
        int posX = (int) Math.round((canvasWidth / 2) - (length / 2) * scaleFactor);
        g2d.drawString(screenText, posX, posY);
    }
}
