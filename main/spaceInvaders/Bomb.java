package main.spaceInvaders;

import main.GamePanel;

import java.awt.*;

public class Bomb {
    private int x;
    private int y;
    private static final int WIDTH = 2;
    private static final int HEIGHT = 3;
    private boolean alive = false;

    public Bomb() {}

    public void shooter(int x_cord, int y_cord){
        alive = true;
        x = x_cord;
        y = y_cord;
    }

    public void update() {
        final int speed = 2;
        if (y+HEIGHT >= GamePanel.getScreenHeight()) {
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
