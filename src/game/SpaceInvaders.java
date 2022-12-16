package game;

import javax.swing.*;

/**
 * The game application.
 */
public class SpaceInvaders {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Space Invaders");
        frame.setSize(GameSurface.WIDTH, GameSurface.HEIGHT);
        GameSurface surface = new GameSurface();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(surface);
        frame.setLocation(100, 100);
        frame.setResizable(false);
        frame.setVisible(true);
        surface.init();
    }

}
