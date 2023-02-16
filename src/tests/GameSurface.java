package tests;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class GameSurface extends JPanel {

    private RenderingHints renderingHints;
    private Font font;

    public GameSurface() {
        super();
        setSize(300, 300);
        setPreferredSize(new Dimension(300, 300));
        Map<RenderingHints.Key, Object> hintsMap = Map.of(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON,
                RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY
        );
        renderingHints = new RenderingHints(hintsMap);
        try {
            InputStream fontIs = this.getClass().getResourceAsStream("/resources/fonts/vt323-v17-latin-regular.ttf");
            Font uniFont = Font.createFont(Font.TRUETYPE_FONT, fontIs);
            font = uniFont.deriveFont(20f);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void init() {
        getGraphics().setFont(font);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHints(renderingHints);
        g2d.setColor(Color.ORANGE);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        drawCircles(g2d);
        drawHud(g2d);
    }

    private void drawHud(Graphics2D g2d) {
        g2d.drawString("FPS: 39.49", 5, 15);
    }

    private void drawCircles(Graphics2D g2d) {
        Dimension size = getSize();
        double w = size.getWidth();
        double h = size.getHeight();
        Ellipse2D e = new Ellipse2D.Double(0, 0, 80, 130);
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.gray);
        for (double deg = 0; deg < 360; deg += 5) {
            AffineTransform at = AffineTransform.getTranslateInstance(w / 2, h / 2);
            at.rotate(Math.toRadians(deg));
            g2d.draw(at.createTransformedShape(e));
        }
    }

    public void setSize(int size) {
      setSize(new Dimension(size, size));
        setPreferredSize(new Dimension(size, size));
    }

}
