package yes;

import javax.swing.*;

public class Window implements Runnable{
    JFrame frame;

    @Override
    public void run() {
        initFrame();
    }

    void initFrame() {
        Config conf = new Config();

        frame = new JFrame();
        frame.getContentPane().setLayout(null);
        frame.setSize(conf.getSize() * conf.getWidth(), conf.getSize() * conf.getHeight());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("Life Game");
    }
}
