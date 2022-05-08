package main.lifeGame;

import java.awt.*;

public class Cell {
    private CellState cellState;

    public Cell() {
        cellState = CellState.DEAD;
    }

    public boolean isLive() {
        return cellState == CellState.ALIVE;
    }

    public void update(int around) {
        cellState = cellState.update(around);
    }

    public void turn() { this.cellState = CellState.ALIVE; }

    public void setCellState(CellState newCellState){
        cellState = newCellState;
    }

    public Color getColor() {
        if (this.cellState == CellState.DEAD) {
            return Color.WHITE;
        }
        return Color.BLUE;
    }
}
