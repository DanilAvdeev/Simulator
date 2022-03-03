package yes;

import javax.swing.*;
import java.awt.*;


//ТУДУ: разные масштабы экрана
public class GamePanel extends JPanel implements Runnable {
    final int scale = 3; //later change it so we can choose it from different
    final int originTileSize = 16;
    final int tileSize = scale * originTileSize;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12; //so window will be 4x3
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    KeyInput keyInput = new KeyInput();
    Thread gameThread;

    public GamePanel () {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyInput);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this); //passing gamePanel
        gameThread.start(); //calls run()
    }

    @Override
    public void run() {

        while(gameThread != null) {

            System.out.println("The game loop is running");

            //1.UPDATE INFORMATION
            update();

            //2.DRAW SCREEN WITH UPDATED INFO
            repaint();

        }
    }

    public void update() {
        if(keyInput.spacePressed){
            System.out.println("I've pressed SPACE button");
        }
    }

    public void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(100,100, tileSize, tileSize);
        graphics2D.dispose();
    }
}
