import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;


public class Screen extends JPanel implements KeyListener {


	private final Game game;


	public Screen(){


		game = new Game();
		
		addKeyListener(this);
		setFocusable(true);
	}

    @Override
	public Dimension getPreferredSize() {
		//Sets the size of the panel
        return new Dimension(800,600);
	}
	
    @Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
	
		game.drawMe(g);
	} 
	
	@Override
	public void keyPressed(KeyEvent e){
		
		System.out.println("key: " + e.getKeyCode());
            switch (e.getKeyCode()) {
                case 38:
                    //up arrow
                    game.movePlayer("up");
                    break;
                case 40:
                    game.movePlayer("down");
                    break;
                case 37:
                    game.movePlayer("left");
                    break;
                case 39:
                    game.movePlayer("right");
                    break;
                default:
                    break;
            }
		
		repaint();
		
	}
	
    @Override
	public void keyReleased(KeyEvent e){}
	@Override
	public void keyTyped(KeyEvent e){}
	
	
}
