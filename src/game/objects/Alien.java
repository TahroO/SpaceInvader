package game.objects;

import game.GameObject;
import java.awt.*;

public class Alien extends GameObject {

    public static final int ALIEN_WIDTH = 20;
    public static final int ALIEN_HEIGHT = 20;
    protected int dir = 1;
    // PX per second.
    private int vx = 27;
    private int minX;
    private int maxX;

    private int frame = 0;

    private long timePassed = 0;
    private double stepsPerSecond = 3;


    public Alien(int x, int y, int maxX) {
        super(x, y, ALIEN_WIDTH, ALIEN_HEIGHT);
        this.maxX = maxX;
        this.minX = x;

    }

    @Override
    public void update(int timeDelta) {
        timePassed += timeDelta;
        if (timePassed >= 1000d / stepsPerSecond) {
            int stride = (int) Math.round(vx / stepsPerSecond);
            if (dir == 1 && x + stride > maxX) {
                dir = -1;
                y += ALIEN_HEIGHT;
            } else if (dir == -1 && x - stride < minX) {
                dir = 1;
                y += ALIEN_HEIGHT;
            } else {
                x += dir * stride;
            }

            timePassed = (int) (timePassed - 1000d / stepsPerSecond);
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.fillRect(x, y, ALIEN_WIDTH, ALIEN_HEIGHT);

    }
}
