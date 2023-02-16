package tests.fullscreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyJFrame extends JFrame implements KeyListener {
    private GraphicsDevice device;
    private boolean isFullscreenSupported;
    private boolean isFullscreen;

    public MyJFrame() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        device = ge.getDefaultScreenDevice();
        isFullscreenSupported = device.isFullScreenSupported();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void toggleFullscreen() {
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
        System.out.println("MyJFrame: " + e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_F11) {
            toggleFullscreen();
        }
    }

    public static void main(String[] args) {
        MyJFrame frame = new MyJFrame();
        MyJComponent gameSurface = new MyJComponent();
        gameSurface.addKeyListener(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gameSurface);
        frame.pack();
        frame.setVisible(true);
    }

}
