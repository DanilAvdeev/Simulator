package main.lifeGame;

import main.GamePanel;

import javax.swing.*;
import java.awt.*;

public class Box extends JPanel {
    private final Cell cell;
    private final int x_cord, y_cord;

    public Box(int x, int y) {
        super();
        this.cell = new Cell();
        x_cord = x;
        y_cord = y;
    }

    void step_1(int around) {
        cell.step_1(around);
    }

    void step_2() {
        cell.step_2();
    }

    void draw(Graphics2D g2) {
        g2.setColor(cell.getColor());
        g2.fillRect(x_cord * GamePanel.getTileSize() + 1, y_cord * GamePanel.getTileSize() + 1,
                GamePanel.getTileSize() - 2, GamePanel.getTileSize() - 2);
    }

    public Cell getCell() {
        return cell;
    }
}
