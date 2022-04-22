package main;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {
    //FRAME
    static final int scale = 1; //later change it so we can choose it from different
    static final int originTileSize = 16;
    static final int tileSize = scale * originTileSize;
    static final int maxScreenCol = 40;
    static final int maxScreenRow = 30; //window is 4x3
    static final int screenWidth = tileSize * maxScreenCol;
    static final int screenHeight = tileSize * maxScreenRow;
    //FPS
    int fps = 60;

    Box[][] boxes;

    KeyInput keyInput = new KeyInput();
    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyInput);
        this.setFocusable(true);
        initBoxes();
    }

    public void startGameThread() {
        gameThread = new Thread(this); //passing gamePanel
        gameThread.start(); //calls run()
    }

    @Override
    public void run() {
        double drawInterval = 10000000000.0 / fps; //0.0166 sec
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            //1.UPDATE INFORMATION
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        int around;
        for (int x = 0; x < maxScreenCol; x++) {
            for (int y = 0; y < maxScreenRow; y++) {
                around = cellsAround(boxes, x, y);
                boxes[x][y].step_1(around);
            }
        }
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        for (int x = 0; x < maxScreenCol; x++) {
            for (int y = 0; y < maxScreenRow; y++) {
                boxes[x][y].draw(graphics2D);
            }
        }
        graphics2D.dispose();
    }

    private void initBoxes () {
        Random random;
        boxes = new Box[maxScreenCol][maxScreenRow];
        for (int x = 0; x < maxScreenCol; x++){
            for (int y = 0; y < maxScreenRow; y++){
                boxes[x][y] = new Box(x, y);
                random = new Random();
                if (random.nextBoolean()) {
                    boxes[x][y].cell.state = State.ALIVE;
                } else {
                    boxes[x][y].cell.state = State.DEAD;
                }
            }
        }
    }

    public static int getTileSize() {
        return tileSize;
    }

    int cellsAround(Box[][] boxes, int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!(i == 0 && j == 0)){
                    if (boxes[(x+i + maxScreenCol)%maxScreenCol][(y+j+ maxScreenRow)%maxScreenRow].cell.isLive()) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
