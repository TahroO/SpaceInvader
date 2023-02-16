package game.view;

import game.GameController;
import game.objects.Renderable;
import game.view.objects.Alien;
import game.view.overlays.GameOverScreen;
import game.view.overlays.Overlay;
import game.view.overlays.PauseScreen;
import game.view.overlays.StartScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Game view takes care of drawing everything.
 */
public class GamePanel extends JPanel implements ActionListener, KeyListener, GameView {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;

    private GameController controller;
    private GameFrame frame;
    private Map<Integer, Overlay> overlays;
    private Overlay overlay;
    private final RenderingHints renderingHints;

    // TODO public?
    private Font font;
    public Font largeFont;
    public Font boldFont;

    private ArrayList<Renderable> renderables = new ArrayList<>();

    /**
     * Creates a new GameView instance.
     */
    public GamePanel() {
        super();
        // Configure JPanel.
        setSize(WIDTH, HEIGHT);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(new Color(30, 30, 30));
        setFocusable(true);
        setDoubleBuffered(true);
        // Create rendering hints.
        Map<RenderingHints.Key, Object> hintsMap = Map.of(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON,
                RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY
        );
        renderingHints = new RenderingHints(hintsMap);
        overlays = new HashMap<>();
        addKeyListener(this);
    }

    /**
     * Initializes the view.
     */
    public void init() {
        InputStream fontIs = this.getClass().getResourceAsStream("/resources/fonts/vt323-v17-latin-regular.ttf");
        try {
            Font uniFont = Font.createFont(Font.TRUETYPE_FONT, fontIs);
            font = uniFont.deriveFont(20f);
            largeFont = uniFont.deriveFont(50f);
            boldFont = uniFont.deriveFont(Font.BOLD, 30f);
            getGraphics().setFont(font);
            overlays.put(OVERLAY_START, new StartScreen(uniFont));
            overlays.put(OVERLAY_PAUSE, new PauseScreen(uniFont));
            overlays.put(OVERLAY_GAME_OVER, new GameOverScreen(uniFont));
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Renderable> getRenderables() {
        return renderables;
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

        g2d.setColor(Color.ORANGE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        double componentWidth = getWidth();
        double componentHeight = getHeight();
        int hudTopHeight, hudBottomHeight;
        int gameSize;
        int x, y;
        if (componentWidth >= Math.round(componentHeight * 0.75)) {
            gameSize = (int) Math.round(componentHeight * 0.75);
            hudTopHeight = (int) Math.round((componentHeight - gameSize) * 0.5);
            hudBottomHeight = (int) componentHeight - gameSize - hudTopHeight;
            x = (int) Math.round((componentWidth - gameSize) / 2);
            y = 0;
        } else {
            gameSize = (int) componentWidth;
            hudTopHeight = (int) Math.round(componentWidth / 75 * 12.5);
            hudBottomHeight = hudTopHeight;
            x = 0;
            y = (int) Math.round((componentHeight - gameSize - hudTopHeight * 2) / 2);
        }

        drawTopHud(g2d, x, y, gameSize, hudTopHeight);
        drawBottomHud(g2d, x, y + gameSize + hudTopHeight, gameSize, hudBottomHeight);
        drawGame(g2d, x, y + hudTopHeight, gameSize);

        if (overlay != null) {
            overlay.draw(g2d, getWidth(), getHeight());
        }
    }

    private void drawGameObject(Graphics2D g2d, Renderable gameObject, int offsetX, int offsetY, int scale) {
        if (gameObject instanceof game.objects.Alien) {
            Alien.getInstance().draw(g2d, gameObject, offsetX, offsetY, scale);
        } else {
            g2d.setColor(Color.WHITE);
            Rectangle2D.Double bounds = gameObject.getBounds();
            g2d.fillRect(
                    (int) Math.round(offsetX + bounds.x * scale),
                    (int) Math.round(offsetY + bounds.y * scale),
                    (int) Math.round(bounds.width * scale),
                    (int) Math.round(bounds.height * scale)
            );
        }
    }

    private void drawTopHud(Graphics2D g2d, int offsetX, int offsetY, int widht, int height) {
        g2d.setColor(Color.GRAY);
        g2d.fillRect(offsetX, offsetY, widht, height);
    }

    private void drawBottomHud(Graphics2D g2d, int offsetX, int offsetY, int widht, int height) {
        g2d.setColor(Color.GRAY);
        g2d.fillRect(offsetX, offsetY, widht, height);
    }

    private void drawGame(Graphics2D g2d, int offsetX, int offsetY, int scale) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(offsetX, offsetY, scale, scale);
        g2d.setColor(Color.WHITE);
        // Draw game objects in reverse order.
        for (int i = renderables.size() - 1; i >= 0; i--) {
            Renderable gameObject = renderables.get(i);
            //gameObject.draw(g2d, offsetX, offsetY, scale);
            drawGameObject(g2d, gameObject, offsetX, offsetY, scale);
        }
    }

    @Override
    public void setOverlay(int overlay) {
        this.overlay = overlay > 0 ? overlays.get(overlay) : null;
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}