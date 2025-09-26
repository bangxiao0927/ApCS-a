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
        super.paintComponent(g);
        
        g.setColor(Color.YELLOW);
        g.fillOval(0, 0, 300, 300);

        Color brown = new Color(165, 42, 42);
        g.setColor(brown);
        g.fillRect(400, 300, 100, 400);

        g.setColor(Color.green);
        g.fillOval(350,200,200,200);
    }
}