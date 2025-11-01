import java.awt.*;
import javax.swing.*;

public class Screen extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Sky
        g.setColor(new Color(135, 206, 235)); // light blue
        g.fillRect(0, 0, getWidth(), getHeight() / 2);

        //Ground
        g.setColor(new Color(85, 107, 47)); // olive green
        g.fillRect(0, getHeight() / 2, getWidth(), getHeight() / 2);

        //Grass Field 10x6
        int startX = 50;
        int startY = 350;
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 6; col++) {
                int grassX = col * 12 + startX;
                int grassY = row * 12 + startY;
                drawGrass(g, grassX, grassY);
            }
        }

        //Fruit Tree Field 5x5
        startX = 300;
        startY = 300;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                int treeX = col * 60 + startX;
                int treeY = row * 60 + startY;
                drawTree(g, treeX, treeY);
            }
        }

        //Vegetable Field 5x5
        startX = 650;
        startY = 380;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                int vegX = col * 40 + startX;
                int vegY = row * 40 + startY;
                drawPumpkin(g, vegX, vegY);
            }
        }
    }

    // Draw one blade of grass
    public void drawGrass(Graphics g, int x, int y) {
        g.setColor(Color.GREEN);
        g.drawLine(x, y, x, y - 8);
    }

    // Draw a fruit tree
    public void drawTree(Graphics g, int x, int y) {
        // trunk
        g.setColor(new Color(139, 69, 19));
        g.fillRect(x + 8, y + 20, 4, 20);
        // leaves
        g.setColor(Color.GREEN);
        g.fillOval(x - 5, y, 25, 25);
        // apple
        g.setColor(Color.RED);
        g.fillOval(x + 5, y + 5, 6, 6);
    }

    // Draw one vegetable (pumpkin)
    public void drawPumpkin(Graphics g, int x, int y) {
        g.setColor(new Color(255, 140, 0));
        g.fillOval(x, y, 25, 20);
        g.setColor(Color.GREEN);
        g.fillRect(x + 10, y - 4, 4, 6);
    }
}
