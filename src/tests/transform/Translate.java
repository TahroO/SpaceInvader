package tests.transform;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Translate extends JPanel {

    public Translate() {
        setPreferredSize(new Dimension(800, 800));
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        drawGutter(g2d);

        int baseSize = 800;
        int gameSize = getWidth();
        int offsetX = 80;
        double scaleFactor = gameSize / (double) baseSize;

        AffineTransform tx1 = new AffineTransform();
        tx1.scale(scaleFactor, scaleFactor);
        double translation = offsetX * (1. / scaleFactor - 1.);
        tx1.translate(translation, translation);

        g2d.setTransform(tx1);
        g2d.setColor(Color.GREEN);
        g2d.fillRect(offsetX, 80, 81, 81);


        /*
        int gameSize = 400;

        g2d.setColor(Color.GREEN);
        g2d.drawRect(0, 0, gameSize, gameSize);

        g2d.setColor(Color.RED);
        g2d.fillRect((int) Math.round((gameSize - 100) * 0.5), 0, 100, 100);


        g2d.setColor(Color.BLUE);
        g2d.setTransform(tx1);
        g2d.fillRect(0, 0, 100, 100);
        */
    }

    protected void drawGutter(Graphics2D g2d) {
        int gutterDivision = 10;
        int cellSize = 800 / gutterDivision;
        g2d.setColor(Color.PINK);
        for (int x = 1; x < gutterDivision; x++) {
            g2d.drawLine(x * cellSize, 0, x * cellSize, 800);
        }
        for (int y = 1; y < gutterDivision; y++) {
            g2d.drawLine(0, y * cellSize, 800, y * cellSize);
        }
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Scaling");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Translate());
        frame.setSize(800, 800);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(true);
    }
}

