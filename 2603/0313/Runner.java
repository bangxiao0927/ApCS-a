import java.util.Scanner;

public class Runner{
    public static void main(String[] args) {
        Game game = new Game();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            game.printTable();
            System.out.print("Player 1 enter row and column: ");
            int row1 = scanner.nextInt();
            int col1 = scanner.nextInt();
            game.insertXO(row1, col1);
            game.printTable();

            int winner = game.checkTicTacToe();
            if (winner != 0) {
                System.out.println("Player " + winner + " wins!");
                break;
            }
            if (game.checkFull()) {
                break;
            }

            System.out.print("Player 2 enter row and column: ");
            int row2 = scanner.nextInt();
            int col2 = scanner.nextInt();
            game.insertXO(row2, col2);
            game.printTable();

            winner = game.checkTicTacToe();
            if (winner != 0) {
                System.out.println("Player " + winner + " wins!");
                break;
            }
            if (game.checkFull()) {
                break;
            }
        }

        scanner.close();
    }
}
