package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
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

    public static WindowState windowState = WindowState.MENU;
    //"map" for LifeGame
    Box[][] boxes;
    Player player;
    Menu menu = new Menu();
    JLabel label;
    MouseInput mouseInput = new MouseInput();
    KeyInput keyInput = new KeyInput();  //not in use rn
    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyInput);
        this.addMouseListener(mouseInput);
        this.setFocusable(true);
        this.addMouseMotionListener(mouseInput);
        initBoxes();
    }

    public void startGameThread() {
        gameThread = new Thread(this); //passing gamePanel
        gameThread.start(); //calls run()
    }

    @Override
    public void run() {
        double drawInterval; //0.0166 sec
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            if(windowState == WindowState.MENU){
                drawInterval = 1000000000.0 / fps;
            } else if(windowState == WindowState.GAME_1){
                drawInterval = 10000000000.0 / fps;
            } else {
                drawInterval = 100000000000.0 / fps;
            }
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

    public void actionPerformed(ActionEvent event) {
        if (windowState.equals(WindowState.MENU)) { //если мы на вкладке меню

        } else if (windowState.equals(WindowState.GAME_1)) {                                  //если мы на вкладке игры

        } else {

        }
    }

    public void update() {
        if (windowState.equals(WindowState.MENU)) { //если мы на вкладке меню
            if (mouseInput.mouseX > menu.getX() && mouseInput.mouseX < menu.getX() + menu.getW() &&
                    mouseInput.mouseY > menu.getY() && mouseInput.mouseY < menu.getY() + menu.getH() && mouseInput.clicked) {
                windowState = WindowState.GAME_1;
                mouseInput.clicked = false;
            }

        } else if (windowState.equals(WindowState.GAME_1)) {
            int around;
            for (int x = 0; x < maxScreenCol; x++) {
                for (int y = 0; y < maxScreenRow; y++) {
                    around = cellsAround(boxes, x, y);
                    boxes[x][y].step_1(around);
                }
            }
            if (keyInput.escPressed) {
                windowState = WindowState.MENU;
                keyInput.escPressed = false;
            }
        } else {
            System.out.print("how did you get here??");
        }
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        if (windowState == WindowState.MENU) {
            super.paintComponent(graphics);
            menu.draw(graphics2D, this);
            graphics2D.dispose();
        } else if (windowState == WindowState.GAME_1) {
            for (int x = 0; x < maxScreenCol; x++) {
                for (int y = 0; y < maxScreenRow; y++) {
                    boxes[x][y].draw(graphics2D);
                }
            }
            graphics2D.dispose();
        } else {

            graphics2D.dispose();
        }
    }

    private void initBoxes() {
        Random random;
        boxes = new Box[maxScreenCol][maxScreenRow];
        for (int x = 0; x < maxScreenCol; x++) {
            for (int y = 0; y < maxScreenRow; y++) {
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
                    if (boxes[(x + i + maxScreenCol) % maxScreenCol][(y + j + maxScreenRow) % maxScreenRow].cell.isLive()) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static int getTileSize() {
        return tileSize;
    }

    public static int getScreenWidth() {
        return screenWidth;
    }

    public static int getScreenHeight() {
        return screenHeight;
    }
}
