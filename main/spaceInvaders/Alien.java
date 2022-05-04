package main.spaceInvaders;

import main.GamePanel;

import java.awt.*;

public class Alien {
    int x;
    int y;
    int w = 25;
    int h = 16;
    final int speed = 1;
    private boolean direction = false;
    private boolean alive;
    Bomb bomb;

    public Alien(int x_cord, int y_cord, Bomb bomb1) {
        alive = true;
        x = x_cord;
        y = y_cord;
        bomb = bomb1;
    }

    public void update() {
        if (alive) {
            boolean getLower = false;
            if (x <= 0) {
                direction = true;
                getLower = true;
            } else if (x + 23 >= GamePanel.getScreenWidth()) {
                direction = false;
                getLower = true;
            }
            if (direction) {
                x += speed;
            } else {
                x -= speed;
            }
            if (getLower) {
                y += 28;
            }
        }
    }

    public void draw(Graphics2D g2) {
        if (alive) {
            g2.setColor(Color.gray);
            g2.fillRect(x, y + 4, 26, 7);
            g2.fillRect(x + 3, y + 1, 5, 14);
            g2.fillRect(x + 18, y + 1, 5, 14);
            g2.fillRect(x + 4, y + 14, 2, 3);
            g2.fillRect(x + 21, y + 14, 2, 3);
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
        return w;
    }

    public int getH() {
        return h;
    }

    public Bomb getBomb() {
        return bomb;
    }
}
