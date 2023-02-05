package game.view.overlays;

import game.objects.GameObject;
import game.view.GameView;

import java.awt.*;

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

    public void draw(Graphics2D g2d, int canvasWidth, int canvasHeight) {
        drawScreen(g2d, canvasWidth, canvasHeight);
    }

    private void drawScreen(Graphics2D g2d, int canvasWidth, int canvasHeight) {
        g2d.setColor(Color.white);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
        g2d.fillRect(0,0 , canvasWidth, canvasHeight);
        String title = "Space Invaders";
        int yHalf = canvasHeight / 2;
        g2d.setColor(Color.cyan);
        g2d.setFont(largeFont);
        drawStringCenter(g2d, title, yHalf, canvasWidth);
    }

    protected void drawStringCenter(Graphics2D g2d, String screenText, int posY, int canvasWidth) {
        int length = g2d.getFontMetrics().stringWidth(screenText);
        int posX = (canvasWidth / 2) - (length / 2);
        g2d.drawString(screenText, posX, posY);
    }
}
