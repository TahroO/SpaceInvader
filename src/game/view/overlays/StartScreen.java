package game.view.overlays;

import java.awt.*;

public class StartScreen extends Overlay {

    public StartScreen(Font uniFont) {
        super(uniFont);
    }

    @Override
    public void draw(Graphics2D g2d, int offsetX, int offsetY, int gameSize) {
        super.draw(g2d, offsetX, offsetY, gameSize);
        String start = "Start your Game!";
        g2d.setColor(Color.blue);
        g2d.setFont(boldFont);
        drawStringCenter(g2d, start, 200, offsetX, offsetY, gameSize);
    }
}
