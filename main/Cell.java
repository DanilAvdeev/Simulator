package main;

import java.awt.*;
import java.util.ArrayList;

public class Cell {
    //public int x, y; //нужно ли? //I. Если будем считать клетки расположенные рядом через фк-цию
    public State state;
    ArrayList<Cell> nearCells;   //II. Если будем хранить клетки находящиеся рядом в листе

    public Cell() {
        state = State.DEAD;
        nearCells = new ArrayList<>();
    }

    public boolean isLive() {
        return state == State.ALIVE;
    }
    public void step1(int around) {
        //int around = countNearCells();
        state = state.step_1(around);
    }
//    public void step2() { state = state.step_2(); }

//    public void addNear(Cell cell) {
//        nearCells.add(cell);
//    }

//    int countNearCells() {
//        int count = 0;
//        for(Cell cell: nearCells) {
//            if (cell.state.isLive()){
//                count++;
//            }
//        }
//        return count;
//    }

    public void turn() { this.state = State.ALIVE; }

    public Color getColor() {
        if (this.state == State.DEAD) {
            return Color.BLACK;
        }
        return Color.BLUE; //if ALIVE
    }
}
