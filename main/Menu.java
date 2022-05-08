package main;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {
    private final int buttonNum = 2; //number of buttons in menu
    private final Color buttonColor = Color.GRAY;
    private final Color lineColor = Color.BLACK;
    private static final String img_1 = "images/leftHalf.png";
    private static final String img_2 = "images/rightHalf.png";
    String[] list = new String[buttonNum];
    //coordinates and size of button № 1
    int x_1 = GamePanel.getTileSize() * 4 + 1;
    int y_1 = GamePanel.getTileSize() * 5 + 1;
    int w_1 = GamePanel.getTileSize() * 12 - 2;
    int h_1 = GamePanel.getTileSize() * 4 - 2;
    //coordinates and size of button № 2
    int x_2 = GamePanel.getTileSize() * 4 + GamePanel.getScreenWidth() / 2 - 4;
    int y_2 = GamePanel.getTileSize() * 5 + 1;
    int w_2 = GamePanel.getTileSize() * 12 + 8;
    int h_2 = GamePanel.getTileSize() * 4 - 2;

    public Menu() {
        list[0] = "Life Game";
        list[1] = "Space Ship";
    }

    public WindowState update(MouseInput mouseInput){
        if (mouseInput.mouseX > x_1 && mouseInput.mouseX < x_1 + w_1 &&
                mouseInput.mouseY > y_1 && mouseInput.mouseY < y_1 + h_1 && mouseInput.clicked) {
            mouseInput.clicked = false;
            return WindowState.GAME_1;
        }
        if (mouseInput.mouseX > x_2 && mouseInput.mouseX < x_2 + w_2 &&
                mouseInput.mouseY > y_2 && mouseInput.mouseY < y_2 + h_2 && mouseInput.clicked) {
            mouseInput.clicked = false;
            return WindowState.GAME_2;
        }
        return WindowState.MENU;
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
        g2.drawImage(new ImageIcon(img_2).getImage(), 320, 0, null);
        //отрисовка кнопки
        g2.setColor(buttonColor);
        g2.fillRect(x_2, y_2, w_2, h_2);
        //отрисовка строки
        g2.setColor(lineColor);
        font = new Font("Arial", Font.PLAIN, 40);
        g2.setFont(font);
        length = (int) g2.getFontMetrics().getStringBounds(list[1], g2).getWidth();
        g2.drawString(list[1], GamePanel.getScreenWidth() / 4 * 3 - length / 2,
                GamePanel.getScreenHeight() / 4 + GamePanel.getTileSize() / 2 - 2);

        g2.dispose();
    }
}
