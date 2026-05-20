public class Player
{
    private String id;
    private int score;


    public Player(String id, int score)
    { /* implementation not shown */ 
        this.id = id;
        this.score = score;
    }


    /**
    * Returns a unique ID for the player
    */
    public String getID()
    { /* implementation not shown */ 
        return id;
    }

    
    /**
    * Returns the player’s score in the game,
    * guaranteed to be nonnegative
    */
    public int getScore()
    { /* implementation not shown */ 
        return score;
    }


    /* There may be instance variables, constructors, and methods
    that are not shown. */
}