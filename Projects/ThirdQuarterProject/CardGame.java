
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

public class CardGame {
    private ArrayList<Card> deck;
	private ArrayList<Card> playersCard;

    public CardGame(){
        deck = new ArrayList<Card>();
        playersCard = new ArrayList<Card>();

        deck.add(new Card(2, "2", "hearts"));
        deck.add(new Card(3, "3", "hearts"));
        deck.add(new Card(4, "4", "hearts"));
        deck.add(new Card(5, "5", "hearts"));
        deck.add(new Card(10, "j", "hearts"));

        //shuffle

        //add 2 cards to the player from deck
        playersCard.add(deck.get(0));
        deck.remove(0);
        playersCard.add(deck.get(0));
        deck.remove(0);
    }

    public void drawGame(Graphics g){
        
		g.setColor(Color.green);
		g.fillRect(0,0,800,600);

		//draw player's card
		int x = 20;
		int y = 400;
		for(int i=0; i<playersCard.size(); i++){
			playersCard.get(i).drawMe(g,x,y);
			x += 130;
		}

	
		
		
	}

    public void getCard(){
        playersCard.add( deck.remove(0) );
    }



	
    
    public void shuffle(){
		//write code to shuffle your deck
	}
}