package main.spaceInvaders;

import main.GamePanel;
import main.KeyInput;
import main.WindowState;

import java.awt.*;
import java.util.ArrayList;

public class GameController {
    ArrayList<Alien> aliens = new ArrayList<>();
    Player player = new Player();
    Shot shot = new Shot();
    Field field = new Field();

    public GameController(){
        initAliens();
    }

    private void shooting(){
        for(Alien i: aliens){
            if(i.isAlive() && !i.getBomb().isAlive()) {
                i.getBomb().shooter(i.getX(), i.getY());
            }
        }
    }

    public void initAliens() {
        int num = 12;
        int x_start = 80;
        int x_offset = 0;
        int y_start = GamePanel.getScreenHeight()/10;

        for(int i = 0; i < num; i ++) {
            Bomb bomb = new Bomb();
            Alien alien = new Alien(x_start + x_offset, y_start, bomb);
            x_offset += 160;
            if (i == 3 || i == 7) {
                x_offset = 0;
                y_start += 28;
            }
            aliens.add(alien);
        }
    }

    public  WindowState update(KeyInput keyInput){
        player.update(keyInput.left, keyInput.right);
        if (keyInput.shooting && !shot.isAlive()) {
            shot.initShot(player.getX(), player.getY());
        }
        if (shot.isAlive()) {
            shot.update();
        }
        shooting();
        for(Alien i: aliens) {
            if(i.getBomb().isAlive()){
                i.getBomb().update();
            }
            if (i.isAlive()) {
                i.update();
                if (collision(i, shot)) {
                    i.setDead();
                    shot.setDead();
                }
            }
        }
        if (keyInput.escPressed) {
            keyInput.escPressed = false;
            return WindowState.MENU;
        }
        return  WindowState.GAME_2;
    }

    public void draw(Graphics2D g2){
        field.draw(g2);
        player.draw(g2);
        if (shot.isAlive()) {
            shot.draw(g2);
        }
        for (Alien i : aliens) {
                i.draw(g2);
                if (i.getBomb().isAlive()) {
                    i.getBomb().draw(g2);
                }
        }
        g2.dispose();
    }

    private boolean collision(Alien alien, Shot shot) {
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
            return y_a <= y_s + w_s && y_a + w_a >= y_s;
        }
        return false;
    }

    public int minX(ArrayList<Alien> aliens){
        int minX = GamePanel.getScreenWidth();
            for(Alien i: aliens){
                if(i.getX() < minX) {
                    minX = i.getX();
                }
            }
        return minX;
    }

    public int maxX(ArrayList<Alien> aliens){
        int maxX = 0;
        for(Alien i: aliens){
            if(i.getX() > maxX) {
                maxX = i.getX();
            }
        }
        return maxX;
    }

    public int minY(ArrayList<Alien> aliens){
        int maxY = 0;
        for(Alien i: aliens){
            if(i.getY()+i.getH() < maxY) {
                maxY = i.getY() + i.getH();
            }
        }
        return maxY;
    }
}
