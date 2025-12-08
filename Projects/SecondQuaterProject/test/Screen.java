import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Screen extends JPanel implements KeyListener, ActionListener {
    private Game game;
    private Timer timer;
    
    private boolean leftPressed;
    private boolean rightPressed;

    public Screen(){
        game = new Game();
        
        // Create timer for game loop (60 FPS)
        timer = new Timer(16, this);
        timer.start();
        
        leftPressed = false;
        rightPressed = false;
        
        addKeyListener(this);
        setFocusable(true);
    }

    public Dimension getPreferredSize() {
        return new Dimension(800,600);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.drawMe(g);
    } 
    
    @Override
    public void actionPerformed(ActionEvent e){
        // Game loop - update game state
        game.update();
        
        // Handle continuous key presses
        if(leftPressed){
            game.movePlayer("left");
        }
        if(rightPressed){
            game.movePlayer("right");
        }
        
        repaint();
    }
    
    public void keyPressed(KeyEvent e){
        if( e.getKeyCode() == 37 ){  // Left arrow
            leftPressed = true;
        } else if( e.getKeyCode() == 39 ){  // Right arrow
            rightPressed = true;
        } else if( e.getKeyCode() == 32 ){  // Spacebar
            game.restartGame();
        }
    }
    
    public void keyReleased(KeyEvent e){
        if( e.getKeyCode() == 37 ){  // Left arrow
            leftPressed = false;
        } else if( e.getKeyCode() == 39 ){  // Right arrow
            rightPressed = false;
        }
    }
    
    public void keyTyped(KeyEvent e){}
}