package game.model;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class Game {
    protected boolean pause = true;
    protected int points;
    protected int round = 1;
    protected int lives = 3;
    protected List<Model> objects = new ArrayList<>();

    protected Gun gun = new Gun();

    protected boolean shooting;
    protected int gunDirection;

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }

    public void setGunDirection(int gunDirection) {
        this.gunDirection = gunDirection;
    }

    public void update() {

    }

    protected void detectCollisions() {

    }
}
