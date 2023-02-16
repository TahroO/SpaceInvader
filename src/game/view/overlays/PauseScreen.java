package game.view.overlays;

import java.awt.*;

public class PauseScreen extends Overlay{
    public PauseScreen(Font uniFont) {
        super(uniFont);
    }
    public void draw(Graphics2D g2d, int offsetX, int offsetY, int gameSize) {
        super.draw(g2d, offsetX, offsetY, gameSize);
        String start = "Game paused - press any button!";
        g2d.setColor(Color.blue);
        g2d.setFont(boldFont);
        //drawStringCenter(g2d, start, 200, offsetX, offsetY);
    }
}
