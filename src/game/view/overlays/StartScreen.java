package game.view.overlays;

import java.awt.*;

public class StartScreen extends Overlay {

    public StartScreen(Font uniFont) {
        super(uniFont);
    }

    @Override
    public void draw(Graphics2D g2d, int offsetX, int offsetY, int scale) {
        super.draw(g2d, offsetX, offsetY, scale);
        String start = "Start your Game!";
        g2d.setColor(Color.blue);
        g2d.setFont(boldFont);
        drawStringCenter(g2d, start, (offsetY / 2) + (offsetY / 6), offsetX, scale);
    }
}
