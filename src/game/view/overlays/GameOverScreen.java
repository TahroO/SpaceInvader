package game.view.overlays;

import game.view.GameView;

import java.awt.*;

public class GameOverScreen extends Overlay{
    public GameOverScreen(Font uniFont) {
        super(uniFont);
    }
    public void draw(Graphics2D g2d, int canvasWidth, int canvasHeight) {
        super.draw(g2d, canvasWidth, canvasHeight);
        String start = "Game Over!";
        g2d.setColor(Color.magenta);
        g2d.setFont(boldFont);
        drawStringCenter(g2d, start, (canvasHeight / 2) + (canvasHeight / 6), canvasWidth);
    }
}
