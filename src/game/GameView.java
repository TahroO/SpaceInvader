package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

/**
 * Game view takes care of drawing everything.
 */
public class GameView extends JPanel implements ActionListener {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int OVERLAY_NONE = 0;
    public static final int OVERLAY_START = 1;
    public static final int OVERLAY_PAUSE = 2;
    public static final int OVERLAY_OVER = 3;

    private int overlay;

    private final RenderingHints renderingHints;
    private Font font;

    private Font largeFont;
    private final ArrayList<Renderable> renderables;

    /**
     * Creates a new GameView instance.
     */
    public GameView(ArrayList<Renderable> renderables) {
        super();
        // Configure JPanel.
        setSize(WIDTH, HEIGHT);
        setBackground(new Color(30, 30, 30));
        setFocusable(true);
        // Create rendering hints.
        Map<RenderingHints.Key, Object> hintsMap = Map.of(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON,
                RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY
        );
        renderingHints = new RenderingHints(hintsMap);
        this.renderables = renderables;
    }

    /**
     * Initializes this game surface.
     */
    public void init() {
        InputStream fontIs = this.getClass().getResourceAsStream("/resources/fonts/vt323-v17-latin-regular.ttf");
        try {
            Font uniFont = Font.createFont(Font.TRUETYPE_FONT, fontIs);
            font = uniFont.deriveFont(20f);
            largeFont = uniFont.deriveFont(50f);
            getGraphics().setFont(font);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Calls draw() on every renderable object.
     *
     * @param g A Graphics object.
     */
    private void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(font);
        g2d.setRenderingHints(renderingHints);

        // Draw game objects in reverse order.
        for (int i = renderables.size() - 1; i >= 0; i--) {
            Renderable gameObject = renderables.get(i);
            gameObject.draw(g2d);
        }

        switch (overlay) {
            case OVERLAY_START -> drawStartScreen(g2d);
            case OVERLAY_PAUSE -> drawPauseScreen(g2d);
            case OVERLAY_OVER -> drawGameOverScreen(g2d);
        }
    }

    private void drawStartScreen(Graphics2D g2d) {
        g2d.setColor(Color.YELLOW);
        g2d.fillRect(20, 40, 200, 200);
        g2d.setColor(Color.cyan);
        g2d.setFont(largeFont);
        g2d.drawString("Space Invader", 50,50);

    }

    private void drawPauseScreen(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.fillRect(20, 40, 200, 200);
        g2d.drawString("Pause", 50,50);
    }

    private void drawGameOverScreen(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        g2d.fillRect(20, 40, 200, 200);
    }

    public void setOverlay(int overlay) {
        this.overlay = overlay;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();
    }

}
