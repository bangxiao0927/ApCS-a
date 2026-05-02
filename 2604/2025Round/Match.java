/** A match between two competitors */
public class Match
{
    private Competitor competitor1;
    private Competitor competitor2;

    public Match(Competitor one, Competitor two)
    {
        competitor1 = one;
        competitor2 = two;
    }

    public String toString()
    {
        return competitor1 + " vs. " + competitor2;
    }

    /* There may be instance variables, constructors,
    and methods that are not shown. */
}
