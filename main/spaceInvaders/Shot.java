package main.spaceInvaders;

import java.awt.*;

public class Shot {
    private int x;
    private int y;
    private final int WIDTH = 2;
    private final int HEIGHT = 3;
    private boolean alive = false;

    public Shot() {
    }

    public void initShot(int x_cord, int y_cord) {
        alive = true;
        x = x_cord + 11;
        y = y_cord;
    }

    public void update() {
        final int SPEED = 4;
        if(alive) {
            if (y <= 0) {
                alive = false;
            } else {
                y -= SPEED;
            }
        }
    }

    public void draw(Graphics2D g2) {
        if (alive) {
            g2.setColor(Color.RED);
            g2.fillRect(x, y, WIDTH, HEIGHT);
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public void setDead() {
        alive = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return WIDTH;
    }

    public int getH() {
        return HEIGHT;
    }
}
