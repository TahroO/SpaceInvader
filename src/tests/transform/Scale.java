package tests.transform;
import java.awt.*;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Scale extends JPanel {

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(150, 150, 150));
        g2d.fillRect(0, 0, 80, 50);

        AffineTransform tx1 = new AffineTransform();
        tx1.translate(110, 20);
        tx1.scale(1, 0.5);

        g2d.setTransform(tx1);
        g2d.fillRect(0, 0, 80, 50);

        AffineTransform tx2 = new AffineTransform();
        tx2.translate(200, 20);
        tx2.scale(1.5, 1.5);

        g2d.setTransform(tx2);
        g2d.fillRect(0, 0, 80, 50);

    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Scaling");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Scale());
        frame.setSize(330, 160);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

