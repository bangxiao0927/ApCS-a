import java.util.ArrayList;

public class Runner
{
    public static void main(String[] args)
    {
        String[] players = {"Alex", "Ben", "Cara", "Drew", "Eli"};
        Round round = new Round(players);

        round.printCompetitors();

        ArrayList<Match> matches = round.buildMatches();
        for (Match match : matches)
        {
            System.out.println(match);
        }
    }
}
