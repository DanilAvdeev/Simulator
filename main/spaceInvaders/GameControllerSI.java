package main.spaceInvaders;

import main.GamePanel;
import main.KeyInput;
import main.WindowState;

import java.awt.*;
import java.util.ArrayList;

public class GameControllerSI {
    ArrayList<Alien> aliens = new ArrayList<>();
    Player player = new Player();
    Shot shot = new Shot();
    Field field = new Field();
    boolean isGameOver = false;
    boolean isWin = false;
    int score = 0;

    public GameControllerSI() {
        initAliens();
    }

    private void shooting() {
        for (Alien i : aliens) {
            if (i.isAlive() && !i.getBomb().isAlive()) {
                i.getBomb().shooter(i.getX(), i.getY());
            }
        }
    }

    public void initAliens() {
        int num = 12;
        int x_start = 80;
        int x_offset = 0;
        int y_start = GamePanel.getScreenHeight() / 10;

        for (int i = 0; i < num; i++) {
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

    public void update(KeyInput keyInput) {
        if (!isGameOver) {
            player.update(keyInput.left, keyInput.right);
            if (keyInput.shooting && !shot.isAlive()) {
                shot.initShot(player.getX(), player.getY());
            }
            if (shot.isAlive()) {
                shot.update();
            }
            shooting();
            switchDirection();
            //aliens came to player
            if (maxY() >= player.getY()) {
                isGameOver = true;
            }

            boolean allDead = true;
            for (Alien i : aliens) {
                if (i.getBomb().isAlive()) {
                    i.getBomb().update();
                }
                if (i.isAlive()) {
                    allDead = false;
                    i.update();
                    if (shot.isAlive() && alienCollision(i, shot)) {
                        i.setDead();
                        shot.setDead();
                        addScore();
                    }
                }
                if (playerCollision(i.getBomb())) {
                    isGameOver = true;
                }
            }
            if (allDead) {
                isWin = true;
                isGameOver = true;
            }
            if (keyInput.escPressed) {
                keyInput.escPressed = false;
                GamePanel.setWindowState(WindowState.MENU);
            }
        } else {
            if (keyInput.escPressed) {
                keyInput.escPressed = false;
                GamePanel.setGameStatus(true);
                isGameOver = false;
                GamePanel.setWindowState(WindowState.MENU);
            }
        }
    }

    public void draw(Graphics2D g2) {
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
        if (isGameOver) {
            drawGameOver(g2);
        }
        g2.dispose();
    }

    private boolean alienCollision(Alien alien, Shot shot) {
        int x_a = alien.getX();
        int y_a = alien.getY();
        int w_a = alien.getW();
        int h_a = alien.getH();

        int x_s = shot.getX();
        int y_s = shot.getY();
        int w_s = shot.getW();
        int h_s = shot.getH();

        if (!shot.isAlive()) {
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

    private boolean playerCollision(Bomb bomb) {
        int x_b = bomb.getX();
        int y_b = bomb.getY();
        int w_b = bomb.getW();
        int h_b = bomb.getH();

        int x_p = player.getX();
        int y_p = player.getY();
        int w_p = player.getW();
        int h_p = player.getH();

        if (x_b + w_b < x_p) {
            return false;
        } else if (x_b > x_p + w_p) {
            return false;
        } else if (y_b + h_b < y_p) {
            return false;
        } else if (y_b > y_p + h_p) {
            return false;
        } else if (x_b + w_b >= x_p && x_b <= x_p + w_p) {
            return y_b + h_b >= y_p && y_b <= y_p + h_p;
        }
        return false;
    }

    private void switchDirection() {
        if (minX() == 0) {
            for (Alien i : aliens) {
                i.setDirection(true);
                i.setY(28);
            }
        }
        if (maxX() == GamePanel.getScreenWidth()) {
            for (Alien i : aliens) {
                i.setDirection(false);
                i.setY(28);
            }
        }
    }

    public int minX() {
        int minX = GamePanel.getScreenWidth();
        for (Alien i : aliens) {
            if (i.getX() < minX) {
                minX = i.getX();
            }
        }
        return minX;
    }

    public int maxX() {
        int maxX = 0;
        for (Alien i : aliens) {
            if (i.getX() + i.getW() > maxX) {
                maxX = i.getX() + i.getW();
            }
        }
        return maxX;
    }

    public int maxY() {
        int maxY = 0;
        for (Alien i : aliens) {
            if (i.getY() + i.getH() > maxY && i.isAlive()) {
                maxY = i.getY() + i.getH();
            }
        }
        return maxY;
    }

    private void drawGameOver(Graphics2D g2) {
        String str;
        Font font;
        int length;

        g2.setComposite(AlphaComposite.SrcOver.derive(0.15f));
        g2.fillRect(0, 0, GamePanel.getScreenWidth(), GamePanel.getScreenHeight());

        if (!isWin) {
            g2.setComposite(AlphaComposite.SrcOver.derive(0.3f));
            g2.setColor(Color.RED);
            str = "Game Over";
            font = new Font("Arial", Font.PLAIN, 60);
            g2.setFont(font);
            length = (int) g2.getFontMetrics().getStringBounds(str, g2).getWidth();
            g2.drawString(str, GamePanel.getScreenWidth() / 2 - length / 2,
                    GamePanel.getScreenHeight() / 3);
        } else {
            g2.setComposite(AlphaComposite.SrcOver.derive(0.3f));
            g2.setColor(Color.GREEN);
            str = "You Won!";
            font = new Font("Arial", Font.PLAIN, 60);
            g2.setFont(font);
            length = (int) g2.getFontMetrics().getStringBounds(str, g2).getWidth();
            g2.drawString(str, GamePanel.getScreenWidth() / 2 - length / 2,
                    GamePanel.getScreenHeight() / 3);
        }
        g2.setColor(Color.GRAY);
        str = "Press esc to leave";
        font = new Font("Arial", Font.PLAIN, 20);
        g2.setFont(font);
        length = (int) g2.getFontMetrics().getStringBounds(str, g2).getWidth();
        g2. drawString(str, GamePanel.getScreenWidth()/2 - length/2,
                GamePanel.getScreenHeight()/5*4);

        font = new Font("Arial", Font.PLAIN, 30);
        g2.setFont(font);
        str = "Your score is: " + score;
        length = (int) g2.getFontMetrics().getStringBounds(str, g2).getWidth();
        g2. drawString(str, GamePanel.getScreenWidth()/2 - length/2,
                GamePanel.getScreenHeight()/2);
        //сделать вывод счета
    }

    public boolean getGameOver(){
        return isGameOver;
    }

    private void addScore(){
        score += 100;
    }
}
