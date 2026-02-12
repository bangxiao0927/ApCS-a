import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Card {

   private int value;
   private String name;
   private String suit;
   private BufferedImage suitImage; 


   public Card(int value, String name, String suit){
		this.value = value;
		this.name = name;
		this.suit = suit;
		if( suit.equals("hearts") ){
			try{
				suitImage = ImageIO.read(new File("hearts.png"));
			} catch (IOException e) {}
		}
	 }

	 public int getValue(){
      return this.value;
   }


	 public void drawMe(Graphics g, int x, int y){

      //draw card outline
      g.setColor(Color.white);
      g.fillRect(x,y,120,150);
      
      g.setColor(Color.black);      
      g.drawRect(x,y,120,150);

      

      //draw suit
      g.drawImage(suitImage, x+2, y, null);
	   
      //Set Font to use with drawString   
      Font font = new Font("Arial", Font.PLAIN, 50);
      g.setFont(font);

      if( this.suit.equals("hearts") ){
         g.setColor(Color.red);
      }
      
      
      g.drawString(this.name+"", x+30, y + 100);

         
   }

   public String toString(){
      return name + " " + suit + ":" + value;
   }
   
}