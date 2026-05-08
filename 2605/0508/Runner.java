public class Runner {
    public static void main(String[] args) {
        PlayerAnalysis analysis = new PlayerAnalysis();

        String closestPlayerID = analysis.playerWithClosestScore(1000);
        System.out.println("The player with the closest score to 1000 is: " + closestPlayerID);
        closestPlayerID = analysis.playerWithClosestScore(2000);
        System.out.println("The player with the closest score to 2000 is: " + closestPlayerID);
        closestPlayerID = analysis.playerWithClosestScore(3000);
        System.out.println("The player with the closest score to 3000 is: " + closestPlayerID);
        closestPlayerID = analysis.playerWithClosestScore(4000);
        System.out.println("The player with the closest score to 4000 is: " + closestPlayerID);
        closestPlayerID = analysis.playerWithClosestScore(5000);
        System.out.println("The player with the closest score to 5000 is: " + closestPlayerID);
    }
}