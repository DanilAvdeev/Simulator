package main.lifeGame;

import main.GamePanel;
import main.KeyInput;
import main.WindowState;

import java.awt.*;
import java.util.Random;

public class GameControllerLG {
    Box[][] boxes;

    public GameControllerLG(){
        initBoxes();
    }

    public WindowState update(KeyInput keyInput){
        if (keyInput.escPressed) {
            keyInput.escPressed = false;
            return WindowState.MENU;
        } else {
            int around;
            for (int x = 0; x < GamePanel.getMaxScreenCol(); x++) {
                for (int y = 0; y < GamePanel.getMaxScreenRow(); y++) {
                    around = cellsAround(boxes, x, y);
                    boxes[x][y].step_1(around);
                }
            }
        }
        return WindowState.GAME_1;
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
        Random random;
        boxes = new Box[GamePanel.getMaxScreenCol()][GamePanel.getMaxScreenRow()];
        for (int x = 0; x < GamePanel.getMaxScreenCol(); x++) {
            for (int y = 0; y < GamePanel.getMaxScreenRow(); y++) {
                boxes[x][y] = new Box(x, y);
                random = new Random();
                if (random.nextBoolean()) {
                    boxes[x][y].cell.cellState = CellState.ALIVE;
                } else {
                    boxes[x][y].cell.cellState = CellState.DEAD;
                }
            }
        }
    }

    int cellsAround(Box[][] boxes, int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!(i == 0 && j == 0)) {
                    if (boxes[(x + i + GamePanel.getMaxScreenCol()) % GamePanel.getMaxScreenCol()]
                            [(y + j + GamePanel.getMaxScreenRow()) % GamePanel.getMaxScreenRow()].cell.isLive()) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
