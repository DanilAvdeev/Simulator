package main;

import javax.swing.*;

public class Window {
    JFrame frame;

    public Window() {
        initFrame();
    }

    void initFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Life Game");
        frame.setResizable(false);

        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        frame.pack();
        frame.setVisible(true);

        gamePanel.startGameThread();
    }
}