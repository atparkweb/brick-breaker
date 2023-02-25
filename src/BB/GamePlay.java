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

    private int playerX = 310;

    private int ballX = 120;
    private int ballY = 350;
    private int ballDX = -1;
    private int ballDY = -2;

    public GamePlay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void render(Graphics g) {
        // Draw background
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);

        // Draw walls
        g.setColor(Color.white);
        g.fillRect(0, 0, 3, 592);   // left wall
        g.fillRect(0, 0, 692, 3);   // top wall
        g.fillRect(691, 0, 3, 592); // right wall

        // Draw paddle
        g.setColor(Color.white);
        g.fillRect(playerX, 590, 100, 8);

        // Draw ball
        g.setColor(Color.yellow);
        g.fillOval(ballX, ballY, 20, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
