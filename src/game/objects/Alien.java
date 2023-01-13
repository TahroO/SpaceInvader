package game.objects;

import game.GameObject;
import java.awt.*;

public class Alien extends GameObject {

    public static final int ALIEN_WIDTH = 20;
    public static final int ALIEN_HEIGHT = 20;
    protected int dir = 1;
    // PX per second.
    private int vx = 50;
    private int minX;
    private int maxX;

    private int frame = 0;

    private double step = 0;
    private int stepsPerSecond = 2;


    public Alien(int x, int y, int maxX) {
        super(x, y, ALIEN_WIDTH, ALIEN_HEIGHT);
        this.maxX = maxX;
        this.minX = x;

    }

    @Override
    public void update(int timeDelta) {
        step += (vx / 1000d) * timeDelta;
        if((int) Math.round(step) % (vx / stepsPerSecond) != 0){
            return;
        }
        int t = vx / stepsPerSecond;
        if (dir == 1 && x + t > maxX) {
            dir = -1;
            y += ALIEN_HEIGHT;
        } else if (dir == -1 && x - t < minX) {
            dir = 1;
            y += ALIEN_HEIGHT;
        }
        x += dir * t;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.fillRect(x, y, ALIEN_WIDTH, ALIEN_HEIGHT);

    }
}
