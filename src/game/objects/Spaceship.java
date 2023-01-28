package game.objects;

import game.GameObject;
import game.GameSurface;

import java.awt.*;
import java.util.Collection;

public class Spaceship extends GameObject {

    public static final int SHIP_WIDTH = 80;
    public static final int SHIP_HEIGHT = 30;
    protected int dir;
    private int vx;

    public Spaceship(int x, int y, int vx, int dir) {
        super (x, y, SHIP_WIDTH, SHIP_HEIGHT);
        this.vx = vx;
        this.dir = dir;
    }

    public static Spaceship startShip(int dir) {
        int y = 35;
        int vx = 133;
        int x = -Spaceship.SHIP_WIDTH;
        if (dir < 0) {
            x = GameSurface.WIDTH + Spaceship.SHIP_WIDTH;
        }
        return new Spaceship(x, y, vx, dir);
    }

    public int getDirection() {
        return dir;
    }

    @Override
    public void update(int timeDelta) {
        int t = (int) Math.ceil((vx / 1000d) * timeDelta);
        x += dir * t;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.CYAN);
        g2d.fillOval(x, y, SHIP_WIDTH, SHIP_HEIGHT);
    }
}
