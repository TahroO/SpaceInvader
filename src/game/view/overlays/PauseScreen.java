package game.view.overlays;

import java.awt.*;

public class PauseScreen extends Overlay{
    public PauseScreen(Font uniFont) {
        super(uniFont);
    }
    public void draw(Graphics2D g2d, int canvasWidth, int canvasHeight) {
        super.draw(g2d, canvasWidth, canvasHeight);
        String start = "Game paused - press any button!";
        g2d.setColor(Color.blue);
        g2d.setFont(boldFont);
        drawStringCenter(g2d, start, (canvasHeight / 2) + (canvasHeight / 6), canvasWidth);
    }
}
