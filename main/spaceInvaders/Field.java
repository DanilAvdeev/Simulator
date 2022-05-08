package main.spaceInvaders;

import main.GamePanel;

import java.awt.*;

public class Field {
    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, GamePanel.getScreenWidth(), GamePanel.getScreenHeight());
        g2.setColor(Color.WHITE);
        //1st star
        g2.fillRect(20, 20, 6, 2);
        g2.fillRect(22, 18, 2, 6);
        //2nd star
        g2.fillRect(45, 30, 6, 2);
        g2.fillRect(47, 28, 2, 6);
        //3rd star
        g2.fillRect(128, 25, 6, 2);
        g2.fillRect(130, 23, 2, 6);
        //4th star
        g2.fillRect(256, 18, 6, 2);
        g2.fillRect(258, 16, 2, 6);
        //5th star
        g2.fillRect(370, 23, 6, 2);
        g2.fillRect(372, 21, 2, 6);
        //6th star
        g2.fillRect(460, 37, 6, 2);
        g2.fillRect(462, 35, 2, 6);
        //7th star
        g2.fillRect(530, 15, 6, 2);
        g2.fillRect(532, 13, 2, 6);
        //8th star
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(600, 24, 3, 1);
        g2.fillRect(601, 23, 1, 3);
        //9th star
        g2.fillRect(500, 25, 3, 1);
        g2.fillRect(501, 24, 1, 3);
        //10th star
        g2.fillRect(330, 40, 3, 1);
        g2.fillRect(331, 39, 1, 3);
        //11th star
        g2.fillRect(180, 20, 3, 1);
        g2.fillRect(181, 19, 1, 3);
        //12 star
        g2.fillRect(63, 15, 3, 1);
        g2.fillRect(64, 14, 1, 3);
    }
}
