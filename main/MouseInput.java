package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {
    private int mouseX = 0, mouseY = 0;
    private boolean clicked = false;

    @Override
    public void mousePressed(MouseEvent e) {
        clicked = true;
        System.out.println("pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        clicked = false;
        System.out.println("released");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public int getMouseX(){
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}
}
