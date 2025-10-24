import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Screen extends JPanel {

    private Color yellow;
    private int sunY;

    public Screen(){
        yellow = new Color(255, 255, 0);
        sunY = 500;
        setBackground(yellow);
    }

	@Override
	public Dimension getPreferredSize(){
		return new Dimension(800,600);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);

        drawSun(g);
	}

    public void drawSun(Graphics g) {
        g.setColor(yellow);
        g.fillOval(500,sunY,50,50);
    }

    public void animate() {
        while (true) { 

            //move the sun up
            sunY --;

            try {
                Thread.sleep(16); // slow down the process in milliseconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            repaint();
        }
    }
}
