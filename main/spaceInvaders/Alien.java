package main.spaceInvaders;

import java.awt.*;

public class Alien {
    int x;
    int y;
    final int speed = 1;
    private Bomb bomb;

    public Alien(int x_cord, int y_cord) {
        x = x_cord;
        y = y_cord;
        shoot();
    }

    public void shoot() {
        bomb = new Bomb(x, y);
    }

    public void update() {
        y += speed;
        bomb.update();
        if (!bomb.alive) {
            shoot();
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.LIGHT_GRAY);
        //g2.fillReact(x, y, 2, 2);
    }
}
