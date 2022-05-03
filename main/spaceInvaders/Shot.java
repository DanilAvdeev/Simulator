package main.spaceInvaders;

import java.awt.*;

public class Shot {
    int x;
    int y;
    int w = 2;
    int h = 3;
    final int speed = 4;
    boolean alive = false;

    public Shot() {
    }

    public void initShot(int x_cord, int y_cord) {
        alive = true;
        x = x_cord + 11;
        y = y_cord;
    }

    public void update() {
        if (y <= 0) {
            alive = false;
        } else {
            y -= speed;
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.fillRect(x, y, w, h);
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
        return w;
    }

    public int getH() {
        return h;
    }
}
