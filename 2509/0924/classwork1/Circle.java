import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

public class Circle extends JPanel {
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //This is recommand from the class

        g.setColor(Color.YELLOW);
        g.fillOval(250, 150, 300, 300);
    }
}