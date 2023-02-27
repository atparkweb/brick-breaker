package BB;

import java.awt.*;

public class MapGenerator {
    public int map[][];
    public int brickWidth;
    public int brickHeight;

    public MapGenerator(int rows, int cols) {
        map = new int[rows][cols];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = 1;
            }
        }

        brickWidth = 540 / cols;
        brickHeight = 150 / rows;
    }

    public void draw(Graphics2D graphics2D) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    graphics2D.setColor(Color.white);
                    int[] brickPos = getBrickPos(i, j);
                    int brickX = brickPos[0];
                    int brickY = brickPos[1];
                    graphics2D.fillRect(brickX, brickY, brickWidth, brickHeight);
                    graphics2D.setStroke(new BasicStroke(3));
                    graphics2D.setColor(Color.black);
                    graphics2D.drawRect(brickX, brickY, brickWidth, brickHeight);
                }
            }
        }
    }

    private int[] getBrickPos(int i, int j) {
        int[] brickPos = new int[2];
        brickPos[0] = j * brickWidth + 80;
        brickPos[1] = i * brickHeight + 50;

        return brickPos;
    }

    public Rectangle getBrickRect(int i, int j) {
        int[] brickPos = getBrickPos(i, j);
        int brickX = brickPos[0];
        int brickY = brickPos[1];

        return new Rectangle(brickX, brickY, brickWidth, brickHeight);
    }

    public void setBrickValue(int value, int row, int col) {
        map[row][col] = value;
    }
}
