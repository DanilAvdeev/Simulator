package main.spaceInvaders;

import main.GamePanel;

import java.awt.*;

public class Bomb {
    int startX;
    int startY;
    final int speed = 2;
    boolean alive = false;

    public Bomb(int x, int y) {
        alive = true;
        startX = x;
        startY = y;
    }

    public void update() {
        startY += speed;
        if (startY >= GamePanel.getScreenHeight()) {
            alive = false;
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.fillRect(startX, startY, 1, 1);
    }
}
