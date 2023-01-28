package game.objects;

import game.GameObject;
import game.GameView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Represents the player's gun.
 */
public class Gun extends GameObject implements KeyListener {
    public static final int GUN_WIDTH = 60;
    public static final int GUN_HEIGHT = 20;
    protected final int DIR_RIGHT = 1;
    protected final int DIR_LEFT = -1;


    private final int[] xPointOffsets = {0, 60, 60, 35, 35, 30, 25, 25, 0, 0};
    private final int[] yPointOffsets = {20, 20, 10, 10, 10, 0, 10, 10, 10, 20};
    private final int[] currentXs = new int[xPointOffsets.length];
    private final int[] currentYs = new int[yPointOffsets.length];
    private final Color color = Color.decode("#48A803");
    private final int X_MIN = 0;
    private final int X_MAX;
    protected int dir;
    // PX per second;
    private int vx = 200;
    private boolean leftArrowKeyPressed, rightArrowKeyPressed;

    /**
     * Creates a new Gun instance.
     */
    public Gun() {
        super((GameView.WIDTH - GUN_WIDTH) / 2, GameView.HEIGHT - 75, GUN_WIDTH, GUN_HEIGHT);
        X_MAX = GameView.WIDTH - GUN_WIDTH;
    }

    /**
     * Updates gun image position.
     */
    private void calcPolygon() {
        for (int i = 0; i < xPointOffsets.length; i++) {
            int newX = x + xPointOffsets[i];
            int newY = y + yPointOffsets[i];
            currentXs[i] = newX;
            currentYs[i] = newY;
        }
    }

    @Override
    public void update(int timeDelta) {
        if (dir == 0) {
            return;
        }

        int t = (int) Math.round((vx / 1000d) * timeDelta);
        x += dir * t;
        if (x > X_MAX) {
            x = X_MAX;
        } else if (x < X_MIN) {
            x = 0;
        }
    }

    @Override
    public void draw(Graphics2D g) {
        calcPolygon();
        Polygon poly = new Polygon(currentXs, currentYs, xPointOffsets.length);
        g.setColor(color);
        g.fill(poly);
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO move to GameController.
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_RIGHT && !rightArrowKeyPressed) {
            dir = DIR_RIGHT;
            rightArrowKeyPressed = true;
        } else if (keyCode == KeyEvent.VK_LEFT && !leftArrowKeyPressed) {
            dir = DIR_LEFT;
            leftArrowKeyPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO move to GameController.
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_RIGHT) {
            rightArrowKeyPressed = false;
            dir = leftArrowKeyPressed ? DIR_LEFT : 0;
        }
        else if (keyCode == KeyEvent.VK_LEFT) {
            leftArrowKeyPressed = false;
            dir = rightArrowKeyPressed ? DIR_RIGHT : 0;
        }
    }

}
