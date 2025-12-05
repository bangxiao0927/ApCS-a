import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Screen extends JPanel implements KeyListener {
    private Game game;

    public Screen(){
        game = new Game();
        
        addKeyListener(this);
        setFocusable(true);
    }

    public Dimension getPreferredSize() {
        //Sets the size of the panel
        return new Dimension(800,600);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    
        game.drawMe(g);
    } 
    
    
    public void keyPressed(KeyEvent e){
        
        System.out.println("key: " + e.getKeyCode());
        if( e.getKeyCode() == 38 ){  //up arrow
            game.movePlayer("up");	
        } else if( e.getKeyCode() == 40 ){  
            game.movePlayer("down");
        } else if( e.getKeyCode() == 37 ){  
            game.movePlayer("left");
        } else if( e.getKeyCode() == 39 ){  
            game.movePlayer("right");
        }
        
        repaint();
        
    }
    
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
    
    
}