package game.view.overlays;

import game.objects.GameObject;
import game.view.GameView;

import java.awt.*;

abstract public class Overlay {

    Font uniFont;
    Font largeFont;
    Font boldFont;
    GameView view;

    public Overlay(Font uniFont, GameView view) {
        this.uniFont = uniFont;
        this.view = view;
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
        int xHalf = 250;
        int yHalf = canvasHeight / 2;
        g2d.setColor(Color.cyan);
        g2d.setFont(largeFont);
        g2d.drawString(title, xHalf,yHalf);
        g2d.drawLine(xHalf,yHalf + 2, xHalf + view.getFontMetrics(largeFont).stringWidth(title), yHalf + 2);
    }
}
