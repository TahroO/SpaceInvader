package game.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame implements KeyListener {
    private final GraphicsDevice device;
    private final boolean isFullscreenSupported;
    private boolean isFullscreen;

    public GameFrame(String title) {
        super(title);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        device = ge.getDefaultScreenDevice();
        isFullscreenSupported = device.isFullScreenSupported();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void toggleFullscreen() {
        if (!isFullscreenSupported) {
            return;
        }
        if (isFullscreen) {
            device.setFullScreenWindow(null);
        } else {
            device.setFullScreenWindow(this);
        }
        isFullscreen = !isFullscreen;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_F11) {
            toggleFullscreen();
        }
    }

}
