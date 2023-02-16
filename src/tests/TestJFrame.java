package tests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class TestJFrame {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Space Invaders");
        GridBagLayout gbl = new GridBagLayout();
        frame.setLayout(gbl);
        GameSurface gameSurface = new GameSurface();
        frame.add(gameSurface);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                Dimension dim = e.getComponent().getSize();
                System.out.println(dim.width + ":" + dim.height);
                gameSurface.setSize(Math.min(dim.width, dim.height));
                frame.revalidate();
            }
        });
        gameSurface.init();
    }
}
