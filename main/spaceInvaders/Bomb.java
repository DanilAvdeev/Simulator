package main.spaceInvaders;

import main.GamePanel;

import java.awt.*;

public class Bomb {
    int x;
    int y;
    final int speed = 2;
    boolean alive = false;

    public Bomb(int x_cord, int y_cord) {
        alive = true;
        x = x_cord;
        y = y_cord;
    }

    public void update() {
        y += speed;
        if (y >= GamePanel.getScreenHeight()) {
            alive = false;
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.fillRect(x, y, 2, 3);
    }
}
