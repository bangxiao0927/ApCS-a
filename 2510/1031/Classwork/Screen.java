import java.awt.*;
import javax.swing.*;

public class Screen extends JPanel implements Runnable {

    //sun
    private int sunY = 100;
    private boolean day = true;

    //animal vars
    private int cowX = 100;
    private int pigX = 300;
    private int birdX = 500;
    private boolean birdMovingRight = true; // for bouncing

    private final int GROUND_Y = 300;

    public Screen() {
        Thread t = new Thread(this);
        t.start(); // start animation loop
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //sku switch from day and night
        if (day) {
            g.setColor(new Color(135, 206, 235)); //day color
        } else {
            g.setColor(new Color(25, 25, 112)); //dark night sky blue
        }
        g.fillRect(0, 0, getWidth(), getHeight() / 2);

        //draw Ground
        g.setColor(new Color(85, 107, 47));
        g.fillRect(0, getHeight() / 2, getWidth(), getHeight() / 2);

        //sun and moon color
        if (day) {
            g.setColor(Color.YELLOW);
        } else {
            g.setColor(Color.WHITE);
        }
        g.fillOval(100, sunY, 80, 80);

        //draw field
        drawFields(g);

        //ddraw animals
        drawCow(g, cowX, GROUND_Y);
        drawPig(g, pigX, GROUND_Y + 40);
        drawBird(g, birdX, GROUND_Y - 60);
    }

    private void drawFields(Graphics g) {
        // Grass Field 10x6
        int startX = 50;
        int startY = 350;
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 6; col++) {
                int grassX = col * 12 + startX;
                int grassY = row * 12 + startY;
                drawGrass(g, grassX, grassY);
            }
        }

        // Fruit Tree Field 5x5
        startX = 300;
        startY = 300;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                int treeX = col * 60 + startX;
                int treeY = row * 60 + startY;
                drawTree(g, treeX, treeY);
            }
        }

        // Vegetable Field 5x5 (pumpkin)
        startX = 650;
        startY = 380;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                int vegX = col * 40 + startX;
                int vegY = row * 40 + startY;
                drawPumpkin(g, vegX, vegY);
            }
        }
    }

    // Draw one blade of grass
    public void drawGrass(Graphics g, int x, int y) {
        g.setColor(Color.GREEN);
        g.drawLine(x, y, x, y - 8);
    }

    // Draw a fruit tree
    public void drawTree(Graphics g, int x, int y) {
        g.setColor(new Color(139, 69, 19)); // trunk
        g.fillRect(x + 8, y + 20, 4, 20);
        g.setColor(Color.GREEN); // leaves
        g.fillOval(x - 5, y, 25, 25);
        g.setColor(Color.RED); // apple
        g.fillOval(x + 5, y + 5, 6, 6);
    }

    // Draw one pumpkin
    public void drawPumpkin(Graphics g, int x, int y) {
        g.setColor(new Color(255, 140, 0));
        g.fillOval(x, y, 25, 20);
        g.setColor(Color.GREEN);
        g.fillRect(x + 10, y - 4, 4, 6);
    }

    //draw cow
    private void drawCow(Graphics g, int x, int y) {
        // Body
        g.setColor(Color.WHITE);
        g.fillRect(x, y, 60, 30);
        g.setColor(Color.BLACK);
        g.fillOval(x + 10, y + 5, 10, 10);
        g.fillOval(x + 30, y + 10, 10, 10);
        // Head
        g.setColor(Color.WHITE);
        g.fillOval(x + 55, y + 5, 20, 20);
    }
	
  	//draw pig 
    private void drawPig(Graphics g, int x, int y) {
        g.setColor(Color.PINK);
        g.fillOval(x, y, 40, 25);
        g.fillOval(x + 30, y + 5, 15, 15); // head
        g.setColor(Color.BLACK);
        g.fillOval(x + 38, y + 10, 3, 3);
        g.fillOval(x + 43, y + 10, 3, 3);
    }

		//draw bird
    private void drawBird(Graphics g, int x, int y) {
        g.setColor(Color.CYAN);
        g.fillOval(x, y, 25, 15);
        g.setColor(Color.ORANGE);
        g.fillPolygon(new int[]{x + 25, x + 35, x + 25}, new int[]{y + 5, y + 8, y + 11}, 3); // beak
    }

    //animation values
    @Override
    public void run() {
        while (true) {
            //sun move up
            sunY -= 2;

            //goes off screen switch to night
            if (sunY < -200) {
                sunY = getHeight(); // reset to below ground
            }

            //day or night
            day = (sunY > 0);

            //animals only move during day
            if (day) {
                cowX += 2;
                pigX += 3;

                //cow and pig movement
                if (cowX > getWidth()) cowX = -60;
                if (pigX > getWidth()) pigX = -40;

                //bird bounce on the edge
                if (birdMovingRight) {
                    birdX += 4;
                    if (birdX + 40 > getWidth()) birdMovingRight = false;
                } else {
                    birdX -= 4;
                    if (birdX < 0) birdMovingRight = true;
                }
            }
            repaint();

            //Delay
            try {
                Thread.sleep(33); // 1000/30 = 33 (to 30fps)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
