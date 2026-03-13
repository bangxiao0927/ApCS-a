import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JPanel;

//table
public class Table extends JPanel implements ActionListener, MouseListener, KeyListener {

	
	private JButton startButton;
	private JButton drawButton;
	private JButton attackButton;
	private JButton dodgeButton;
	private JButton takeHitButton;
	private JButton healButton;
	private JButton tacticButton;
	private JButton endTurnButton;
	private JButton passButton;
	private JButton menuButton;
	private JButton twoPlayerButton;
	private JButton threePlayerButton;
	private CardGame cardGame;

	public Table() {

			cardGame = new CardGame();	

			setLayout(null);

			startButton = createButton("Start Battle", 440, 700, 200, 50);
			drawButton = createButton("Draw Card", 820, 300, 200, 40);
			attackButton = createButton("Play Sha", 820, 350, 200, 40);
			dodgeButton = createButton("Play Shan", 820, 400, 200, 40);
			takeHitButton = createButton("Take Hit", 820, 450, 200, 40);
			healButton = createButton("Use Peach", 820, 500, 200, 40);
			tacticButton = createButton("Use Tactic", 820, 550, 200, 40);
			endTurnButton = createButton("End Turn", 820, 600, 200, 40);
			passButton = createButton("Ready", 440, 560, 200, 50);
			menuButton = createButton("Return to Menu", 440, 620, 200, 50);
			twoPlayerButton = createButton("2 Players", 360, 740, 160, 45);
			threePlayerButton = createButton("3 Players", 560, 740, 160, 45);

			addMouseListener(this);
			addKeyListener(this);

			setFocusable(true);
			requestFocusInWindow();
			updateButtonStates();


	}
	
	public Dimension getPreferredSize() {
		//Sets the size of the panel
		return new Dimension(1080, 1080);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);

		cardGame.drawGame(g);
		
	}
	




	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src == startButton) {
			cardGame.startGame();
		} else if (src == drawButton) {
			cardGame.drawForActivePlayer();
		} else if (src == attackButton) {
			cardGame.playAttack();
		} else if (src == dodgeButton) {
			cardGame.playDodgeFromViewingPlayer();
		} else if (src == takeHitButton) {
			cardGame.resolveAttackWithoutDodge();
		} else if (src == healButton) {
			cardGame.playPeachFromViewingPlayer();
		} else if (src == tacticButton) {
			cardGame.playTacticFromViewingPlayer();
		} else if (src == endTurnButton) {
			cardGame.endTurn();
		} else if (src == passButton) {
			cardGame.finishPassing();
		} else if (src == menuButton) {
			cardGame.returnToMenu();
		} else if (src == twoPlayerButton) {
			cardGame.setPlayerCount(2);
		} else if (src == threePlayerButton) {
			cardGame.setPlayerCount(3);
		}
		updateButtonStates();
		repaint();
		requestFocusInWindow();
		setFocusable(true);
	}

	private JButton createButton(String text, int x, int y, int width, int height) {
		JButton button = new JButton(text);
		button.setBounds(x, y, width, height);
		button.setFocusable(false);
		button.addActionListener(this);
		add(button);
		return button;
	}

	private void updateButtonStates() {
		CardGame.GameState state = cardGame.getGameState();
		boolean playing = state == CardGame.GameState.PLAYING;
		boolean awaiting = state == CardGame.GameState.AWAITING_DODGE;
		boolean passing = state == CardGame.GameState.PASSING || state == CardGame.GameState.PASSING_RESPONSE;

		startButton.setVisible(state == CardGame.GameState.MENU || state == CardGame.GameState.GAME_OVER);
		startButton.setText(state == CardGame.GameState.GAME_OVER ? "Play Again" : "Start Battle");
		drawButton.setEnabled(playing && !cardGame.hasDrawnThisTurn());
		attackButton.setEnabled(playing && cardGame.canActiveAttack());
		dodgeButton.setEnabled(awaiting && cardGame.canViewingPlayerDodge());
		takeHitButton.setEnabled(awaiting && cardGame.isViewingPlayerTargeted());
		healButton.setEnabled(playing && cardGame.canViewingPlayerHeal());
		tacticButton.setEnabled(playing && cardGame.canViewingPlayTactic());
		endTurnButton.setEnabled(playing && cardGame.canEndTurn());
		passButton.setVisible(passing);
		passButton.setEnabled(passing);
		menuButton.setVisible(state == CardGame.GameState.GAME_OVER);
		menuButton.setEnabled(state == CardGame.GameState.GAME_OVER);
		twoPlayerButton.setVisible(state == CardGame.GameState.MENU);
		threePlayerButton.setVisible(state == CardGame.GameState.MENU);
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
		if (e.getKeyChar() == 'c' || e.getKeyChar() == 'C') {
			cardGame.applyCheatForViewingPlayer();
			updateButtonStates();
			repaint();
		} else if (e.getKeyChar() == 'p' || e.getKeyChar() == 'P') {
			cardGame.advanceCheatState();
			updateButtonStates();
			repaint();
		}
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}

	
	
}
