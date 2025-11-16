import java.awt.Color;
import java.awt.Graphics;

public class Tree {
    private final int x;
    private final int y;

    public Tree(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void drawMe(Graphics g) {
        // Draw the trunk
        g.setColor(new Color(139, 69, 19)); // Brown color for trunk
        g.fillRect(x - 10, y, 20, 50); // Trunk rectangle

        // Draw the leaves
        g.setColor(new Color(34, 139, 34)); // Green color for leaves
        g.fillOval(x - 30, y - 40, 60, 60); // Leaves oval
    }
}
