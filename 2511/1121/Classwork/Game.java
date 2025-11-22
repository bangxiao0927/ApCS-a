import java.awt.Graphics;


public class Game {
    private final Player p1;
	private final Target t1;


    public Game(){
        
        p1 = new Player(50,300);
        t1 = new Target(150,300);   
    }


    public void drawMe(Graphics g){
        p1.drawMe(g);
        t1.drawMe(g);
    }


    public void movePlayer(String direction){
        switch (direction) {
            case "up":
                p1.moveUp();
                break;
            case "down":
                p1.moveDown();
                break;
            case "left":
                p1.moveLeft();
                break;
            case "right":
                p1.moveRight();
                break;
            default:
                break;
        }

        p1.checkCollision(t1);
    }
}
