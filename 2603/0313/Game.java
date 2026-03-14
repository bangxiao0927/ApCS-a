public class Game {
    private int[][] table;
    private int turn;

    public Game() {
        table = new int[3][3];
        turn = 1;
    }

    public void printTable() {
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[row].length; col++) {
                int value = table[row][col];
                if (value == 1) {
                    System.out.print("X");
                } else if (value == 2) {
                    System.out.print("O");
                } else {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }

    public void insertXO(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && table[row][col] == 0) {
            table[row][col] = turn;
        }

        if (turn == 1) {
            turn = 2;
        } else {
            turn = 1;
        }
    }

    public boolean checkFull() {
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[row].length; col++) {
                if (table[row][col] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public int checkTicTacToe() {
        for (int row = 0; row < 3; row++) {
            if (table[row][0] != 0
                    && table[row][0] == table[row][1]
                    && table[row][1] == table[row][2]) {
                return table[row][0];
            }
        }

        for (int col = 0; col < 3; col++) {
            if (table[0][col] != 0
                    && table[0][col] == table[1][col]
                    && table[1][col] == table[2][col]) {
                return table[0][col];
            }
        }

        if (table[0][0] != 0
                && table[0][0] == table[1][1]
                && table[1][1] == table[2][2]) {
            return table[0][0];
        }

        if (table[0][2] != 0
                && table[0][2] == table[1][1]
                && table[1][1] == table[2][0]) {
            return table[0][2];
        }

        return 0;
    }
}
