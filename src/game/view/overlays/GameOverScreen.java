package game.view.overlays;

import java.awt.*;

public class GameOverScreen extends Overlay{
    public GameOverScreen(Font uniFont) {
        super(uniFont);
    }
    public void draw(Graphics2D g2d, int offsetX, int offsetY, int gameSize) {
        super.draw(g2d, offsetX, offsetY,gameSize);
        String start = "Game Over!";
        g2d.setColor(Color.magenta);
        g2d.setFont(boldFont);
        //drawStringCenter(g2d, start, 200, offsetX, offsetY);
    }
}
