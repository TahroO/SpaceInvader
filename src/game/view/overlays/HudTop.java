package game.view.overlays;

import java.awt.*;

public class HudTop extends Overlay {
    int points;
    int life;
    int round;
    public HudTop(Font uniFont) {
        super(uniFont);
    }

    public void draw(Graphics2D g2d, int offsetX, int offsetY, int gameSize) {
        g2d.setColor(Color.blue);
        g2d.setFont(boldFont);
        drawString(g2d, String.format("Points: %s", points), 5, 125,  offsetX, offsetY, gameSize);
        drawString(g2d, String.format("Life: %s", life), 700, 125,  offsetX, offsetY, gameSize);
        drawStringCenter(g2d, String.format("Round: %s", round), 128, offsetX, offsetY, gameSize);
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public void setLife(int life) {
        this.life = life;
    }
    public void setRound(int round) {
        this.round = round;
    }
}
