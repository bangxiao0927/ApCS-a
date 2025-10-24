import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Screen extends JPanel {

    // Object positions
    private int sunY = 500;
    private int cloudX = 100;
    private int birdX = 700, birdY = 100;
    private int boatX = 50, boatY = 520;
    private int starX = 400, starY = 300;

    // Object speed/direction variables
    private int cloudSpeed = 2;
    private int birdDX = -3, birdDY = 2;
    private int boatSpeed = 1;
    private int starDY = -2;

    public Screen() {
        setBackground(new Color(135, 206, 235)); // sky blue
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawSun(g);
        drawCloud(g);
        drawBird(g);
        drawBoat(g);
        drawStar(g);
    }

    // ☀️ Sun moving upward
    public void drawSun(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillOval(600, sunY, 80, 80);
    }

    // Cloud moving to the right
    public void drawCloud(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(cloudX, 100, 60, 40);
        g.fillOval(cloudX + 25, 85, 60, 40);
        g.fillOval(cloudX + 50, 100, 60, 40);
    }

    // Bird flying diagonally
    public void drawBird(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval(birdX, birdY, 30, 20);
        g.drawLine(birdX, birdY, birdX - 10, birdY - 10);
        g.drawLine(birdX + 30, birdY, birdX + 40, birdY - 10);
    }

    // Boat moving left
    public void drawBoat(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(boatX, boatY, 80, 20);
        g.setColor(Color.WHITE);
        g.fillPolygon(new int[]{boatX + 40, boatX + 60, boatX + 40},
                      new int[]{boatY, boatY - 40, boatY}, 3);
    }

    // Star moving upward
    public void drawStar(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(starX, starY, 20, 20);
    }

    public void animate() {
        while (true) {
            // Move each object in its direction
            sunY -= 1; // up
            cloudX += cloudSpeed; // right
            birdX += birdDX; 
            birdY += birdDY; // diagonal
            boatX -= boatSpeed; // left
            starY += starDY; // up

            repaint();

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
