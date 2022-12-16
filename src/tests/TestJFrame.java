package tests;

import javax.swing.*;

public class TestJFrame {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Space Invaders");
        GameSurface gameSurface = new GameSurface();
        frame.add(gameSurface);
        frame.setSize(600, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameSurface.init();
    }
}
