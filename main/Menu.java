package main;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {
    private final int buttonNum = 2; //number of buttons in menu
    private final Color buttonColor = Color.GRAY;
    private final Color lineColor = Color.BLACK;
    private final String img_1 = "images/leftHalf.png";
    private final String img_2 = "";
    String[] list = new String[buttonNum];
    JLabel label;
    int x = GamePanel.getTileSize() * 4 + 1;
    int y = GamePanel.getTileSize() * 5 + 1;
    int w = GamePanel.getTileSize() * 12 - 2;
    int h = GamePanel.getTileSize() * 4 - 2;

    public Menu() {
        list[0] = "Life Game";
        list[1] = "Space Ship";
    }

    public void update() {
    }

    public void draw(Graphics2D g2, GamePanel gp) {
        //левая половина меню
        g2.drawImage(new ImageIcon(img_1).getImage(), 0, 0, null);

        //отрисовка кнопки
        g2.setColor(buttonColor);
        g2.fillRect(GamePanel.getTileSize() * 4 + 1, GamePanel.getTileSize() * 5 + 1,
                GamePanel.getTileSize() * 12 - 2, GamePanel.getTileSize() * 4 - 2);

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
        g2.setColor(buttonColor);
        g2.fillRect(GamePanel.getTileSize() * 4 + GamePanel.getScreenWidth() / 2 - 4, GamePanel.getTileSize() * 5 + 1,
                GamePanel.getTileSize() * 12 + 8, GamePanel.getTileSize() * 4 - 2);
        //отрисовка строки
        g2.setColor(lineColor);
        font = new Font("Arial", Font.PLAIN, 40);
        g2.setFont(font);
        length = (int) g2.getFontMetrics().getStringBounds(list[1], g2).getWidth();
        g2.drawString(list[1], GamePanel.getScreenWidth() / 4 * 3 - length / 2,
                GamePanel.getScreenHeight() / 4 + GamePanel.getTileSize() / 2 - 2);
    }

    public int getX(){ return x; }
    public int getY(){ return y; }
    public int getW(){ return w; }
    public int getH(){ return h; }
}
