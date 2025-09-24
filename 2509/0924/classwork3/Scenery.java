import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

public class Scenery extends JPanel {
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        //sky
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, 800, 600);

        //ground
        g.setColor(Color.GREEN);
        g.fillRect(0, 475, 800, 125);

        //sun
        g.setColor(Color.YELLOW);
        g.fillOval(0, 0, 300, 300);

        Color brown = new Color(165, 42, 42);

        //tree1
        g.setColor(brown);
        g.fillRect(400, 300, 25, 200);

        g.setColor(Color.green);
        g.fillOval(362,250,100,100);

        //tree2
        g.setColor(brown);
        g.fillRect(550, 300, 25, 200);

        g.setColor(Color.green);
        g.fillOval(512,250,100,100);

        //tree3
        g.setColor(brown);
        g.fillRect(700, 300, 25, 200);

        g.setColor(Color.green);
        g.fillOval(662,250,100,100);

        //lake
        g.setColor(Color.CYAN);
        g.fillOval(100,500,200,50);

        //clouds
        g.setColor(Color.WHITE);
        g.fillOval(550,150,200,100);

        g.fillOval(350,100,200,100);
        g.fillOval(500,50,200,75);
        g.fillOval(700,100,150,70);
    }
}