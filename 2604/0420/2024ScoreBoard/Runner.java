public class Runner {

public static void main(String[] args) {
        // create a ScoreBoard object
        ScoreBoard game = new ScoreBoard("Red", "Blue");
        
        System.out.println("Test ScoreBoard Class");
        System.out.println("\t getScore() = " + game.getScore());

        game.recordPlay(1);
        System.out.println("\t getScore() = " + game.getScore());

        game.recordPlay(0);
        System.out.println("\t getScore() = " + game.getScore());

        System.out.println("\t getScore() = " + game.getScore());

        game.recordPlay(3);
        System.out.println("\t getScore() = " + game.getScore());

        game.recordPlay(1);
        game.recordPlay(0);
        System.out.println("\t getScore() = " + game.getScore());

        game.recordPlay(0);
        game.recordPlay(4);
        game.recordPlay(0);
        System.out.println("\t getScore() = " + game.getScore());

        ScoreBoard match = new ScoreBoard("Lions", "Tigers");

        System.out.println("\t getScore() = " + match.getScore());

        System.out.println("\t getScore() = " + game.getScore());

}

}