public class Runner {
    public static void main(String[] args) {
        SumOrSameGame game = new SumOrSameGame(3, 4);
        
        System.out.println("Testing Part A: ");
        System.out.println("The puzzle should be a 3 by 4 array of random integers between 1 and 9, inclusive.");
        game.printPuzzle();


        System.out.println("\nTesting Part B: ");
        
        boolean result = false;
        System.out.println("Test Case 1: ");
        game = new SumOrSameGame(1);
        game.printPuzzle();
        System.out.println("Clearing the pair (0,1).");
        result = game.clearPair(0, 1);
        System.out.println("Result: " + result);
        game.printPuzzle();
        System.out.println();


        System.out.println("Test Case 2: ");
        game = new SumOrSameGame(2);
        game.printPuzzle();
        System.out.println("Clearing the pair (2,2).");
        result = game.clearPair(2, 2);
        System.out.println("Result: " + result);
        game.printPuzzle();
        System.out.println();


        System.out.println("Test Case 3: ");
        game = new SumOrSameGame(3);
        game.printPuzzle();
        System.out.println("Clearing the pair (1,1).");
        result = game.clearPair(1, 1);
        System.out.println("Result: " + result);
        game.printPuzzle();
        System.out.println();




        System.out.println("Test Case 4: ");
        game = new SumOrSameGame(4);
        game.printPuzzle();
        System.out.println("Clearing the pair (0,2).");
        result = game.clearPair(0, 2);
        System.out.println("Result: " + result);
        game.printPuzzle();
        System.out.println();


       
    }
}
