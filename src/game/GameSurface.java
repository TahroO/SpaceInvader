package game;

import game.objects.Alien;
import game.objects.Bullet;
import game.objects.Gun;
import game.objects.Spaceship;

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
import java.util.Random;

public class GameSurface extends JPanel implements ActionListener, KeyListener {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int ROWS = 4;
    public static final int COLS = 10;

    private final int DELAY = 16;
    private Timer timer;
    private final RenderingHints renderingHints;
    private Font font;
    private boolean spacePressed;
    private final Random rng = new Random();

    // Game objects.
    private final Gun gun;
    private final GameHud hud;
    private Bullet bullet;
    private Spaceship ship;
    private final ArrayList<GameObject> gameObjects = new ArrayList<>();
    private final ArrayList<Alien> aliens = new ArrayList<>();

    private long frameCounter;
    private long lastFrameTimeMs;
    private int points;
    private int round;
    private int alienVx = 27;
    private double alienSPS = 3;
    private long nextShipTimeMs;

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
        createAliens(alienVx, alienSPS);
        setNextShipTime();
    }

    private void createAliens(int vx, double stepsPerSecond) {
        int margin = 15;
        int marginTop = 80;
        int spacing = (int) Math.round(Alien.ALIEN_WIDTH * 0.75);
        int rowHeight = Alien.ALIEN_HEIGHT + spacing;
        int stride = Alien.ALIEN_WIDTH + spacing;
        int maxDistance = getWidth() - (stride * COLS - spacing) - (2 * margin);
        for (int row = 0; row < ROWS; row++) {
            int y = marginTop + row * rowHeight;
            for (int col = 0; col < COLS; col++) {
                int x = margin + col * stride;
                Alien alien = new Alien(x, y, x + maxDistance, vx, stepsPerSecond);
                gameObjects.add(alien);
                aliens.add(alien);
            }
        }
    }

    public void startShip() {
        if (ship != null) {
            return;
        }

        final int[] directions = {-1, 1};
        int dir = directions[rng.nextInt(0, 2)];
        int y = 35;
        int vx = 10;
        int x = -Spaceship.SHIP_WIDTH;
        if (dir < 0) {
            x = GameSurface.WIDTH + Spaceship.SHIP_WIDTH;
        }
        this.ship = new Spaceship(x, y, vx, dir);
        gameObjects.add(this.ship);
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

        // Update bullet.
        if (bullet == null && spacePressed) {
            bullet = new Bullet(gun.getX() + (Gun.GUN_WIDTH - Bullet.BULLET_WITH) / 2, gun.getY() - Bullet.BULLET_HEIGHT);
            gameObjects.add(bullet);
        }
        if (bullet != null && bullet.getY() < GameHud.HUD_HEIGHT) {
            gameObjects.remove(bullet);
            bullet = null;
        }

        // Start or update ship.
        if (ship != null) {
            ship.update(lastFrameDelta);
            int shipX = ship.getX();
            int shipDirection = ship.getDirection();
            // Ship hat fenster verlassen?
            if (shipX > GameSurface.WIDTH && shipDirection == 1
                    || shipX < -Spaceship.SHIP_WIDTH && shipDirection == -1
            ) {
                gameObjects.remove(this.ship);
                ship = null;
                setNextShipTime();
            }
        } else if (currentTimeMs >= nextShipTimeMs) {
            startShip();
        }

        gameObjects.forEach(gameObject -> gameObject.update(lastFrameDelta));
        gun.update(lastFrameDelta);
        detectCollisions();
        hud.update(lastFrameDelta);

        // Check next level.
        if (aliens.isEmpty()) {
            round += 1;
            hud.setRound(round);
            createAliens(alienVx += 15, alienSPS *= 1.25);
        }
        lastFrameTimeMs = currentTimeMs;
    }

    private void detectCollisions() {
        if (bullet == null) {
            return;
        }
        // Check alien collision with bullet.
        for (Alien alien : aliens) {
            if (alien.detectCollision(bullet)) {
                gameObjects.remove(bullet);
                bullet = null;
                aliens.remove(alien);
                gameObjects.remove(alien);
                points += 10;
                hud.setPoints(points);
                break;
            }
        }
        // Check ship collision with bullet.
        if (ship != null && bullet != null && ship.detectCollision(bullet)) {
            gameObjects.remove(ship);
            ship = null;
            points += 50;
            hud.setPoints(points);
            setNextShipTime();
        }
    }

    private void setNextShipTime() {
        this.nextShipTimeMs = System.currentTimeMillis() + rng.nextLong(10000, 20001);
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
