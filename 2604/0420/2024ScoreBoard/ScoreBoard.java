public class ScoreBoard {
    private String team1;
    private String team2;
    private int team1Score;
    private int team2Score;
    private boolean team1Turn;

    public ScoreBoard(String firstTeam, String secondTeam) {
        team1 = firstTeam;
        team2 = secondTeam;
        team1Score = 0;
        team2Score = 0;
        team1Turn = true;
    }

    public void recordPlay(int points) {
        if (team1Turn) {
            team1Score += points;
        } else {
            team2Score += points;
        }

        if (points == 0) {
            team1Turn = !team1Turn;
        }
    }

    public String getScore() {
        if (team1Turn) {
            return team1Score + "-" + team2Score + "-" + team1;
        }

        return team1Score + "-" + team2Score + "-" + team2;
    }
}
