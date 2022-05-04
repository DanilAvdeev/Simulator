package main.spaceInvaders;

import main.GamePanel;

import java.awt.*;

public class Bomb {
    int x;
    int y;
    int w = 2;
    int h = 3;
    final int speed = 2;
    boolean alive = false;

    public Bomb() {}

    public void shooter(int x_cord, int y_cord){
        alive = true;
        x = x_cord;
        y = y_cord;
    }

    public void update() {
        if (y+h >= GamePanel.getScreenHeight()) {
            alive = false;
        } else {
            y += speed;
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillRect(x, y, 2, 3);
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
