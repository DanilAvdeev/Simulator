package main;

import main.lifeGame.*;
import main.spaceInvaders.GameControllerSI;

import javax.swing.*;
import java.awt.*;

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

    Menu menu = new Menu();
    MouseInput mouseInput = new MouseInput();
    KeyInput keyInput = new KeyInput();
    Thread gameThread;

    GameControllerSI gameController = new GameControllerSI();
    static boolean gameStart = true;

    GameControllerLG gameControllerLG = new GameControllerLG();

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyInput);
        this.addMouseListener(mouseInput);
        this.setFocusable(true);
        this.addMouseMotionListener(mouseInput);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
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
            if (windowState == WindowState.MENU) {
                drawInterval = 1000000000.0 / fps;
            } else if (windowState == WindowState.GAME_1) {
                drawInterval = 10000000000.0 / fps;
            } else {
                drawInterval = 1000000000.0 / fps;
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

    public void update() {
        if (windowState.equals(WindowState.MENU)) {
            windowState = menu.update(mouseInput);
        } else if (windowState.equals(WindowState.GAME_1)) {
            windowState = gameControllerLG.update(keyInput);
        } else if (windowState.equals(WindowState.GAME_2)) {
            if (gameController.getGameOver()) {
                gameStart = false;
            }
            if (gameStart) {
                gameController = new GameControllerSI();
                gameStart = false;
            }
            windowState = gameController.update(keyInput);
        }
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        if (windowState.equals(WindowState.MENU)) {
            super.paintComponent(graphics);
            menu.draw(graphics2D);
        } else if (windowState.equals(WindowState.GAME_1)) {
            gameControllerLG.draw(graphics2D);
        } else if (windowState.equals(WindowState.GAME_2)) {
            gameController.draw(graphics2D);
        }
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

    public static void setGameStatus(boolean setToStart) {
        gameStart = setToStart;
    }

    public static int getMaxScreenCol() {
        return maxScreenCol;
    }

    public static int getMaxScreenRow() {
        return maxScreenRow;
    }
}
