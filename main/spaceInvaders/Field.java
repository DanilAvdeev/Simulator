package main.spaceInvaders;

import main.GamePanel;

import java.awt.*;

public class Field {
    public void draw(Graphics2D g2){
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, GamePanel.getScreenWidth(), GamePanel.getScreenHeight());
    }
}
