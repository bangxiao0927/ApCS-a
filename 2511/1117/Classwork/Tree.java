public class Tree {
    private int x;
    private int y;

    public Tree(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void drawMe(Graphics g) {
        // Draw the trunk
        g.setColor(new Color(139, 69, 19)); // Brown color for the trunk
        g.fillRect(x, y, 20, 60); // Trunk dimensions

        // Draw the leaves
        g.setColor(Color.GREEN); // Green color for the leaves
        g.fillOval(x - 30, y - 50, 80, 80); // Leaves dimensions
    }
}
