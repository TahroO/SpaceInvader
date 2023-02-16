package game;

import game.view.GameFrame;
import game.view.GamePanel;
import game.view.GameView;

import javax.swing.*;

/**
 * The game application.
 */
public class SpaceInvaders {

    public static void main(String[] args) {
        GameFrame frame = new GameFrame("Space Invaders");
        frame.setSize(GamePanel.WIDTH, GamePanel.HEIGHT);
        GamePanel view = new GamePanel(600, 800);
        view.addKeyListener(frame);
        GameController gameController = new GameController(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(view);
        frame.pack();;
        frame.setLocation(100, 100);
        frame.setResizable(true);
        frame.setVisible(true);
        gameController.init();
    }

}