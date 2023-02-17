package game;

import game.model.*;
import game.view.GamePanel;
import game.view.GameView;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.*;

public class GameController implements KeyListener, ActionListener {
    public static final int ROWS = 5;
    public static final int COLS = 11;

    private final int DELAY = 16;
    private Timer timer;
    private final Random rng = new Random();
    private final GamePanel view;

    // Game objects.
    private final List<Model> renderables;
    private final List<Alien> aliens = new ArrayList<>();
    private final Gun gun;
    //private final GameHud hud;
    private Bullet gunBullet;
    private List<Bullet> alienBullets = new ArrayList<>();
    private Spaceship ship;

    // Game state.
    private boolean pause = true;
    private int round = 1;
    private int life = 3;
    private int points;
    private long nextShipTimeMs;
    private long nextAlienBulletTimeMs;
    private long lastFrameTimeMs;
    private boolean spacePressed;

    private final double GAME_WIDTH = 0.869767442;

    /**
     * Creates a new GameController instance.
     */
    public GameController(GamePanel view) {
        renderables = view.getRenderables();
        this.view = view;
        gun = new Gun();
        renderables.add(gun);
        createAlienGang();
        //setNextShipTime();
        lastFrameTimeMs = System.currentTimeMillis();
    }

    /**
     * Initializes controller and view.
     */
    public void init() {
        view.addKeyListener(this);
        view.addKeyListener(gun);
        timer = new Timer(DELAY, this);
        timer.addActionListener(view);
        timer.start();
        view.init();
        view.setLife(life);
        view.setRound(round);
        view.setOverlay(GamePanel.OVERLAY_START);
    }

    /**
     * Creates a new alien gang.
     */
    private void createAlienGang() {
        Collection<Alien> newAliens = Alien.createAlienGang(ROWS, COLS);
        aliens.addAll(newAliens);
        renderables.addAll(newAliens);
        updateAlienVelocity();
    }

    /**
     * Calculates alien velocity.
     *
     * @param alienCount Number of aliens alive.
     * @return Alien velocity.
     */
    private double getAlienVelocity(int alienCount) {
        double scale = ROWS * COLS / (double) alienCount;
        double aliensWidth = COLS * (Alien.ALIEN_WIDTH + Alien.ALIEN_SPACING) - Alien.ALIEN_SPACING;
        return (GAME_WIDTH - aliensWidth) / 16d * scale;
    }

    /**
     * Sets new velocity on every alien.
     */
    private void updateAlienVelocity() {
        // TODO scale vx with number of rounds played.
        double alienVx = getAlienVelocity(aliens.size()) * round * 0.5;
        double stepsPerSecond = 150 * alienVx;
        aliens.forEach(alien -> {
            alien.setVelocity(alienVx);
            alien.setStepsPerSecond(stepsPerSecond);
        });
    }

    /**
     * Gets the current game view.
     *
     * @return A game view object.
     */
    public GamePanel getView() {
        return view;
    }

    /**
     * Starts a new spaceship.
     */
    private void startShip() {
        if (ship != null) {
            return;
        }
        int dir = rng.nextInt(0, 2);
        ship = Spaceship.startShip(dir == 0 ? -1 : 1);
        renderables.add(ship);
    }

    /**
     * Starts a new spaceship.
     */
    private void startAlienBullet(Alien alien) {
        Rectangle2D.Double bounds = alien.getBounds();
        double bulletX = bounds.x + bounds.width / 2d;
        double bulletY = bounds.y + bounds.height;
        Bullet alienBullet = new Bullet(bulletX, bulletY, 1);
        alienBullet.setVy(0.2);
        alienBullets.add(alienBullet);
        renderables.add(alienBullet);
    }

    /**
     * Updates all game objects.
     */
    private void update() {
        long currentTimeMs = System.currentTimeMillis();
        int lastFrameDelta = (int) (currentTimeMs - lastFrameTimeMs);
        if (!pause) {
            updateBullets();
            updateAliens(currentTimeMs);
            // TODO update in reverse order?
            renderables.forEach(renderable -> renderable.update(lastFrameDelta));
            updateSpaceShip(currentTimeMs);
            detectCollisions();
            // Check next level.
            if (aliens.isEmpty()) {
                nextRound();
            }
        }
        lastFrameTimeMs = currentTimeMs;
    }

    /**
     * Updates the alien gang (switches direction).
     */
    private void updateAliens(long currentTimeMs) {
        double edgeRight = 1 - 0.065116279;
        double edgeLeft = 0.065116279;
        if (currentTimeMs >= nextAlienBulletTimeMs) {
            startAlienBullet(aliens.get(rng.nextInt(0, aliens.size())));
            setNextAlienBulletTime();
        }
        for (Alien alien : aliens) {
            if (alien.getDir() == 1 && alien.getX() + Alien.ALIEN_WIDTH >= edgeRight
                    || alien.getDir() == -1 && alien.getX() <= edgeLeft) {
                aliens.forEach(Alien::switchDirection);
                break;
            }
        }
    }

    /**
     * Updates player bullet.
     */
    private void updateBullets() {
        // TODO a game object can now determine on its own, if it has left the view.
        // TODO move to Bullet.java.
        if (gunBullet == null && spacePressed) {
            gunBullet = new Bullet(gun.getX() + (Gun.GUN_WIDTH - Bullet.BULLET_WITH) / 2, gun.getY() - Bullet.BULLET_HEIGHT);
            renderables.add(gunBullet);
        }
        if (gunBullet != null && gunBullet.getY() < 0) {
            renderables.remove(gunBullet);
            gunBullet = null;
        }

        Iterator<Bullet> i = alienBullets.listIterator();
        while (i.hasNext()) {
            Bullet alienBullet = i.next();
            if (alienBullet.getY() > 1) {
                renderables.remove(alienBullet);
                i.remove();
            }
        }
    }

    /**
     * Updates the space ship.
     *
     * @param currentTimeMs
     */
    private void updateSpaceShip(long currentTimeMs) {
        // TODO ship can update itself in SpaceShip.java.
        // Start or update ship.
        if (ship != null) {
            double shipX = ship.getX();
            int shipDirection = ship.getDirection();
            // Ship left game area?
            if (shipX > 1 && shipDirection == 1
                    || shipX < -Spaceship.SHIP_WIDTH && shipDirection == -1
            ) {
                renderables.remove(this.ship);
                ship = null;
                setNextShipTime();
            }
        } else if (currentTimeMs >= nextShipTimeMs) {
            startShip();
        }
    }

    /**
     * Next round.
     */
    private void nextRound() {
        round += 1;
        view.setRound(round);
        createAlienGang();
    }

    /**
     * Detects collision of game objects.
     */
    private void detectCollisions() {
        if (gunBullet != null) {
            // Check alien collision with bullet.
            for (Alien alien : aliens) {
                if (alien.detectCollision(gunBullet)) {
                    renderables.remove(gunBullet);
                    renderables.remove(alien);
                    aliens.remove(alien);
                    points += alien.getPoints();
                    gunBullet = null;
                    view.setPoints(points);
                    updateAlienVelocity();
                    break;
                }
            }
        }

        // Check ship collision with bullet.
        if (gunBullet != null && ship != null && ship.detectCollision(gunBullet)) {
            renderables.remove(ship);
            renderables.remove(gunBullet);
            points += ship.getPoints();
            gunBullet = null;
            ship = null;
            view.setPoints(points);
            setNextShipTime();
        }

        // Alien bullet collision with gun.
        Iterator<Bullet> i = alienBullets.listIterator();
        while (i.hasNext()) {
            Bullet alienBullet = i.next();
            if (gun.detectCollision(alienBullet)) {
                renderables.remove(alienBullet);
                i.remove();
                life -= 1;
                view.setLife(life);
            }
        }

        if (life == 0) {
            view.setOverlay(GameView.OVERLAY_GAME_OVER);
            pause = true;
        }
    }

    /**
     * Calculates and sets the next spawn time of a spaceship.
     */
    private void setNextShipTime() {
        this.nextShipTimeMs = System.currentTimeMillis() + rng.nextLong(10000, 20001);
    }

    /**
     * Calculates and sets the next spawn time of a spaceship.
     */
    private void setNextAlienBulletTime() {
        this.nextAlienBulletTimeMs = System.currentTimeMillis() + rng.nextLong(1000, 5001);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (pause && e.getKeyCode() != KeyEvent.VK_F11) {
            pause = false;
            view.setOverlay(GamePanel.OVERLAY_NONE);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            spacePressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            pause = true;
            view.setOverlay(GamePanel.OVERLAY_PAUSE);
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
