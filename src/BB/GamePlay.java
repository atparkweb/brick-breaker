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
    private final int ballStartDX = -2;
    private final int ballStartDY = -3;
    private int ballDX = ballStartDX;
    private int ballDY = ballStartDY;
    private final int ballW = 20;
    private final int ballH = 20;

    private MapGenerator mapGenerator;

    private final int frameW;
    private final int frameH;

    public GamePlay(int width, int height) {
        this.frameW = width;
        this.frameH = height;
        this.playerX = width / 2;
        this.playerY = height - 100;

        mapGenerator = new MapGenerator(3, 7);
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

        // Draw bricks
        mapGenerator.draw((Graphics2D)g);

        // Draw ball
        g.setColor(Color.yellow);
        g.fillOval(ballX, ballY, ballW, ballH);

        // Draw text
        // TODO set text positions based on frame
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("" + score, 590, 30);

        if (numberOfBricks <= 0) {
            gameWin(g);
        }

        if (ballY > 570) {
            gameOver(g);
        }

        // Releases resources after this method is finished
        g.dispose();
    }

    private void gameWin(Graphics g) {
        isPlaying = false;
        ballDX = 0;
        ballDY = 0;

        g.setColor(Color.green);
        g.setFont(new Font("serif", Font.BOLD, 30));
        g.drawString("YOU WON, Score: " + score, 190, 300);

        g.setFont(new Font("serif", Font.BOLD, 20));
        g.drawString("Press ENTER to restart", 230, 350);
    }

    private void gameOver(Graphics g) {
        isPlaying = false;
        ballDX = 0;
        ballDY = 0;

        g.setColor(Color.red);
        g.setFont(new Font("serif", Font.BOLD, 30));
        g.drawString("GAME OVER, Score: " + score, 190, 300);

        g.setFont(new Font("serif", Font.BOLD, 20));
        g.drawString("Press ENTER to restart", 230, 350);
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

            for (int i = 0; i < mapGenerator.map.length; i++) {
                for (int j = 0; j < mapGenerator.map[0].length; j++) {
                    if (mapGenerator.map[i][j] > 0) {
                        Rectangle brickHitBox = mapGenerator.getBrickRect(i, j);
                        if (ballHitBox.intersects(brickHitBox)) {
                            mapGenerator.setBrickValue(0, i, j);
                            numberOfBricks--;
                            score += 5;

                            if (ballX + ballW - 1 <= brickHitBox.x || ballX + 1 >= brickHitBox.x + brickHitBox.width) {
                                ballDX = -ballDX; // Reverse the ball direction
                            } else {
                                ballDY = -ballDY; // Reverse the ball direction
                            }
                        }
                    }
                }
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

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!isPlaying) {
                isPlaying = true;
                ballX = 120;
                ballY = 350;
                ballDX = ballStartDX;
                ballDY = ballStartDY;
                score = 0;
                numberOfBricks = 21;
                mapGenerator = new MapGenerator(3, 7);

                repaint();
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
