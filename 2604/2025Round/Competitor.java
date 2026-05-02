/** A single competitor in the tournament */
public class Competitor
{
    /** The competitor’s name and rank */
    private String name;
    private int rank;

    /**
    * Assigns n to name and initialRank to rank
    * Precondition: initialRank >= 1
    */
    public Competitor(String n, int initialRank)
    { 
        name = n;
        rank = initialRank;
    }

    public String toString(){ 
        return name + " (rank " + rank + ")";
    }

    /* implementation not shown */ 
    /* There may be instance variables, constructors,
    and methods that are not shown. */
}