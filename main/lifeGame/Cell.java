package main.lifeGame;

import java.awt.*;

public class Cell {
    private CellState cellState;

    public Cell() {
        cellState = CellState.NONE;
    }

    public boolean isLive() {
        return cellState == CellState.ALIVE;
    }

    public void step_1(int around) {
        cellState = cellState.step_1(around);
    }

    public void step_2() {
        cellState = cellState.step_2();
    }

    public void setCellState(CellState newCellState) {
        cellState = newCellState;
    }

    public Color getColor() {
        if (this.cellState == CellState.DEAD || this.cellState == CellState.NONE) {
            return Color.WHITE;
        }
        if (this.cellState == CellState.BORN) {
            return Color.RED;
        }

        return Color.BLUE;
    }
}
