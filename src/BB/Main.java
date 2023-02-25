package BB;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        int width = 800;
        int height = 600;

        GamePlay gamePlay = new GamePlay(width, height);

        frame.setBounds(10, 10, width, height);
        frame.setTitle("Brick Breaker");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(gamePlay);
    }
}
