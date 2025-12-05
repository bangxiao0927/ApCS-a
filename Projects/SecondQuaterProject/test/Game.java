import java.awt.Graphics;
import java.awt.Color;


public class Game {
    private Player p1;
	private Target t1;


    public Game(){
        
        p1 = new Player(50,300);
        t1 = new Target(150,300);   
    }


    public void drawMe(Graphics g){
        p1.drawMe(g);
        t1.drawMe(g);
    }


    public void movePlayer(String direction){
        if(direction.equals("up")){
            p1.moveUp();
        } else if(direction.equals("down")){
            p1.moveDown();
        } else if(direction.equals("left")){
            p1.moveLeft();
        } else if(direction.equals("right")){
            p1.moveRight();
        }


    }
}
