package tests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TestJFrame2 extends JFrame implements KeyListener {

    public TestJFrame2(String title) {
        super(title);
    }

    public static void main(String[] args) {
        TestJFrame2 frame = new TestJFrame2("Space Invaders");
        MyComponent gameSurface = new MyComponent();
        gameSurface.addKeyListener(frame);
        frame.add(gameSurface);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
