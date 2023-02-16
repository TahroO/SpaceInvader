package game.view.overlays;

import java.awt.*;

public class HudTop extends Overlay {
    int points;
    int life;
    public HudTop(Font uniFont) {
        super(uniFont);
    }

    public void draw(Graphics2D g2d, int offsetX, int offsetY, int gameSize) {
        String counter = "Points: " + points + "Life: " + life;
        g2d.setColor(Color.blue);
        g2d.setFont(boldFont);
        drawString(g2d, counter, 10, 100,  offsetX, 0, gameSize);
    }
    private void setPoints(int points) {
        this.points = points;
    }
    private void setLife(int life) {
        this.life = life;
    }
}
