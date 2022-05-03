package main;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {
    private final int buttonNum = 2; //number of buttons in menu
    private final Color buttonColor = Color.GRAY;
    private final Color lineColor = Color.BLACK;
    final String img_1 = "images/leftHalf.png";
    //private final String img_2 = "";
    String[] list = new String[buttonNum];

    int x_1 = GamePanel.getTileSize() * 4 + 1;
    int y_1 = GamePanel.getTileSize() * 5 + 1;
    int w_1 = GamePanel.getTileSize() * 12 - 2;
    int h_1 = GamePanel.getTileSize() * 4 - 2;

    int x_2 = GamePanel.getTileSize() * 4 + GamePanel.getScreenWidth() / 2 - 4;
    int y_2 = GamePanel.getTileSize() * 5 + 1;
    int w_2 = GamePanel.getTileSize() * 12 + 8;
    int h_2 = GamePanel.getTileSize() * 4 - 2;

    public Menu() {
        list[0] = "Life Game";
        list[1] = "Space Ship";
    }

    public void draw(Graphics2D g2) {
        //левая половина меню
        g2.drawImage(new ImageIcon(img_1).getImage(), 0, 0, null);

        //отрисовка кнопки
        g2.setColor(buttonColor);
        g2.fillRect(x_1, y_1, w_1, h_1);

        //отрисовка строки
        g2.setColor(lineColor);
        Font font = new Font("Arial", Font.PLAIN, 40);
        g2.setFont(font);
        int length = (int) g2.getFontMetrics().getStringBounds(list[0], g2).getWidth();
        g2.drawString(list[0], GamePanel.getScreenWidth() / 4 - length / 2,
                GamePanel.getScreenHeight() / 4 + GamePanel.getTileSize() / 2 - 2);

        //правая половина
        //g2.drawImage(new ImageIcon(img_2).getImage(), GamePanel.getScreenWidth()/2, 0, null);
        //отрисовка кнопки
        g2.setColor(Color.WHITE);
        g2.fillRect(GamePanel.getScreenWidth() / 2, 0, GamePanel.getScreenWidth() / 2, GamePanel.getScreenHeight());
        g2.setColor(buttonColor);
        g2.fillRect(x_2, y_2, w_2, h_2);
        //отрисовка строки
        g2.setColor(lineColor);
        font = new Font("Arial", Font.PLAIN, 40);
        g2.setFont(font);
        length = (int) g2.getFontMetrics().getStringBounds(list[1], g2).getWidth();
        g2.drawString(list[1], GamePanel.getScreenWidth() / 4 * 3 - length / 2,
                GamePanel.getScreenHeight() / 4 + GamePanel.getTileSize() / 2 - 2);
    }

    public int getX_1() {
        return x_1;
    }

    public int getY_1() {
        return y_1;
    }

    public int getW_1() {
        return w_1;
    }

    public int getH_1() {
        return h_1;
    }

    public int getX_2() {
        return x_2;
    }

    public int getY_2() {
        return y_2;
    }

    public int getW_2() {
        return w_2;
    }

    public int getH_2() {
        return h_2;
    }
}
