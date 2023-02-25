package BB;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        final int FRAME_WIDTH = 700;
        final int FRAME_HEIGHT = 600;

        JFrame frame = new JFrame();

        GamePlay gamePlay = new GamePlay(FRAME_WIDTH, FRAME_HEIGHT);

        frame.setBounds(10, 10, FRAME_WIDTH, FRAME_HEIGHT);
        frame.setTitle("Brick Breaker");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(gamePlay);
    }
}
