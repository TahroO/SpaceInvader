package game;

import javax.swing.*;

/**
 * The game application.
 */
public class SpaceInvaders {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Space Invaders");
        frame.setSize(GameView.WIDTH, GameView.HEIGHT);
        GameController gameController = new GameController();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gameController.getView());
        frame.setLocation(100, 100);
        frame.setResizable(false);
        frame.setVisible(true);
        gameController.init();
    }
}