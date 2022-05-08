package main.spaceInvaders;

import main.GamePanel;

import java.awt.*;

public class Player {
    //начальные координаты и размеры игрока (корабля)
    private int x;
    private final int y = 400; //по идее не будет меняться
    final int height;
    final int width;
    //скорость перемещения
    final int speed = 2;

    public Player() {
        x = GamePanel.getScreenWidth() / 2;
        height = GamePanel.getTileSize();
        width = GamePanel.getTileSize();
    }

    //поправить координаты слева и справа
    public void update(boolean left, boolean right) {
        if (left && x >= speed) {
            x -= speed;
        }
        if (right && x <= GamePanel.getScreenWidth() - speed - width) {
            x += speed;
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.GRAY);
        g2.fillRect(x + 5, y + 5, 14, 9);
        g2.fillRect(x + 9, y, 6, 6);
        g2.fillRect(x + 2, y + 14, 20, 3);//g.drawImage(img, x, y, null);
        g2.fillRect(x, y + 17, 24, 3);
        g2.fillRect(x + 5, y + 20, 3, 3);
        g2.fillRect(x + 16, y + 20, 3, 3);
        g2.setColor(Color.BLACK);
        g2.fillRect(x + 9, y + 8, 6, 2);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return width;
    }

    public int getH() {
        return height;
    }
}
