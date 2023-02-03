package game.view.overlays;

import game.view.GameView;

import java.awt.*;

public class StartScreen extends Overlay{

    public StartScreen(Font uniFont, GameView view) {
        super(uniFont, view);
    }

    @Override
    public void draw(Graphics2D g2d, int canvasWidth, int canvasHeight) {
        super.draw(g2d, canvasWidth, canvasHeight);
        String start = "Start your Game!";
        g2d.setColor(Color.blue);
        g2d.setFont(boldFont);
        g2d.drawString(start, 280,400);

    }
}
