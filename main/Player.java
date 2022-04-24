package main;

import javax.swing.*;
import java.awt.*;

public class Player {
    //начальные координаты и размеры игрока (корабля)
    private int x;
    private int y; //по идее не будет меняться
    private int height;
    private int weight;
    //скорость перемещения
    private int speed;

    //перемещение влево, вправо
    public static boolean left;
    public static boolean right;

    //Image img = new ImageIcon("").getImage(); //текстурка корабля

    //конструктор сделать синглтоновым
    public Player() {
        x = 0;
        y = 0;

        height = GamePanel.getTileSize();
        weight = GamePanel.getTileSize();

        speed = 2;

        left = false;
        right = false;
    }

    void update(){
        if (left && x >= speed) {
            x -= speed;
        }
        if (right && x <= GamePanel.screenWidth - speed - weight) {
            x += speed;
        }
    }

    public void draw(Graphics2D g) {
        //потом сам нарисую корабль 20х20
        //g.drawImage(img, x, y, null);
    }
}
