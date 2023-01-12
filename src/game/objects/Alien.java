package game.objects;

import game.GameObject;
import game.GameSurface;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Alien extends GameObject {

    public static final int ALIEN_WIDTH = 20;
    public static final int ALIEN_HEIGHT = 20;

    public Alien( int x, int y ) {
        super( x, y, ALIEN_WIDTH, ALIEN_HEIGHT);

    }

    @Override
    public void update(int lastFrameDelta) {

    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.fillRect( x, y, ALIEN_WIDTH, ALIEN_HEIGHT);

    }

}
