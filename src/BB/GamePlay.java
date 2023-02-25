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

    private Timer timer;
    private int delay = 8;

    private int playerX;
    private final int PLAYER_DX = 20;    // number of pixels to move player

    private int ballX = 120;
    private int ballY = 350;
    private int ballDX = -1;
    private int ballDY = -2;

    private final int FRAME_WIDTH;
    private final int FRAME_HEIGHT;

    public GamePlay(int width, int height) {
        this.FRAME_WIDTH = width;
        this.FRAME_HEIGHT = height;
        this.playerX = width / 2;
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        int width = this.FRAME_WIDTH - 8;
        int height = this.FRAME_HEIGHT - 8;
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
        g.fillRect(playerX, height - 50, 100, 8);

        // Draw ball
        g.setColor(Color.yellow);
        g.fillOval(ballX, ballY, 20, 20);

        // Releases resources after this method is finished
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        final int PLAYER_L_BOUND = 3;
        final int PLAYER_R_BOUND = FRAME_WIDTH - 100;

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX + PLAYER_DX > PLAYER_R_BOUND) {
                playerX = PLAYER_R_BOUND;
            } else {
                moveRight();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX - PLAYER_DX < PLAYER_L_BOUND) {
                playerX = PLAYER_L_BOUND;
            } else {
                moveLeft();
            }
        }
    }

    private void moveRight() {
        isPlaying = true;
        playerX += PLAYER_DX;
    }
    private void moveLeft() {
        isPlaying = true;
        playerX -= PLAYER_DX;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
