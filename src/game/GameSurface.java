package game;

import game.objects.Bullet;
import game.objects.Gun;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

public class GameSurface extends JPanel implements ActionListener, KeyListener {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public final int DELAY = 16;
    private Timer timer;
    private final RenderingHints renderingHints;
    private final Gun gun;
    private final GameHud hud;
    private Bullet bullet;
    private Font font;
    private long frameCounter;
    private long lastFrameTimeMs;
    private ArrayList<GameObject> gameObjects = new ArrayList<>();
    private boolean spacePressed;

    /**
     * Creates a new GameSurface instance.
     */
    public GameSurface() {
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
        lastFrameTimeMs = System.currentTimeMillis();
        // Create game objects.
        gun = new Gun();
        hud = new GameHud();
    }

    /**
     * Initializes this game surface.
     */
    public void init() {
        InputStream fontIs = this.getClass().getResourceAsStream("/resources/fonts/vt323-v17-latin-regular.ttf");
        try {
            Font uniFont = Font.createFont(Font.TRUETYPE_FONT, fontIs);
            font = uniFont.deriveFont(20f);
            getGraphics().setFont(font);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        addKeyListener(this);
        addKeyListener(gun);
        timer = new Timer(DELAY, this);
        timer.start();
    }

    /**
     * Calls update() on every game object.
     */
    private void update() {
        long currentTimeMs = System.currentTimeMillis();
        int lastFrameDelta = (int) (currentTimeMs - lastFrameTimeMs);
        if (bullet == null && spacePressed) {
            bullet = new Bullet(gun.getX() + (Gun.GUN_WIDTH - Bullet.BULLET_WITH) / 2, gun.getY() - Bullet.BULLET_HEIGHT);
            gameObjects.add(bullet);
        }
        if (bullet != null && bullet.getY() < GameHud.HUD_HEIGHT) {
            gameObjects.remove(bullet);
            bullet = null;
        }
        gameObjects.forEach(gameObject -> gameObject.update(lastFrameDelta));
        gun.update(lastFrameDelta);
        hud.update(lastFrameDelta);
        lastFrameTimeMs = currentTimeMs;
    }

    /**
     * Calls draw() on every game object.
     *
     * @param g A Graphics object.
     */
    private void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(font);
        g2d.setRenderingHints(renderingHints);
        // Draw game objects.
        gameObjects.forEach(gameObject -> gameObject.draw(g2d));
        gun.draw(g2d);
        hud.draw(g2d);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        update();
        draw(g);
        frameCounter++;
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
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
    }

}
