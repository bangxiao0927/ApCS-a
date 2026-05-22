package Projects.FourthQuaterProject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Sprite extends JPanel implements MouseListener {
    private static final int PANEL_WIDTH = 900;
    private static final int PANEL_HEIGHT = 700;

    private static final int XIANGQI_COLS = 9;
    private static final int XIANGQI_ROWS = 10;
    private static final int CELL_SIZE = 58;
    private static final int BOARD_X = 218;
    private static final int BOARD_Y = 90;

    private boolean showStartMenu;
    private Rectangle startButton;

    public Sprite() {
        showStartMenu = true;
        startButton = createStartButton();
        addMouseListener(this);
        setFocusable(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Xiangqi");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new Sprite());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                frame.setVisible(true);
            }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (showStartMenu) {
            drawStartMenu(g2);
        } else {
            drawXiangqiBoard(g2);
        }
    }

    private Rectangle createStartButton() {
        int buttonWidth = 220;
        int buttonHeight = 72;
        int x = (PANEL_WIDTH - buttonWidth) / 2;
        int y = 360;
        return new Rectangle(x, y, buttonWidth, buttonHeight);
    }

    private void drawStartMenu(Graphics2D g) {
        g.setColor(new Color(240, 223, 188));
        g.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);

        g.setColor(new Color(115, 49, 33));
        g.setFont(new Font("Serif", Font.BOLD, 52));
        drawCenteredText(g, "Xiangqi", PANEL_WIDTH / 2, 170);

        g.setFont(new Font("Serif", Font.PLAIN, 26));
        drawCenteredText(g, "Chinese Chess", PANEL_WIDTH / 2, 215);

        g.setColor(new Color(141, 61, 42));
        g.fillRoundRect(startButton.x, startButton.y, startButton.width, startButton.height, 24, 24);

        g.setColor(new Color(255, 245, 232));
        g.setFont(new Font("SansSerif", Font.BOLD, 30));
        drawCenteredText(g, "Start", PANEL_WIDTH / 2, startButton.y + 46);
    }

    private void drawXiangqiBoard(Graphics2D g) {
        int boardWidth = (XIANGQI_COLS - 1) * CELL_SIZE;
        int boardHeight = (XIANGQI_ROWS - 1) * CELL_SIZE;

        g.setColor(new Color(234, 208, 166));
        g.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);

        g.setColor(new Color(216, 184, 138));
        g.fillRoundRect(BOARD_X - 36, BOARD_Y - 36, boardWidth + 72, boardHeight + 72, 24, 24);

        g.setColor(new Color(88, 45, 30));
        g.setFont(new Font("Serif", Font.BOLD, 28));
        drawCenteredText(g, "9 x 10 Xiangqi Board", PANEL_WIDTH / 2, 48);

        for (int row = 0; row < XIANGQI_ROWS; row++) {
            int y = BOARD_Y + row * CELL_SIZE;
            g.drawLine(BOARD_X, y, BOARD_X + boardWidth, y);
        }

        for (int col = 0; col < XIANGQI_COLS; col++) {
            int x = BOARD_X + col * CELL_SIZE;
            if (col == 0 || col == XIANGQI_COLS - 1) {
                g.drawLine(x, BOARD_Y, x, BOARD_Y + boardHeight);
            } else {
                g.drawLine(x, BOARD_Y, x, BOARD_Y + 4 * CELL_SIZE);
                g.drawLine(x, BOARD_Y + 5 * CELL_SIZE, x, BOARD_Y + boardHeight);
            }
        }

        drawPalace(g, 0);
        drawPalace(g, 7);
        drawRiverText(g, boardWidth);
        drawStarPoints(g);
    }

    private void drawPalace(Graphics2D g, int startRow) {
        int leftX = BOARD_X + 3 * CELL_SIZE;
        int rightX = BOARD_X + 5 * CELL_SIZE;
        int topY = BOARD_Y + startRow * CELL_SIZE;
        int bottomY = BOARD_Y + (startRow + 2) * CELL_SIZE;

        g.drawLine(leftX, topY, rightX, bottomY);
        g.drawLine(rightX, topY, leftX, bottomY);
    }

    private void drawRiverText(Graphics2D g, int boardWidth) {
        g.setColor(new Color(120, 63, 42));
        g.setFont(new Font("Serif", Font.BOLD, 24));
        drawCenteredText(g, "楚 河", BOARD_X + boardWidth / 2, BOARD_Y + 4 * CELL_SIZE + 34);
        drawCenteredText(g, "漢 界", BOARD_X + boardWidth / 2, BOARD_Y + 5 * CELL_SIZE - 14);
        g.setColor(new Color(88, 45, 30));
    }

    private void drawStarPoints(Graphics2D g) {
        int[][] points = {
            {1, 2}, {7, 2}, {0, 3}, {2, 3}, {4, 3}, {6, 3}, {8, 3},
            {1, 7}, {7, 7}, {0, 6}, {2, 6}, {4, 6}, {6, 6}, {8, 6}
        };

        for (int[] point : points) {
            drawStar(g, BOARD_X + point[0] * CELL_SIZE, BOARD_Y + point[1] * CELL_SIZE);
        }
    }

    private void drawStar(Graphics2D g, int x, int y) {
        int size = 8;
        g.drawLine(x - size, y, x - 2, y);
        g.drawLine(x + 2, y, x + size, y);
        g.drawLine(x, y - size, x, y - 2);
        g.drawLine(x, y + 2, x, y + size);
    }

    private void drawCenteredText(Graphics g, String text, int centerX, int baselineY) {
        FontMetrics metrics = g.getFontMetrics();
        int x = centerX - metrics.stringWidth(text) / 2;
        g.drawString(text, x, baselineY);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (showStartMenu && startButton.contains(e.getPoint())) {
            showStartMenu = false;
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
