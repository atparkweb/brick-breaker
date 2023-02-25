package BB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private boolean isPlaying = false;
    private int score = 0;
    private int numberOfBricks = 21;

    private final Timer timer;
    private final int delay = 8;

    private int playerX;
    private final int playerY;
    private final int playerW = 100;
    private final int playerH = 8;
    private final int playerDX = 20;    // number of pixels to move player

    private int ballX = 120;
    private int ballY = 350;
    private int ballDX = -1;
    private int ballDY = -2;
    private final int ballW = 20;
    private final int ballH = 20;

    private final int frameW;
    private final int frameH;

    public GamePlay(int width, int height) {
        this.frameW = width;
        this.frameH = height;
        this.playerX = width / 2;
        this.playerY = height - 50;
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        int width = this.frameW - 8;
        int height = this.frameH - 8;
        final int BORDER = 3;

        // Draw background
        g.setColor(Color.black);
        g.fillRect(1, 1, width, height);

        // Draw walls
        g.setColor(Color.white);
        g.fillRect(0, 0, BORDER, height);   // left wall
        g.fillRect(0, 0, width, BORDER);   // top wall
        g.fillRect(width - 13, 0, BORDER, height); // right wall

        // Draw paddle
        g.setColor(Color.white);
        g.fillRect(playerX, playerY, playerW, playerH);

        // Draw ball
        g.setColor(Color.yellow);
        g.fillOval(ballX, ballY, ballW, ballH);

        // Releases resources after this method is finished
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if (isPlaying) {
            Rectangle ballHitBox = new Rectangle(ballX, ballY, ballW, ballH + ballH / 2);
            Rectangle paddleHitBox = new Rectangle(playerX, playerY, playerW, playerH);

            if (ballHitBox.intersects(paddleHitBox)) {
                ballDY = -ballDY;
            }

            ballX += ballDX;
            ballY += ballDY;

            if (ballX < 0 || ballX > this.frameW - 13 - ballW) {
                ballDX = -ballDX;
            }
            if (ballY < 0) {
                ballDY = -ballDY;
            }

            if (ballY > this.frameH) {
                isPlaying = false;
                System.out.println("GAME OVER");
            }
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        final int PLAYER_L_BOUND = 3;
        final int PLAYER_R_BOUND = frameW - 100;

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX + playerDX > PLAYER_R_BOUND) {
                playerX = PLAYER_R_BOUND;
            } else {
                moveRight();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX - playerDX < PLAYER_L_BOUND) {
                playerX = PLAYER_L_BOUND;
            } else {
                moveLeft();
            }
        }
    }

    private void moveRight() {
        isPlaying = true;
        playerX += playerDX;
    }
    private void moveLeft() {
        isPlaying = true;
        playerX -= playerDX;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
