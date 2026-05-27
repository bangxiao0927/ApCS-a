import javax.swing.JPanel;

public class Sprite extends JPanel {
    public static int rows = 10;
    public static int cols = 9;
    public static Sprite[][] xiangqiBoard = new Sprite[rows][cols];

    public int row;
    public int col;
    public boolean visible;
    public boolean redTeam;
    public boolean movingSoundOn;
    public boolean winningSoundOn;

    public Sprite() {
        this(0, 0, true);
    }

    public Sprite(int row, int col, boolean redTeam) {
        this.row = row;
        this.col = col;
        this.redTeam = redTeam;
        visible = true;
        movingSoundOn = false;
        winningSoundOn = false;

        if (isInsideBoard(row, col)) {
            xiangqiBoard[row][col] = this;
        }
    }

    public boolean isInsideBoard(int checkRow, int checkCol) {
        return checkRow >= 0 && checkRow < rows && checkCol >= 0 && checkCol < cols;
    }

    public void capture(Sprite other) {
        if (other == null) {
            return;
        }

        if (isInsideBoard(other.row, other.col) && xiangqiBoard[other.row][other.col] == other) {
            xiangqiBoard[other.row][other.col] = null;
        }
        other.visible = false;
    }

    public void animateMove(int newRow, int newCol) {
        if (!ruleDisplace(newRow, newCol)) {
            return;
        }

        playMovingSound();
        repaint();
    }

    public void playMovingSound() {
        movingSoundOn = true;
    }

    public void playWinningSound() {
        winningSoundOn = true;
    }

    public boolean ruleDisplace(int newRow, int newCol) {
        if (!isInsideBoard(newRow, newCol)) {
            return false;
        }

        Sprite target = xiangqiBoard[newRow][newCol];
        if (target != null) {
            if (target.redTeam == redTeam) {
                return false;
            }
            capture(target);
        }

        if (isInsideBoard(row, col) && xiangqiBoard[row][col] == this) {
            xiangqiBoard[row][col] = null;
        }

        row = newRow;
        col = newCol;
        xiangqiBoard[row][col] = this;
        return true;
    }
}
