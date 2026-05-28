import java.awt.Color;
import java.awt.Font;
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

    Font title = new Font("Arial", Font.BOLD, 36);
    Font body = new Font("Arial", Font.PLAIN, 20);

    //buttons
    private JButton redFirstStartButton;
    private JButton blackFirstStartButton;
    private JButton returnToMenuButton;

    //Board Game

    private BoardGame boardGame = new BoardGame("red");
    public Screen() {
        setLayout(null);
        addMouseListener(this);
        addKeyListener(this);

        setFocusable(true);

        redFirstStartButton = createButton("Start with Red",350,420,200,40);
        blackFirstStartButton = createButton("Start with Black",350,480,200,40);
        returnToMenuButton = createButton("Return to Menu",350,600,200,40);

        showMenuButtons();
	}

    @Override
    public void addNotify() {
        super.addNotify();
        requestFocusInWindow();
    }

    public void paintComponent(Graphics g){
		super.paintComponent(g);


        if (boardGame.getGameState()) {
            drawGameScreen(g, boardGame);
        } else {
            if (boardGame.getWinStatus().equals("exited")) {
                drawEnd(g);
            } else {
                drawMain(g);
            }
        }
	}

    private JButton createButton(String text, int x, int y, int width, int height) {
		JButton button = new JButton(text);
		button.setBounds(x, y, width, height);
		button.setFocusable(false);
		button.addActionListener(this);
		add(button);
		return button;
	}

    public void drawMain(Graphics g) {
        if (!boardGame.getGameState()) { 
            g.setColor(BACK_GROUND_COLOR);
            g.fillRect(0, 0, 900, 700);
            g.setColor(BLACK);
            g.setFont(title);
            g.drawString("Welcome to Xiangqi!", 290, 200);
            //draw Rules

            g.setFont(body);
            g.drawString("Rules:", 240, 250);

            showMenuButtons();
        }
    }

    private void drawGameScreen(Graphics g, BoardGame boardGame) {
        boardGame.drawGame(g,boardGame);
        showGameButtons();
    }

    //using this for end game screen and implementing in drawGame for win condition in BoardGame
    public void drawEnd(Graphics g) {
        if (boardGame.getWinStatus().equals("exited")) {
            g.setColor(BACK_GROUND_COLOR);
            g.fillRect(0, 0, 900, 700);
            g.setColor(BLACK);
            g.setFont(title);
            g.drawString("Game Exited", 350, 350);
            showEndButtons();
            return;
        }
        g.setColor(BACK_GROUND_COLOR);
        g.fillRect(0, 0, 900, 700);
        g.setColor(BLACK);
        g.setFont(title);
        g.drawString(boardGame.getWinStatus() + "WINS", 290, 200);
        showEndButtons();
    }

    //essentials
    @Override
    public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
        if (src == redFirstStartButton ) {
            boardGame = new BoardGame("red");
            boardGame.updateInGame(true);
            showGameButtons();
        } else if(src == blackFirstStartButton ) {
            boardGame = new BoardGame("black");
            boardGame.updateInGame(true);
            showGameButtons();
        } else if(src == returnToMenuButton ) {
            boardGame = new BoardGame("red");
            boardGame.updateInGame(false);
            showMenuButtons();
        }
        repaint();
        requestFocusInWindow();
    }

    private void showMenuButtons() {
        redFirstStartButton.setVisible(true);
        blackFirstStartButton.setVisible(true);
        returnToMenuButton.setVisible(false);
    }

    private void showGameButtons() {
        redFirstStartButton.setVisible(false);
        blackFirstStartButton.setVisible(false);
        returnToMenuButton.setVisible(false);
    }

    private void showEndButtons() {
        redFirstStartButton.setVisible(false);
        blackFirstStartButton.setVisible(false);
        returnToMenuButton.setVisible(true);
    }

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
		if (e.getKeyCode() == KeyEvent.VK_F1) {
            boardGame.setForceQuit();
            repaint();
        }
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}
