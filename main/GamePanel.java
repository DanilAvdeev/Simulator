package main;

import main.lifeGame.GameControllerLG;
import main.spaceInvaders.GameControllerSI;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //Singleton
    private static GamePanel instance;
    //FRAME SETTINGS
    private static final int SCALE = 1; //later change it so we can choose it from different
    private static final int ORIGIN_TILE_SIZE = 16;
    private static final int TILE_SIZE = SCALE * ORIGIN_TILE_SIZE;
    private static final int MAX_SCREEN_COL = 40;
    private static final int MAX_SCREEN_ROW = 30; //window is 4x3
    private static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;
    private static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;
    //FPS
    private static final int FPS = 60;
    //
    private static WindowState windowState = WindowState.MENU;
    //
    private final GameControllerLG gameControllerLG = new GameControllerLG();
    private GameControllerSI gameControllerSI =  new GameControllerSI();
    private static boolean gameStart = true;
    //
    private final Menu menu = new Menu();
    private final MouseInput mouseInput = new MouseInput();
    private final KeyInput keyInput = new KeyInput();
    private Thread gameThread;




    private GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyInput);
        this.addMouseListener(mouseInput);
        this.setFocusable(true);
        this.addMouseMotionListener(mouseInput);
    }

    public static GamePanel getInstance(){
        if (instance == null) {
            instance = new GamePanel();
        }
        return instance;
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
                drawInterval = 1000000000.0 / FPS;
            } else if (windowState == WindowState.GAME_1) {
                drawInterval = 10000000000.0 / FPS;
            } else {
                drawInterval = 1000000000.0 / FPS;
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

    private void update() {
        if (windowState.equals(WindowState.MENU)) {
            windowState = menu.update(mouseInput);
        } else if (windowState.equals(WindowState.GAME_1)) {
            gameControllerLG.update(keyInput);
        } else if (windowState.equals(WindowState.GAME_2)) {
            if (gameControllerSI.getGameOver()) {
                gameStart = false;
            }
            if (gameStart) {
                gameControllerSI = new GameControllerSI();
                gameStart = false;
            }
            gameControllerSI.update(keyInput);
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
            gameControllerSI.draw(graphics2D);
        }
    }

    public static int getTileSize() {
        return TILE_SIZE;
    }

    public static int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    public static int getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    public static int getMaxScreenCol() {
        return MAX_SCREEN_COL;
    }

    public static int getMaxScreenRow() {
        return MAX_SCREEN_ROW;
    }

    public static void setGameStatus(boolean setToStart) {
        gameStart = setToStart;
    }

    public static void setWindowState(WindowState newState) {
        windowState = newState;
    }
}