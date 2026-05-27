import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sprite extends JPanel {
    public static int maxRow = 10;
    public static int maxCol = 9;
    public static int rows = maxRow;
    public static int cols = maxCol;
    public static Sprite[][] xiangqiBoard = new Sprite[maxRow][maxCol];
    private static final int CELL_SIZE = 60;
    private static final int ANIMATION_STEPS = 12;
    private static final int ANIMATION_DELAY = 15;

    public int row;
    public int col;
    public String side;
    public String pieceType;
    public boolean visible;
    public boolean movingSoundEffect;
    public boolean winningSoundEffect;
    public boolean redTeam;
    public boolean movingSoundOn;
    public boolean winningSoundOn;
    public boolean captured;
    public boolean animating;
    public int drawX;
    public int drawY;

    public Sprite() {
        row = 0;
        col = 0;
        side = "";
        pieceType = "";
        visible = true;
        movingSoundEffect = false;
        winningSoundEffect = false;
        redTeam = true;
        movingSoundOn = false;
        winningSoundOn = false;
        captured = false;
        animating = false;
        drawX = col * CELL_SIZE;
        drawY = row * CELL_SIZE;
    }

    public Sprite(int startRow, int startCol, String startSide, String startPieceType) {
        row = startRow;
        col = startCol;
        side = startSide;
        pieceType = startPieceType;
        visible = true;
        movingSoundEffect = false;
        winningSoundEffect = false;
        redTeam = side.equals("red");
        movingSoundOn = false;
        winningSoundOn = false;
        captured = false;
        animating = false;
        drawX = col * CELL_SIZE;
        drawY = row * CELL_SIZE;

        if (isValidPosition(row, col)) {
            xiangqiBoard[row][col] = this;
        }
    }

    public Sprite(int startRow, int startCol, boolean startRedTeam) {
        this(startRow, startCol, startRedTeam ? "red" : "black", "");
    }

    public boolean isValidPosition(int checkRow, int checkCol) {
        return checkRow >= 0 && checkRow < maxRow && checkCol >= 0 && checkCol < maxCol;
    }

    public boolean isInsideBoard(int checkRow, int checkCol) {
        return isValidPosition(checkRow, checkCol);
    }

    public static void clearBoard() {
        xiangqiBoard = new Sprite[maxRow][maxCol];
    }

    public Sprite getSpriteAt(int checkRow, int checkCol) {
        if (!isValidPosition(checkRow, checkCol)) {
            return null;
        }
        return xiangqiBoard[checkRow][checkCol];
    }

    public boolean placeOnBoard(int newRow, int newCol) {
        if (!isValidPosition(newRow, newCol)) {
            return false;
        }

        if (isValidPosition(row, col) && xiangqiBoard[row][col] == this) {
            xiangqiBoard[row][col] = null;
        }

        row = newRow;
        col = newCol;
        drawX = col * CELL_SIZE;
        drawY = row * CELL_SIZE;
        xiangqiBoard[row][col] = this;
        visible = true;
        captured = false;
        redTeam = side.equals("red");
        return true;
    }

    public void capture(Sprite capturedPiece) {
        if (capturedPiece == null || capturedPiece == this) {
            return;
        }

        if (isValidPosition(capturedPiece.row, capturedPiece.col)
                && xiangqiBoard[capturedPiece.row][capturedPiece.col] == capturedPiece) {
            xiangqiBoard[capturedPiece.row][capturedPiece.col] = null;
        }

        capturedPiece.visible = false;
        capturedPiece.captured = true;
        capturedPiece.animating = false;

        if (isGeneral(capturedPiece)) {
            winningSound();
        }
    }

    public void captureAt(int captureRow, int captureCol) {
        if (isValidPosition(captureRow, captureCol)) {
            capture(xiangqiBoard[captureRow][captureCol]);
        }
    }

    public void movingAnimation(int newRow, int newCol) {
        if (!canDisplace(newRow, newCol)) {
            return;
        }

        final int startRow = row;
        final int startCol = col;
        final int startX = drawX;
        final int startY = drawY;
        final int endX = newCol * CELL_SIZE;
        final int endY = newRow * CELL_SIZE;

        animating = true;

        Timer timer = new Timer(ANIMATION_DELAY, null);
        timer.addActionListener(new ActionListener() {
            private int step = 0;

            public void actionPerformed(ActionEvent event) {
                step++;
                double percent = (double) step / ANIMATION_STEPS;
                drawX = startX + (int) Math.round((endX - startX) * percent);
                drawY = startY + (int) Math.round((endY - startY) * percent);
                repaint();

                if (step >= ANIMATION_STEPS) {
                    timer.stop();
                    animating = false;
                    row = startRow;
                    col = startCol;
                    displace(newRow, newCol, true);
                    drawX = endX;
                    drawY = endY;
                    repaint();
                }
            }
        });
        timer.start();
    }

    public void animateMove(int newRow, int newCol) {
        movingAnimation(newRow, newCol);
    }

    public void movingSound() {
        movingSoundEffect = true;
        movingSoundOn = true;
        Toolkit.getDefaultToolkit().beep();
    }

    public void playMovingSound() {
        movingSound();
    }

    public void winningSound() {
        winningSoundEffect = true;
        winningSoundOn = true;
        Toolkit.getDefaultToolkit().beep();
    }

    public void playWinningSound() {
        winningSound();
    }

    public boolean ruleDisplace(int newRow, int newCol) {
        return displace(newRow, newCol, true);
    }

    private boolean displace(int newRow, int newCol, boolean playSound) {
        if (!canDisplace(newRow, newCol)) {
            return false;
        }

        Sprite target = xiangqiBoard[newRow][newCol];
        if (target != null) {
            if (target.side.equals(side)) {
                return false;
            }
            capture(target);
        }

        if (isValidPosition(row, col) && xiangqiBoard[row][col] == this) {
            xiangqiBoard[row][col] = null;
        }

        row = newRow;
        col = newCol;
        xiangqiBoard[row][col] = this;
        drawX = col * CELL_SIZE;
        drawY = row * CELL_SIZE;
        if (playSound) {
            movingSound();
        }
        return true;
    }

    public String[] ruleDisplay() {
        return new String[] {
            "Xiangqi is played on a 9 by 10 board.",
            "A move must stay on the board.",
            "A piece cannot capture another piece on the same side.",
            "Moving onto an opponent piece captures it.",
            "Capturing a general triggers the winning sound effect."
        };
    }

    private boolean canDisplace(int newRow, int newCol) {
        if (!visible || captured || !isValidPosition(newRow, newCol)) {
            return false;
        }

        Sprite target = xiangqiBoard[newRow][newCol];
        return target == null || target == this || !target.side.equals(side);
    }

    private boolean isGeneral(Sprite piece) {
        return piece.pieceType != null
                && (piece.pieceType.equals("将")
                || piece.pieceType.equalsIgnoreCase("general")
                || piece.pieceType.equalsIgnoreCase("king"));
    }
}
