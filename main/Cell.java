package main;

import java.awt.*;

public class Cell {
    public CellState cellState;

    public Cell() {
        cellState = CellState.DEAD;
    }

    public boolean isLive() {
        return cellState == CellState.ALIVE;
    }

    public void step1(int around) {
        cellState = cellState.step_1(around);
    }

    public void turn() { this.cellState = CellState.ALIVE; }

    public Color getColor() {
        if (this.cellState == CellState.DEAD) {
            return Color.WHITE;
        }
        return Color.BLUE;
    }
}
