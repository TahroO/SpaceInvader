package tests.fullscreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyJComponent extends JPanel implements KeyListener {

    public MyJComponent() {
        System.out.println("MyJComponent");
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        setDoubleBuffered(true);
        setPreferredSize(new Dimension(600, 800));
        setSize(new Dimension(600, 800));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.ORANGE);
        g.fillRect(0, 0, getWidth(), getHeight());

        System.out.println(getWidth() + ":" + getHeight());

        double componentWidth = getWidth();
        double componentHeight = getHeight();
        int hudTopHeight, hudBottomHeight;
        int gameSize;
        int x, y;
        if (componentWidth >= Math.round(componentHeight * 0.75)) {
            gameSize = (int) Math.round(componentHeight * 0.75);
            hudTopHeight = (int) Math.round((componentHeight - gameSize) * 0.5);
            hudBottomHeight = (int) componentHeight - gameSize - hudTopHeight;
            x = (int) Math.round((componentWidth - gameSize) / 2);
            y = 0;
        } else {
            gameSize = (int) componentWidth;
            hudTopHeight = (int) Math.round(componentWidth / 75 * 12.5);
            hudBottomHeight = hudTopHeight;
            x = 0;
            y = (int) Math.round((componentHeight - gameSize - hudTopHeight * 2) / 2);
        }

        // Hud top.
        g.setColor(Color.BLUE);
        g.fillRect(x, y, gameSize, hudTopHeight);
        // Game area.
        g.setColor(Color.GREEN);
        g.fillRect(x, y + hudTopHeight, gameSize, gameSize);
        // Hud bottom.
        g.setColor(Color.BLUE);
        g.fillRect(x, y + hudTopHeight + gameSize, gameSize, hudBottomHeight);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
