package main;

import main.lifeGame.CellState;
import main.spaceInvaders.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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
    Menu menu = new Menu();
    MouseInput mouseInput = new MouseInput();
    KeyInput keyInput = new KeyInput();  //not in use rn
    Thread gameThread;

    //Bomb bomb = new Bomb(400, 400);
    Player player = new Player();
    Alien alien = new Alien(250, 50);
    Shot shot = new Shot();
    Field field = new Field();

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyInput);
        this.addMouseListener(mouseInput);
        this.setFocusable(true);
        this.addMouseMotionListener(mouseInput);
        initBoxes();
        initAliens();
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
                //System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        if (windowState.equals(WindowState.MENU)) { //если мы на вкладке меню
            if (mouseInput.mouseX > menu.getX_1() && mouseInput.mouseX < menu.getX_1() + menu.getW_1() &&
                    mouseInput.mouseY > menu.getY_1() && mouseInput.mouseY < menu.getY_1() + menu.getH_1() && mouseInput.clicked) {
                windowState = WindowState.GAME_1;
                mouseInput.clicked = false;
            }
            if (mouseInput.mouseX > menu.getX_2() && mouseInput.mouseX < menu.getX_2() + menu.getW_2() &&
                    mouseInput.mouseY > menu.getY_2() && mouseInput.mouseY < menu.getY_2() + menu.getH_2() && mouseInput.clicked) {
                windowState = WindowState.GAME_2;
                mouseInput.clicked = false;
            }
        } else if (windowState.equals(WindowState.GAME_1)) {
            if (keyInput.escPressed) {
                windowState = WindowState.MENU;
                keyInput.escPressed = false;
            } else {
                int around;
                for (int x = 0; x < maxScreenCol; x++) {
                    for (int y = 0; y < maxScreenRow; y++) {
                        around = cellsAround(boxes, x, y);
                        boxes[x][y].step_1(around);
                    }
                }
            }
        } else if (windowState.equals(WindowState.GAME_2)) {
            player.update(keyInput.left, keyInput.right);
            if (keyInput.shooting && !shot.isAlive()) {
                shot.initShot(player.getX(), player.getY());
            }
            if (shot.isAlive()) {
                shot.update();
            }
            for(Alien i: aliens) {
                if (i.isAlive()) {
                    i.update();
                    if (doDestroy(i, shot)) {
                        i.setDead();
                        shot.setDead();
                    }
                }
            }
            if (keyInput.escPressed) {
                windowState = WindowState.MENU;
                keyInput.escPressed = false;
            }
        }
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        if (windowState.equals(WindowState.MENU)) {
            super.paintComponent(graphics);
            menu.draw(graphics2D);
            graphics2D.dispose();
        } else if (windowState.equals(WindowState.GAME_1)) {
            for (int x = 0; x < maxScreenCol; x++) {
                for (int y = 0; y < maxScreenRow; y++) {
                    boxes[x][y].draw(graphics2D);
                }
            }
            graphics2D.dispose();
        } else if (windowState.equals(WindowState.GAME_2)) {
            field.draw(graphics2D);
            player.draw(graphics2D);
            if (shot.isAlive()) {
                shot.draw(graphics2D);
            }
            for(Alien i: aliens){
                i.draw(graphics2D);
            }
            //alien.draw(graphics2D);
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

    ArrayList<Alien> aliens = new ArrayList<>();

    private void initAliens() {
//        int num = 7;
//        final int maxInRow = 4;
//        int x_offset = 0;
//        int x_start = x_offset/2;
//        int y_start = GamePanel.getScreenHeight()/7;
//        for (int i = 0; i < num; i++) {
//            Alien alien = new Alien(x_start + x_offset, y_start);
//            x_offset += GamePanel.getScreenWidth()/maxInRow;
//            if (i == maxInRow-1) {
//                y_start += 28;
//                x_offset = 0;
//                x_start = GamePanel.getScreenWidth()/(num-maxInRow)/2;
//            }
//            aliens.add(alien);
//        }

        int num = 12;
        int x_start = 80;
        int x_offset = 0;
        int y_start = GamePanel.getScreenHeight()/10;

        for(int i = 0; i < num; i ++) {
            Alien alien = new Alien(x_start + x_offset, y_start);
            x_offset += 160;
            if (i == 3 || i == 7) {
                x_offset = 0;
                y_start += 28;
            }
            aliens.add(alien);
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

    private boolean doDestroy(Alien alien, Shot shot) {
        int x_a = alien.getX();
        int y_a = alien.getY();
        int w_a = alien.getW();
        int h_a = alien.getH();

        int x_s = shot.getX();
        int y_s = shot.getY();
        int w_s = shot.getW();
        int h_s = shot.getH();

        if(!shot.isAlive()){
            return false;
        }
        if (x_a + h_a < x_s) {
            return false;
        } else if (x_a > x_s + h_s) {
            return false;
        } else if (y_a > y_s + w_a) {
            return false;
        } else if (y_a + w_a < y_s) {
            return false;
        } else if (x_s >= x_a && x_s <= x_a + h_a) {
            if(y_a <= y_s + w_s && y_a + w_a >= y_s){
                return true;
            }
        }
        return false;
    }

}
