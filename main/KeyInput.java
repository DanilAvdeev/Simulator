package main;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

    private boolean escPressed = false;
    private boolean left = false;
    private boolean right = false;
    private boolean shooting = false;


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_ESCAPE) {
            escPressed = true;
        }
        if (code == KeyEvent.VK_A){
            left = true;
        }
        if (code == KeyEvent.VK_D){
            right = true;
        }
        if (code == KeyEvent.VK_SPACE){
            shooting = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_ESCAPE) {
            escPressed = false;
        }
        if (code == KeyEvent.VK_A){
            left = false;
        }
        if (code == KeyEvent.VK_D){
            right = false;
        }
        if (code == KeyEvent.VK_SPACE){
            shooting = false;
        }
    }

    public boolean isEscPressed(){
        return escPressed;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isShooting() {
        return shooting;
    }

    public void setEscPressed(boolean escPressed) {
        this.escPressed = escPressed;
    }
}
