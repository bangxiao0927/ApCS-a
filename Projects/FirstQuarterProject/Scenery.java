import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Scenery extends JPanel {
    // instance variables
    private String dayOrNight;
    private String weather;
    private int width;
    private int height;

    // constructor
    public Scenery(String dayOrNight, String weather, int width, int height) {
        this.dayOrNight = dayOrNight;
        this.weather = weather;
        this.width = width;
        this.height = height;
    }

    
    private void backGroundSelection(Graphics g, String dayOrNight) {
        if (dayOrNight.equals("day")) {
            g.setColor(Color.CYAN);
        } else if (dayOrNight.equals("night")) {
            g.setColor(Color.DARK_GRAY);
        } else {
            System.out.println("Invalid background selection. Defaulting to light gray.");
            g.setColor(Color.LIGHT_GRAY);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background
        g.fillRect(0, 0, width, height);
    }
}