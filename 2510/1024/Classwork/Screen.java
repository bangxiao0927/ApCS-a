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
        return new Dimension(1920, 1080);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGround(g);
        drawSun(g);
        drawCloud(g);
        drawBird(g);
        drawBoat(g);
        drawStar(g);
        
		int x = 1;
        while(x<=1920) {
        	drawTree(g,x,900);
            x+=96;
        }
        int grassX = 1;
        while(grassX<=1920) {
        	drawGrass(g,grassX,950);
            grassX+=20;
        }
    }
    
    //ground
    public void drawGround(Graphics g) {
        g.setColor(new Color(34, 139, 34)); //green
        g.fillRect(0, 920, 1920, 80);
    }

    // Sun moving upward
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
        g.setColor(new Color(139, 69, 19)); // brown
        g.drawArc(sunY, sunY, 180, 80, 180, 180);
    }

    // Star moving upward
    public void drawStar(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(starX, starY, 20, 20);
    }
    
    // Tree
    public void drawTree(Graphics g, int x, int y) {
        // trunk
        g.setColor(new Color(139, 69, 19)); // brown
        g.fillRect(x, y, 50, 100);

        // leaves
        g.setColor(Color.GREEN);
        g.fillOval(x - 25, y - 90, 100, 100);
    }
    
    //grass
    public void drawGrass(Graphics g, int x, int y) {
        // blades
        g.setColor(Color.GREEN); // green
        g.fillRect(x, y, 10, 50);
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
            
            // Wrap around edges to keep animation continuous
            if (cloudX > getWidth()) cloudX = -100;
            if (boatX < -100) boatX = getWidth();
            if (birdX < -50 || birdX > getWidth()) birdDX = -birdDX;
            if (birdY < 0 || birdY > getHeight()) birdDY = -birdDY;
            if (sunY < -100) sunY = 1080;
            if (starY < -50) starY = 1080;

            repaint();

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}