package main;

import main.lifeGame.Cell;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.*;

public class Box extends JPanel {
    Cell cell;
    int x_cord, y_cord;

    public Box(int x, int y) {
        super();
        this.cell = new Cell();
        x_cord = x;
        y_cord = y;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cell.turn();
            }
        });
    }

    void step_1(int around) {
        cell.step1(around);
    }

    void draw(Graphics2D g2) {
        g2.setColor(cell.getColor());
        g2.fillRect(x_cord * GamePanel.getTileSize() + 1, y_cord * GamePanel.getTileSize() + 1,
                GamePanel.getTileSize() - 2, GamePanel.getTileSize() - 2);
    }
}
