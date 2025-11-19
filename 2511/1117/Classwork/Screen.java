import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;

public class Screen extends JPanel {
    private Tree tree = new Tree();
    private final Tree[] myTrees;

    public Screen() {
        myTrees = new Tree[100];
        for (int i = 0; i < myTrees.length; i++) {
            int randX = (int) (Math.random() * 800); // Assuming screen width is 800
            int randY = (int) (Math.random() * 400) + 200; // Assuming screen height is 600, trees start from y=200
            myTrees[i] = new Tree(randX, randY);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < myTrees.length; i++) {
            myTrees[i].drawMe(g);
        }
    }
}
