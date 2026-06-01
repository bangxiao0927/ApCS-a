import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Sprite extends JPanel {
    public static int maxRow = 10;
    public static int maxCol = 9;
    public static int rows = maxRow;
    public static int cols = maxCol;
    public static Sprite[][] xiangqiBoard = new Sprite[maxRow][maxCol];
    private static final int CELL_SIZE = 60;
    private static final int ANIMATION_STEPS = 12;
    private static final int ANIMATION_DELAY = 15;
    private static final float SAMPLE_RATE = 44100.0f;
    private static final String[] APPLAUSE_SOUND_FILES = {
        "applause.wav",
        "plause.wav",
        "Projects/FourthQuaterProject/applause.wav",
        "Projects/FourthQuaterProject/plause.wav"
    };

    public int row;
    public int col;
    public String side;
    public String pieceType;
    public boolean visible;
    public boolean redTeam;
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
        redTeam = true;
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
        redTeam = side.equals("red");
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
        if (animating || !canDisplace(newRow, newCol)) {
            return;
        }

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

    public void winningSound() {
        playApplauseSound();
    }

    public boolean ruleDisplace(int newRow, int newCol) {
        return displace(newRow, newCol, true);
    }

    private boolean displace(int newRow, int newCol, boolean playSound) {
        if (!canDisplace(newRow, newCol)) {
            return false;
        }

        Sprite target = xiangqiBoard[newRow][newCol];
        boolean capturedGeneral = false;
        if (target != null) {
            if (target.side.equals(side)) {
                return false;
            }
            capturedGeneral = isGeneral(target);
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
        if (playSound && !capturedGeneral) {
            Toolkit.getDefaultToolkit().beep();
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

    public void drawXiangqiBoard(Graphics g) {
        Color boardColor = new Color(255, 226, 175);
        Color lineColor = new Color(122, 54, 23);
        Font riverFont = new Font("Arial", Font.BOLD, 28);

        int left = getBoardOffsetX() + CELL_SIZE / 2;
        int top = getBoardOffsetY() + CELL_SIZE / 2;
        int right = left + (maxCol - 1) * CELL_SIZE;
        int bottom = top + (maxRow - 1) * CELL_SIZE;
        int riverTop = top + 4 * CELL_SIZE;
        int riverBottom = top + 5 * CELL_SIZE;

        g.setColor(boardColor);
        g.fillRect(0, 0, 900, 700);

        g.setColor(lineColor);
        for (int row = 0; row < maxRow; row++) {
            int y = top + row * CELL_SIZE;
            g.drawLine(left, y, right, y);
        }

        for (int col = 0; col < maxCol; col++) {
            int x = left + col * CELL_SIZE;
            if (col == 0 || col == maxCol - 1) {
                g.drawLine(x, top, x, bottom);
            } else {
                g.drawLine(x, top, x, riverTop);
                g.drawLine(x, riverBottom, x, bottom);
            }
        }

        drawPalace(g, left, top);
        drawPalace(g, left, top + 7 * CELL_SIZE);

        g.setFont(riverFont);
        g.drawString("楚河", left + CELL_SIZE, riverTop + 42);
        g.drawString("汉界", left + 5 * CELL_SIZE, riverTop + 42);
    }

    protected int getBoardOffsetX() {
        return (900 - maxCol * CELL_SIZE) / 2;
    }

    protected int getBoardOffsetY() {
        return (700 - maxRow * CELL_SIZE) / 2;
    }

    protected int getCellSize() {
        return CELL_SIZE;
    }

    private void drawPalace(Graphics g, int left, int top) {
        int palaceLeft = left + 3 * CELL_SIZE;
        int palaceRight = left + 5 * CELL_SIZE;
        int palaceTop = top;
        int palaceBottom = top + 2 * CELL_SIZE;

        g.drawLine(palaceLeft, palaceTop, palaceRight, palaceBottom);
        g.drawLine(palaceRight, palaceTop, palaceLeft, palaceBottom);
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

    private void playApplauseSound() {
        Thread soundThread = new Thread(new Runnable() {
            public void run() {
                try {
                    File applauseFile = findApplauseSoundFile();
                    if (applauseFile != null) {
                        playAudioFile(applauseFile);
                    } else {
                        playGeneratedApplause();
                    }
                } catch (Exception e) {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        soundThread.setDaemon(true);
        soundThread.start();
    }

    private File findApplauseSoundFile() {
        for (String fileName : APPLAUSE_SOUND_FILES) {
            File file = new File(fileName);
            if (file.exists() && file.isFile()) {
                return file;
            }
        }
        return null;
    }

    private void playAudioFile(File soundFile) throws Exception {
        AudioInputStream stream = AudioSystem.getAudioInputStream(soundFile);
        Clip clip = AudioSystem.getClip();
        clip.open(stream);
        clip.start();
        Thread.sleep(Math.max(1, clip.getMicrosecondLength() / 1000));
        clip.close();
        stream.close();
    }

    private void playGeneratedApplause() throws LineUnavailableException, InterruptedException {
        AudioFormat format = new AudioFormat(SAMPLE_RATE, 8, 1, true, true);
        SourceDataLine line = AudioSystem.getSourceDataLine(format);
        line.open(format);
        line.start();

        for (int clap = 0; clap < 26; clap++) {
            playClap(line, 45 + (int) (Math.random() * 35), 0.75 + Math.random() * 0.25);
            Thread.sleep(12 + (int) (Math.random() * 45));
        }

        playCrowdTail(line, 420);
        line.drain();
        line.stop();
        line.close();
    }

    private void playClap(SourceDataLine line, int milliseconds, double volume) {
        int length = (int) (SAMPLE_RATE * milliseconds / 1000);
        byte[] data = new byte[length];
        double last = 0.0;
        double previous = 0.0;

        for (int i = 0; i < data.length; i++) {
            double progress = (double) i / data.length;
            double attack = Math.min(1.0, progress * 16.0);
            double decay = Math.pow(1.0 - progress, 2.2);
            double envelope = attack * decay;
            double noise = Math.random() * 2.0 - 1.0;

            last = last * 0.45 + noise * 0.55;
            double filtered = last - previous * 0.35;
            previous = last;

            if (i < 80) {
                filtered += (Math.random() * 2.0 - 1.0) * 0.9;
            }

            data[i] = clipToByte(filtered * 120.0 * envelope * volume);
        }

        line.write(data, 0, data.length);
    }

    private void playCrowdTail(SourceDataLine line, int milliseconds) {
        int length = (int) (SAMPLE_RATE * milliseconds / 1000);
        byte[] data = new byte[length];
        double last = 0.0;

        for (int i = 0; i < data.length; i++) {
            double progress = (double) i / data.length;
            double envelope = Math.pow(1.0 - progress, 1.6) * 0.32;
            double noise = Math.random() * 2.0 - 1.0;
            last = last * 0.72 + noise * 0.28;
            data[i] = clipToByte(last * 80.0 * envelope);
        }

        line.write(data, 0, data.length);
    }

    private byte clipToByte(double value) {
        if (value > 127.0) {
            return 127;
        }
        if (value < -128.0) {
            return -128;
        }
        return (byte) value;
    }

}
