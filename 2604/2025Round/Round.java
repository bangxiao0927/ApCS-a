/** A single round of the tournament */
import java.util.ArrayList;

public class Round
{
    /** The list of competitors participating in this round */
    private ArrayList<Competitor> competitorList;

    /** Initializes competitorList, as described in part (a) */
    public Round(String[] names)
    {
        competitorList = new ArrayList<Competitor>();

        for (int i = 0; i < names.length; i++)
        {
            competitorList.add(new Competitor(names[i], i + 1));
        }
    }

    /**
    * Creates an ArrayList of Match objects for the next round
    * of the tournament, as described in part (b)
    * Preconditions: competitorList contains at least one element.
    * competitorList is ordered from best to worst rank.
    * Postcondition: competitorList is unchanged.
    */
    public ArrayList<Match> buildMatches()
    {
        ArrayList<Match> matches = new ArrayList<Match>();
        int left = competitorList.size() % 2;
        int right = competitorList.size() - 1;

        while (left < right)
        {
            matches.add(new Match(competitorList.get(left), competitorList.get(right)));
            left++;
            right--;
        }

        return matches;
    }

    public void printCompetitors()
    {
        System.out.println(competitorList);
    }

    /* There may be instance variables, constructors,
    and methods that are not shown. */
}
