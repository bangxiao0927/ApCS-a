import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Table extends JPanel implements ActionListener, MouseListener {

	
	private JButton startButton;
	private JButton drawButton;
	private JButton attackButton;
	private JButton dodgeButton;
	private JButton takeHitButton;
	private JButton healButton;
	private JButton tacticButton;
	private JButton endTurnButton;
	private JButton switchPlayerButton;
	private CardGame cardGame;

	public Table() {

			cardGame = new CardGame();	

			setLayout(null);

			startButton = createButton("Start Battle", 320, 220, 160, 40);
			drawButton = createButton("Draw Card", 640, 360, 130, 30);
			attackButton = createButton("Play Sha", 640, 400, 130, 30);
			dodgeButton = createButton("Play Shan", 640, 440, 130, 30);
			takeHitButton = createButton("Take Hit", 640, 480, 130, 30);
			healButton = createButton("Use Peach", 500, 360, 130, 30);
			tacticButton = createButton("Battle Orders", 500, 400, 130, 30);
			endTurnButton = createButton("End Turn", 500, 440, 130, 30);
			switchPlayerButton = createButton("Switch View", 500, 480, 130, 30);

			addMouseListener(this);

			setFocusable(true);
			updateButtonStates();


	}
	
	public Dimension getPreferredSize() {
		//Sets the size of the panel
		return new Dimension(800,600);
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
			cardGame.playBattleOrders();
		} else if (src == endTurnButton) {
			cardGame.endTurn();
		} else if (src == switchPlayerButton) {
			cardGame.cycleView();
		}
		updateButtonStates();
		repaint();
	}

	private JButton createButton(String text, int x, int y, int width, int height) {
		JButton button = new JButton(text);
		button.setBounds(x, y, width, height);
		button.addActionListener(this);
		add(button);
		return button;
	}

	private void updateButtonStates() {
		CardGame.GameState state = cardGame.getGameState();
		boolean playing = state == CardGame.GameState.PLAYING;
		boolean awaiting = state == CardGame.GameState.AWAITING_DODGE;

		startButton.setVisible(state == CardGame.GameState.MENU || state == CardGame.GameState.GAME_OVER);
		drawButton.setEnabled(playing && !cardGame.hasDrawnThisTurn());
		attackButton.setEnabled(playing && cardGame.canActiveAttack());
		dodgeButton.setEnabled(awaiting && cardGame.canViewingPlayerDodge());
		takeHitButton.setEnabled(awaiting && cardGame.isViewingPlayerTargeted());
		healButton.setEnabled(cardGame.canViewingPlayerHeal());
		tacticButton.setEnabled(cardGame.canViewingPlayTactic());
		endTurnButton.setEnabled(cardGame.canEndTurn());
		switchPlayerButton.setEnabled(state != CardGame.GameState.MENU);
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

	
	
}
