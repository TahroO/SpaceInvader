package game.objects;

import game.GameObject;
import game.GameSurface;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Alien extends GameObject {

    public static final int ALIEN_WIDTH = 20;
    public static final int ALIEN_HEIGHT = 20;
    protected int dir = 1;
    // PX per second;
    private int vx = 50;
    private int minX;
    private int maxX;

    public Alien(int x, int y, int maxX) {
        super(x, y, ALIEN_WIDTH, ALIEN_HEIGHT);
        this.maxX = maxX;
        this.minX = x;

    }

    @Override
    public void update(int timeDelta) {
        int t = (int) Math.round((vx / 1000d) * timeDelta);
        x += dir * t;
        if (x > maxX) {
            dir = -1;
        } else if (x < minX) {
            dir = 1;
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.fillRect(x, y, ALIEN_WIDTH, ALIEN_HEIGHT);

    }

}
