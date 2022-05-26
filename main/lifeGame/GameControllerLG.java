package main.lifeGame;

import main.GamePanel;
import main.KeyInput;
import main.WindowState;
import java.awt.*;
import java.util.Random;

public class GameControllerLG {
    private Box[][] boxes;

    public GameControllerLG(){
        initBoxes();
    }

    public void update(KeyInput keyInput){
        if (keyInput.isEscPressed()) {
            keyInput.setEscPressed(false);
            GamePanel.setWindowState(WindowState.MENU);
        } else {
            int around;
            for (int x = 0; x < GamePanel.getMaxScreenCol(); x++) {
                for (int y = 0; y < GamePanel.getMaxScreenRow(); y++) {
                    around = cellsAround(boxes, x, y);
                    boxes[x][y].step_1(around);
                }
            }
            for (int x = 0; x < GamePanel.getMaxScreenCol(); x++) {
                for (int y = 0; y < GamePanel.getMaxScreenRow(); y++) {
                    boxes[x][y].step_2();
                }
            }
        }
    }

    public void draw(Graphics2D g2){
        for (int x = 0; x < GamePanel.getMaxScreenCol(); x++) {
            for (int y = 0; y < GamePanel.getMaxScreenRow(); y++) {
                boxes[x][y].draw(g2);
            }
        }
        g2.dispose();
    }

    private void initBoxes() {
        int a = 0;
        int b = 100;
        boxes = new Box[GamePanel.getMaxScreenCol()][GamePanel.getMaxScreenRow()];
        for (int x = 0; x < GamePanel.getMaxScreenCol(); x++) {
            for (int y = 0; y < GamePanel.getMaxScreenRow(); y++) {
                boxes[x][y] = new Box(x, y);
                int randomNumber = a + (int)(Math.random() * b);
                if ( randomNumber <= 40) {
                    boxes[x][y].getCell().setCellState(CellState.ALIVE);
                } else {
                    boxes[x][y].getCell().setCellState(CellState.NONE);
                }
            }
        }
        //Glider
//        boxes[0][1].getCell().setCellState(CellState.ALIVE);
//        boxes[1][2].getCell().setCellState(CellState.ALIVE);
//        boxes[2][0].getCell().setCellState(CellState.ALIVE);
//        boxes[2][1].getCell().setCellState(CellState.ALIVE);
//        boxes[2][2].getCell().setCellState(CellState.ALIVE);
//        //blinker (period 2)
//        boxes[20][6].getCell().setCellState(CellState.ALIVE);
//        boxes[20][7].getCell().setCellState(CellState.ALIVE);
//        boxes[20][8].getCell().setCellState(CellState.ALIVE);
//        //Beacon (period 2)
//        boxes[30][20].getCell().setCellState(CellState.ALIVE);
//        boxes[31][20].getCell().setCellState(CellState.ALIVE);
//        boxes[30][21].getCell().setCellState(CellState.ALIVE);
//        boxes[33][23].getCell().setCellState(CellState.ALIVE);
//        boxes[32][23].getCell().setCellState(CellState.ALIVE);
//        boxes[33][22].getCell().setCellState(CellState.ALIVE);
    }

    private int cellsAround(Box[][] boxes, int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!(i == 0 && j == 0)) {
                    if (boxes[(x + i + GamePanel.getMaxScreenCol()) % GamePanel.getMaxScreenCol()]
                            [(y + j + GamePanel.getMaxScreenRow()) % GamePanel.getMaxScreenRow()].getCell().isLiveOrDead()) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
