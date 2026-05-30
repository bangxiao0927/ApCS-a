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
    public final static Color BACK_GROUND_COLOR = new Color(255,226,175);
    public final static Color BLACK = Color.BLACK;
    public final static Color RED = new Color(153,0,0);
    public final static Color BOARD_LINE = new Color(122,54,23);

    Font title = new Font("Arial", Font.BOLD, 48);
    Font subtitle = new Font("Arial", Font.BOLD, 24);
    Font body = new Font("Arial", Font.PLAIN, 20);

    //buttons
    private JButton redFirstStartButton;
    private JButton blackFirstStartButton;
    private JButton returnToMenuButton;

    //Board Game

    private BoardGame boardGame = new BoardGame("red");
    public Screen() {
        setLayout(null);
        setBackground(BACK_GROUND_COLOR);
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

        g.setColor(BACK_GROUND_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());

        if (boardGame.getGameState()) {
            drawGameScreen(g, boardGame);
        } else {
            if (!boardGame.getWinStatus().equals("")) {
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
        button.setBackground(new Color(250, 224, 160));
        button.setForeground(BOARD_LINE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
		button.addActionListener(this);
		add(button);
		return button;
	}

    public void drawMain(Graphics g) {
        if (!boardGame.getGameState()) { 
            drawDecorativeBoard(g);
            g.setColor(BOARD_LINE);
            g.setFont(title);
            drawCenteredString(g, "Chinese Chess", 150);
            g.setFont(subtitle);
            drawCenteredString(g, "Xiangqi", 195);
            g.setFont(body);
            drawCenteredString(g, "Choose who moves first", 260);
            g.drawString("F1: Exit current game", 350, 580);
            showMenuButtons();
        }
    }

    private void drawGameScreen(Graphics g, BoardGame boardGame) {
        boardGame.drawGame(g,boardGame);
        showGameButtons();
    }

    //using this for end game screen and implementing in drawGame for win condition in BoardGame
    public void drawEnd(Graphics g) {
        drawDecorativeBoard(g);
        g.setColor(BOARD_LINE);
        g.setFont(title);

        if (boardGame.getWinStatus().equals("exited")) {
            drawCenteredString(g, "Game Exited", 260);
            g.setFont(body);
            drawCenteredString(g, "Return to the menu to start a new match.", 320);
            showEndButtons();
            return;
        }

        drawCenteredString(g, boardGame.getWinStatus() + "Wins", 250);
        g.setFont(subtitle);
        drawCenteredString(g, "General captured", 310);
        g.setFont(body);
        drawCenteredString(g, "Return to the menu to play again.", 360);
        showEndButtons();
    }

    private void drawDecorativeBoard(Graphics g) {
        g.setColor(BACK_GROUND_COLOR);
        g.fillRect(0, 0, 900, 700);

        g.setColor(new Color(250, 224, 160));
        g.fillRoundRect(240, 95, 420, 455, 20, 20);
        g.setColor(BOARD_LINE);
        g.drawRoundRect(240, 95, 420, 455, 20, 20);

        for (int i = 0; i < 7; i++) {
            int x = 285 + i * 55;
            g.drawLine(x, 135, x, 510);
        }

        for (int i = 0; i < 8; i++) {
            int y = 135 + i * 50;
            g.drawLine(285, y, 615, y);
        }
    }

    private void drawCenteredString(Graphics g, String text, int y) {
        java.awt.FontMetrics metrics = g.getFontMetrics();
        int x = (900 - metrics.stringWidth(text)) / 2;
        g.drawString(text, x, y);
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
        boardGame.handleClick(e.getX(), e.getY());
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
