import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Screen extends JPanel implements java.awt.event.ActionListener {
    private static String treeString;
    private static String flowerString;
    private static int numTrees = 40;
    private static int numFlowers = 20;
	private JButton redrawButton;
    private JTextField treeInput;
    private JTextField flowerInput;

    public Screen() {
        setLayout( null );

        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.CYAN);

        redrawButton = new JButton("Repaint");
		redrawButton.setBounds( 310, 30, 100, 30 );

		add( redrawButton );

		//listener
		redrawButton.addActionListener( this ::actionPerformed );

        //tree input
        treeInput = new JTextField();
		treeInput.setBounds( 50, 30, 100, 30 );
		add( treeInput );

        //flower input
        flowerInput = new JTextField();
		flowerInput.setBounds( 50, 70, 100, 30 );
		add( flowerInput );
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw ground
        drawGround(g);
       
        // Draw trees
        for(int i=0; i< numTrees; i++){
            int treeX = (int)(Math.random() * getWidth());
            int treeY = (int)(Math.random() * getHeight());
            drawTree(g,treeX, treeY);
        }

        // Draw flowers
        for(int i=0; i< numFlowers; i++){
            int X = (int)(Math.random() * getWidth());
            int Y = (int)(Math.random() * getHeight());
            drawFlower(g, X, Y);
        }

        // Draw quantity of grass
        for(int i=0; i< 100; i++){
            int X = (int)(Math.random() * getWidth());
            int Y = (int)(Math.random() * getHeight());
            drawFlower(g, X, Y);
        }

        // Draw clouds
        for(int i=0; i< 10; i++){
            int X = (int)(Math.random() * getWidth());
            int Y = (int)(Math.random() * getHeight());
            drawFlower(g, X, Y);
        }

        // Draw birds
        for(int i=0; i< 3; i++){
            int X = (int)(Math.random() * getWidth());
            int Y = (int)(Math.random() * getHeight());
            drawFlower(g, X, Y);
        }

        // Draw cows
        for(int i=0; i< 5; i++){
            int X = (int)(Math.random() * getWidth());
            int Y = (int)(Math.random() * getHeight());
            drawFlower(g, X, Y);
        }
    }

    private void drawGround(Graphics g) {
        g.setColor(new Color(34, 139, 34)); // Green color
        g.fillRect(0, getHeight() / 4 * 3, getWidth(), getHeight() / 4 * 3);
    }

    private void drawTree(Graphics g, int x, int y) {
        // Draw trunk
        g.setColor(new Color(139, 69, 19)); // Brown color
        g.fillRect(x, y, 20, 50);
        
        // Draw leaves
        g.setColor(Color.GREEN);
        g.fillOval(x - 15, y - 30, 50, 50);
    }

    // Draw one flower
    public void drawFlower(Graphics g, int x, int y) {
        g.setColor(Color.PINK);
        g.fillOval(x, y, 10, 10);
        g.setColor(Color.YELLOW);
        g.fillOval(x + 2, y + 2, 6, 6);
    }

    // Draw one blade of grass
    public void drawGrass(Graphics g, int x, int y) {
        g.setColor(Color.GREEN);
        g.drawLine(x, y, x, y - 8);
    }

    // Draw one cloud
    public void drawCloud(Graphics g, int x, int y) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, 30, 20);
        g.fillOval(x + 15, y - 10, 30, 20);
        g.fillOval(x + 30, y, 30, 20);
    }

    // Draw one bird
    public void drawBird(Graphics g, int x, int y) {
        g.setColor(Color.CYAN);
        g.fillOval(x, y, 25, 15);
        g.setColor(Color.ORANGE);
        g.drawArc(x, y, WIDTH, HEIGHT, ABORT, HEIGHT); // beak
    }   

    // Draw one animal (cow)
    public void drawCow(Graphics g, int x, int y) {
        g.setColor(Color.BLACK);
        g.fillOval(x, y, 50, 30); // Body
        g.setColor(Color.WHITE);
        g.fillOval(x + 10, y - 10, 30, 30); // Head
        g.setColor(Color.BLACK);
        g.fillOval(x + 15, y - 5, 10, 10); // Eye
        g.setColor(Color.PINK);
        g.fillOval(x + 20, y + 5, 10, 10); // Udder
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == redrawButton) {
            treeString = treeInput.getText();
            flowerInput.getText();
            numTrees = Integer.parseInt(treeString);
            numFlowers = Integer.parseInt(flowerString);
			removeAll();
            add( redrawButton );
			repaint();
		}
    }
}