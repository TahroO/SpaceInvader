package game;

import game.objects.Alien;
import game.objects.Bullet;
import game.objects.Gun;
import game.objects.Spaceship;
import game.view.GameView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class GameController implements KeyListener, ActionListener {
    public static final int ROWS = 5;
    public static final int COLS = 11;

    private final int DELAY = 16;
    private Timer timer;
    private final Random rng = new Random();
    private final GameView view;

    // Game objects.
    private final ArrayList<Renderable> renderables;
    private final ArrayList<Alien> aliens = new ArrayList<>();
    private final Gun gun;
    //private final GameHud hud;
    private Bullet bullet;
    private Spaceship ship;

    // Game state.
    private boolean pause = true;
    private int round = 1;
    private int points;
    private double alienVx = 0.03375;
    private double alienSPS = 3;
    private long nextShipTimeMs;
    private long lastFrameTimeMs;
    private boolean spacePressed;

    /**
     * Creates a new GameController instance.
     */
    public GameController() {
        renderables = new ArrayList<>();
        view = new GameView(renderables);
        gun = new Gun();
        //hud = new GameHud();
        renderables.add(gun);
        //renderables.add(hud);
        createAliens(alienVx, alienSPS);
        //setNextShipTime();
        //lastFrameTimeMs = System.currentTimeMillis();
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
        //hud.setRound(round);
        view.setOverlay(GameView.OVERLAY_START);
    }

    /**
     * Gets the current game view.
     * @return A game view object.
     */
    public GameView getView() {
        return view;
    }

    // TODO move to Alien class static method.
    private void createAliens(double vx, double stepsPerSecond) {
        double margin = 0.136046512;
        double marginTop = 0.261627907;
        double spacing = 0.013953488;
        double rowHeight = 0.077906977;
        double stride = Alien.ALIEN_WIDTH + spacing;
        for (int row = 0; row < ROWS; row++) {
            double y = marginTop + row * rowHeight;
            for (int col = 0; col < COLS; col++) {
                double x = margin + col * stride;
                Alien alien = new Alien(x, y,vx, stepsPerSecond);
                aliens.add(alien);
                renderables.add(alien);
            }
        }
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
     * Updates all game objects.
     */
    private void update() {
        long currentTimeMs = System.currentTimeMillis();
        int lastFrameDelta = (int) (currentTimeMs - lastFrameTimeMs);
        if (!pause) {
            updateBullet();
            // TODO update in reverse order?
            renderables.forEach(renderable -> renderable.update(lastFrameDelta));
            //updateSpaceShip(currentTimeMs);
            //detectCollisions();
            // Check next level.
            if (aliens.isEmpty()) {
                //nextRound();
            }
        }
        //hud.update(lastFrameDelta);
        lastFrameTimeMs = currentTimeMs;
    }

    private void updateBullet() {
        // Update bullet.
        if (bullet == null && spacePressed) {
            bullet = new Bullet(gun.getX() + (Gun.GUN_WIDTH - Bullet.BULLET_WITH) / 2, gun.getY() - Bullet.BULLET_HEIGHT);
            renderables.add(bullet);
        }
        if (bullet != null && bullet.getY() < 0) {
            renderables.remove(bullet);
            bullet = null;
        }
    }

    /*
    private void updateSpaceShip(long currentTimeMs) {
        // Start or update ship.
        if (ship != null) {
            int shipX = ship.getX();
            int shipDirection = ship.getDirection();
            // Ship hat fenster verlassen?
            if (shipX > GameView.WIDTH && shipDirection == 1
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


    private void nextRound() {
        round += 1;
        //hud.setRound(round);
        createAliens(alienVx += 15, alienSPS *= 1.25);
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

    */

    /**
     * Calculates and sets the next spawn time of a spaceship.
     */
    private void setNextShipTime() {
        this.nextShipTimeMs = System.currentTimeMillis() + rng.nextLong(10000, 20001);
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (pause) {
            pause = false;
            view.setOverlay(GameView.OVERLAY_NONE);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            spacePressed = true;
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            pause = true;
            view.setOverlay(GameView.OVERLAY_PAUSE);
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
