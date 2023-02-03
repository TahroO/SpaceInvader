package game;

import java.awt.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Renders the game's heads up display.
 */
public class GameHud implements Renderable {
    public static final int HUD_HEIGHT = 18;

    int points;
    int round;
    float avgFps = 0;
    DecimalFormat fpsFormat;

    /**
     * Creates a new GameHud instance.
     */
    public GameHud() {
        fpsFormat = new DecimalFormat("#.00");
        fpsFormat.setRoundingMode(RoundingMode.HALF_UP);
    }

    /**
     * Sets player points.
     * @param points Player points.
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Sets current round.
     * @param round Current round.
     */
    public void setRound(int round) {
        this.round = round;
    }

    @Override
    public void update(int timeDelta) {
        avgFps += (timeDelta / 1000. - avgFps) * 0.03;
    }

    @Override
    public void draw(Graphics2D g, int canvasWidth, int canvasHeight) {
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, GameView.WIDTH, HUD_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawString("FPS: " + fpsFormat.format(1. / avgFps), 5, 15);
        g.drawString("Points: " + this.points, 700,15);
        g.drawString("Round: " + this.round, 370,15);
    }

}
