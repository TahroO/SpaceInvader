package game.objects;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Represents the player's gun.
 */
public class Gun extends GameObject implements KeyListener {
    public static final double GUN_WIDTH = 0.058139535;
    public static final double GUN_HEIGHT = 0.026744186;
    protected final int DIR_RIGHT = 1;
    protected final int DIR_LEFT = -1;

    //private final int[] xPointOffsets = {0, 60, 60, 35, 35, 30, 25, 25, 0, 0};
    //private final int[] yPointOffsets = {20, 20, 10, 10, 10, 0, 10, 10, 10, 20};
    //private final int[] currentXs = new int[xPointOffsets.length];
    //private final int[] currentYs = new int[yPointOffsets.length];
    private final Color color = Color.decode("#48A803");
    private final int X_MIN = 0;
    private final double X_MAX = 1 - GUN_WIDTH;
    protected int dir;
    // PX per second;
    private double vx = 0.23255814;
    private boolean leftArrowKeyPressed, rightArrowKeyPressed;

    /**
     * Creates a new Gun instance.
     */
    public Gun() {
        super((1 - GUN_WIDTH) / 2d, 1 - 0.11627907, GUN_WIDTH, GUN_HEIGHT);
    }

    @Override
    public void update(int timeDelta) {
        if (dir == 0) {
            return;
        }

        double t = (vx / 1000d) * timeDelta;
        posX += dir * t;
        if (posX > X_MAX) {
            posX = X_MAX;
        } else if (posX < X_MIN) {
            posX = 0;
        }
    }

    @Override
    public void draw(Graphics2D g, int canvasWidth, int canvasHeight) {
        g.setColor(color);
        g.fillRect(
                toPixel(canvasWidth, posX),
                toPixel(canvasWidth, posY),
                toPixel(canvasWidth, width),
                toPixel(canvasWidth, height)
        );
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
