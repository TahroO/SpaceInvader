package game.view.overlays;

import java.awt.*;

public class StartScreen extends Overlay {

    public StartScreen(Font uniFont) {
        super(uniFont);
    }

    @Override
    public void draw(Graphics2D g2d, int canvasWidth, int canvasHeight) {
        super.draw(g2d, canvasWidth, canvasHeight);
        String start = "Start your Game!";
        g2d.setColor(Color.blue);
        g2d.setFont(boldFont);
        drawStringCenter(g2d, start, (canvasHeight / 2) + (canvasHeight / 6), canvasWidth);
    }
}
