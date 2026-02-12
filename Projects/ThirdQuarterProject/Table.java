import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


public class Table extends JPanel implements ActionListener, MouseListener {

	
	private JButton getCard;
	private CardGame cardGame;

	public Table() {

			cardGame = new CardGame();	

			//setup buttons
			setLayout(null);

			getCard = new JButton("Get Card");
			getCard.setBounds(400,300,100,30); //x,y,width,height
			getCard.addActionListener(this);
			add(getCard);


			
			addMouseListener(this);

			setFocusable(true);


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
		if( e.getSource() == getCard ){
			
			//add a card to the playersDeck
			cardGame.getCard();

			//remove the top from the deck
			//call repaint to update the paintComponent
		}
		repaint();
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