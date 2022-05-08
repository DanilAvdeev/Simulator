package main.spaceInvaders;

import java.awt.*;

public class Alien {
    private int x;
    private int y;
    private final int WIDTH = 26;
    private final int HEIGHT = 16;
    private boolean direction = false;
    private boolean alive;
    private final Bomb bomb;

    public Alien(int x_cord, int y_cord, Bomb bomb1) {
        alive = true;
        x = x_cord;
        y = y_cord;
        bomb = bomb1;
    }

    public void update() {
        final int SPEED = 1;
        if (direction) {
            x += SPEED;
        } else {
            x -= SPEED;
        }
    }

    public void draw(Graphics2D g2) {
        if (alive) {
            g2.setColor(Color.gray);
            g2.fillRect(x, y + 4, WIDTH, 7);
            g2.fillRect(x + 3, y + 1, 5, HEIGHT - 2);
            g2.fillRect(x + 18, y + 1, 5, HEIGHT - 2);
            g2.fillRect(x + 4, y + 14, 2, 3);
            g2.fillRect(x + 21, y + 14, 2, 3);
        }
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

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public Bomb getBomb() {
        return bomb;
    }

    public void setDead() {
        alive = false;
    }

    public void setDirection(boolean dir) {
        direction = dir;
    }

    public void setY(int diff) {
        y += diff;
    }
}
