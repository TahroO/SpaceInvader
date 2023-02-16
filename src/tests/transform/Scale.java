package tests.transform;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Scale extends JPanel {

    private Font font;

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        InputStream fontIs = this.getClass().getResourceAsStream("/resources/fonts/vt323-v17-latin-regular.ttf");
        try {
            Font uniFont = Font.createFont(Font.TRUETYPE_FONT, fontIs);
            font = uniFont.deriveFont(20f);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }

        String test = "TestString";
        g2d.setFont(font);
        g2d.setColor(new Color(150, 150, 150));
        g2d.drawString(test, 100,100);

        AffineTransform tx1 = new AffineTransform();
        tx1.translate(110, 20);
        tx1.scale(0.5, 0.5);

        g2d.setTransform(tx1);
        g2d.drawString(test, 100,100);

        AffineTransform tx2 = new AffineTransform();
        tx2.translate(200, 20);
        tx2.scale(1.5, 1.5);

        g2d.setTransform(tx2);
        g2d.drawString(test, 100,100);

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

