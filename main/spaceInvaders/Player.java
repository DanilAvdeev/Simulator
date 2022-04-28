package main.spaceInvaders;

import main.GamePanel;

import javax.swing.*;
import java.awt.*;

public class Player {
    //начальные координаты и размеры игрока (корабля)
    private int x;
    private final int y = 400; //по идее не будет меняться
    private int height;
    private int width;
    //скорость перемещения
    private final int speed = 2;

    //перемещение влево, вправо
    public static boolean left;
    public static boolean right;

    //Image img = new ImageIcon("").getImage(); //текстурка корабля

    //конструктор сделать синглтоновым
    public Player() {
        x = GamePanel.getScreenWidth() / 2;

        height = GamePanel.getTileSize();
        width = GamePanel.getTileSize();

        left = false;
        right = false;
    }

    void update() {
        if (left && x >= speed) {
            x -= speed;
        }
        if (right && x <= GamePanel.getScreenWidth() - speed - width) {
            x += speed;
        }
    }

    public void draw(Graphics2D g) {
        //потом сам нарисую корабль 20х20
        //g.drawImage(img, x, y, null);
    }
}
