import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

public class Screen extends Sprite implements ActionListener, MouseListener, KeyListener {
    public final static Color BACK_GROUND_COLOR = new Color(255,205,120);
    public final static Color BLACK = Color.BLACK;
    public final static Color RED = new Color(153,0,0);

    //buttons
    private JButton redFirstStartButton;
    private JButton blackFirstStartButton;
    private JButton returnToMenuButton;

    private BoardGame boardGame = new BoardGame("red");

    public Screen() {
        setLayout(null);
        addMouseListener(this);
        addKeyListener(this);

        setFocusable(true);
        requestFocusInWindow();
	}

    public void paintComponent(Graphics g){
		super.paintComponent(g);

        drawMain(g); 
	}

    private JButton createButton(String text, int x, int y, int width, int height) {
		JButton button = new JButton(text);
		button.setBounds(x, y, width, height);
		button.setFocusable(false);
		button.addActionListener(this);
		add(button);
		return button;
	}

    public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
        if (src == redFirstStartButton ) {
            boardGame.updateInGame();
        } else if(src == blackFirstStartButton ) {
            boardGame = new BoardGame("black");
            boardGame.updateInGame();
        } else if(src == returnToMenuButton ) {
            boardGame.updateInGame();
        }
    }

    public void drawMain(Graphics g) {
        if (!boardGame.getGameState()) { 
            g.setColor(BACK_GROUND_COLOR);
            g.drawRect(0, 0, WIDTH, HEIGHT);
            g.setColor(BLACK);
            g.drawString("Welcome to Xiangqi!", 350, 200);
            //draw Rules
            
            //Start Button
            redFirstStartButton = createButton("Start",350,400,200,40);
            blackFirstStartButton = createButton("Start",350,600,200,40);
        }
    }

    public void drawEnd(Graphics g) {
        g.setColor(BACK_GROUND_COLOR);
        g.drawRect(0, 0, WIDTH, HEIGHT);
        g.setColor(BLACK);
        g.drawString(boardGame.getWinStatus() + "wins", 350, 200);
    }

    public void board(Graphics g) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++) {
                g.drawRect(j * 60, i * 60, 60, 60);
            }
        }
    }    

    public void graphicsPieces(int pieceSide , String piece , Graphics g, int row, int col) {
        Color color = (pieceSide == 1) ? new Color(222, 26, 26) : new Color(0, 0, 0);
        g.setColor(color);
        g.fillOval(col * 60, row * 60, 60, 60);
        g.drawString(piece, col * 60 + 10, row * 60 + 40);
    }

    //essentials
    public void mousePressed(MouseEvent e) {
        //Print location of x and y
        System.out.println("X: " + e.getX() + ", Y: " + e.getY());
        repaint();
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}

	public void keyPressed(KeyEvent e) {
		
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}
