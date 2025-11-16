import java.awt.Graphics;
import javax.swing.JPanel;

public class Screen extends JPanel {
    private Tree[] myTrees;

    public Screen() {
        myTrees = new Tree[100];
        for (int i = 0; i < myTrees.length; i++) {
            int randX = (int)(Math.random() * 800); // assuming screen width is 800
            int randY = (int)(Math.random() * 400) + 200; // assuming screen height is 600, trees start from y=200
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
