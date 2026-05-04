public class RobotMover
{
    private String moveSequence;

    /** For Testing Purposes */
    public RobotMover(String moveSequence)
    {
        this.moveSequence = moveSequence;
    }


    public String getMoveSequence()
    {
        return moveSequence;
    }

    /**
    * Initalizes moveSequence with numMoves moves, with an equal
    * chance of "up", "down", "left", and "right". Each move is
    * followed by an underscore ("_").
    * Precondition: numMoves > 0
    */
    public RobotMover(int numMoves)
    { /* to be implemented in part (a) */ 
        String[] chance = ["up", "down", "left", "right"];
        this.moveSequence = "";
        for(int i = 0; i < numMoves; i++) {
            int random = (int)(Math.random() * 4);
            moveSequence += chance[random] + "_";
        }

    }

    /**
    * Returns the number of times that str appears in moveSequence
    * Precondition: moveSequence is a valid sequence of moves,
    * each followed by an underscore.
    * Postcondition: moveSequence is unchanged.
    */
    public int countOccurrences(String str)
    { /* to be implemented in part (b) */ 
        if (moveSequence.contains(str)) {
            int count = 0;
            int i = moveSequence.indexOf(str);
            while(i != -1) {
                count++;
                i = moveSequence.indexOf(str, i + 1);
            }
            return count;
        }
        return 0;
    }

    /* There may be instance variables, constructors,
    and methods that are not shown. */
}
