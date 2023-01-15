package game;

import java.awt.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Game's HUD.
 */
public class GameHud {
    public static final int HUD_HEIGHT = 18;
    int points;
    int round;
    float avgFps = 0;
    DecimalFormat fpsFormat;

    public GameHud() {
        fpsFormat = new DecimalFormat("#.00");
        fpsFormat.setRoundingMode(RoundingMode.HALF_UP);
    }

    public void update(int timeDelta) {
        avgFps += (timeDelta / 1000. - avgFps) * 0.03;
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, GameSurface.WIDTH, HUD_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawString("FPS: " + fpsFormat.format(1. / avgFps), 5, 15);
        g.drawString("Points: " + this.points, 700,15);
        g.drawString("Round: " + this.round, 370,15);
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setRound(int round) {
        this.round = round;
    }

}
