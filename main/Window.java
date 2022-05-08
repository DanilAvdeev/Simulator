package main;

import javax.swing.*;

public class Window {
    private static Window instance;
    JFrame frame;

    private Window() {
        initFrame();
    }

    public static Window getInstance(){
        if (instance == null){
            instance = new Window();
        }
        return instance;
    }

    void initFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Simulator");
        frame.setResizable(false);

        GamePanel gamePanel = GamePanel.getInstance();
        frame.add(gamePanel);
        frame.pack();
        frame.setVisible(true);

        gamePanel.startGameThread();
    }
}