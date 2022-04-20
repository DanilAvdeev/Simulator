package main;

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
//        setBounds(x * GamePanel.getTileSize(), y * GamePanel.getTileSize(), GamePanel.getTileSize(), GamePanel.getTileSize());
//        setBackground(Color.YELLOW);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cell.turn();
            }
        });
    }

    void step_1(int around) {
        cell.step1(around);
//        setColor();
    }

    void draw(Graphics2D g2){
        g2.setColor(cell.getColor());
        g2.fillRect(x_cord*GamePanel.getTileSize(), y_cord*GamePanel.getTileSize(), GamePanel.getTileSize(), GamePanel.getTileSize());
    }
//    void step_2() {
//        cell.step2();
//        setColor();
//    }



//    void setColor(){ setBackground(Cell.getColor(cell.state)); }
}
