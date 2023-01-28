package game;

import game.objects.Alien;
import game.objects.Bullet;
import game.objects.Gun;
import game.objects.Spaceship;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class GameController implements KeyListener, ActionListener {
    public static final int ROWS = 4;
    public static final int COLS = 10;

    private final int DELAY = 16;
    private Timer timer;

    private boolean spacePressed;
    private final Random rng = new Random();

    // Game objects.
    private final Gun gun;
    private final GameHud hud;
    private Bullet bullet;
    private Spaceship ship;
    private final ArrayList<Alien> aliens = new ArrayList<>();

    private int points;
    private int round;
    private int alienVx = 27;
    private double alienSPS = 3;
    private long nextShipTimeMs;
    private long lastFrameTimeMs;

    private GameSurface view;

    private ArrayList<Renderable> renderables;

    public GameController() {
        renderables = new ArrayList<>();
        view = new GameSurface(renderables);
        gun = new Gun();
        hud = new GameHud();
        renderables.add(gun);
        renderables.add(hud);
        createAliens(alienVx, alienSPS);
        setNextShipTime();
        lastFrameTimeMs = System.currentTimeMillis();

    }

    /**
     * Initializes this game surface.
     */
    public void init() {
        view.addKeyListener(this);
        view.addKeyListener(gun);
        timer = new Timer(DELAY, this);
        timer.addActionListener(view);
        timer.start();
        view.init();
    }

    public GameSurface getView() {
        return view;
    }

    private void createAliens(int vx, double stepsPerSecond) {
        int margin = 15;
        int marginTop = 80;
        int spacing = (int) Math.round(Alien.ALIEN_WIDTH * 0.75);
        int rowHeight = Alien.ALIEN_HEIGHT + spacing;
        int stride = Alien.ALIEN_WIDTH + spacing;
        int maxDistance = view.getWidth() - (stride * COLS - spacing) - (2 * margin);
        for (int row = 0; row < ROWS; row++) {
            int y = marginTop + row * rowHeight;
            for (int col = 0; col < COLS; col++) {
                int x = margin + col * stride;
                Alien alien = new Alien(x, y, x + maxDistance, vx, stepsPerSecond);
                aliens.add(alien);
                renderables.add(alien);
            }
        }
    }

    private void startShip() {
        if (ship != null) {
            return;
        }
        int dir = rng.nextInt(0, 2);
        ship = Spaceship.startShip(dir == 0 ? -1 : 1);
        renderables.add(ship);
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
            renderables.add(bullet);
        }
        if (bullet != null && bullet.getY() < GameHud.HUD_HEIGHT) {
            renderables.remove(bullet);
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
                renderables.remove(this.ship);
                ship = null;
                setNextShipTime();
            }
        } else if (currentTimeMs >= nextShipTimeMs) {
            startShip();
        }

        renderables.forEach(renderable -> renderable.update(lastFrameDelta));
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
                renderables.remove(bullet);
                bullet = null;
                aliens.remove(alien);
                renderables.remove(alien);
                points += 10;
                hud.setPoints(points);
                break;
            }
        }
        // Check ship collision with bullet.
        if (ship != null && bullet != null && ship.detectCollision(bullet)) {
            renderables.remove(ship);
            ship = null;
            points += 50;
            hud.setPoints(points);
            setNextShipTime();
        }
    }

    private void setNextShipTime() {
        this.nextShipTimeMs = System.currentTimeMillis() + rng.nextLong(10000, 20001);
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

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        update();
    }

}
