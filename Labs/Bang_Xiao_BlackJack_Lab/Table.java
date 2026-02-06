import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Table extends JPanel implements ActionListener {

    private static final int W = 1000;
    private static final int H = 600;

    private static final int BTN_Y = 520;
    private static final int HIT_X = 50;
    private static final int STAND_X = 160;
    private static final int AGAIN_X = 270;

    private static final int STAR_N = 28;

    private Blackjack game;

    private JButton hitButton;
    private JButton standButton;
    private JButton playAgainButton;

    private boolean showWin;
    private int frame;

    private Color[] winColors;
    private int[] starX;
    private int[] starY;
    private Random rnd;

    public Table() {
        game = new Blackjack();

        setLayout(null);

        hitButton = new JButton("Hit");
        hitButton.setBounds(HIT_X, BTN_Y, 100, 30);
        add(hitButton);
        hitButton.addActionListener(this);

        standButton = new JButton("Stand");
        standButton.setBounds(STAND_X, BTN_Y, 100, 30);
        add(standButton);
        standButton.addActionListener(this);

        playAgainButton = new JButton("Play Again");
        playAgainButton.setBounds(AGAIN_X, BTN_Y, 120, 30);
        add(playAgainButton);
        playAgainButton.addActionListener(this);

        initWinAnim();
    }

    public Dimension getPreferredSize() {
        return new Dimension(W, H);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        hitButton.setEnabled(!game.isGameOver());
        standButton.setEnabled(!game.isGameOver());

        if (showWin) {
            drawWin(g);
            return;
        }

        g.setColor(new Color(20, 100, 60));
        g.fillRect(0, 0, W, H);

        g.setColor(Color.WHITE);

        g.setFont(new Font("Arial", Font.BOLD, 22));
        g.drawString("Blackjack", 40, 40);

        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString("Hit = take card | Stand = finish | Bust over 21 | Lose under 16", 40, 65);

        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Card Total: " + game.handTotal(), 40, 100);
        g.drawString("Player Points: " + game.getTotalPoints(), 40, 125);
        g.drawString("Last Win: " + game.getLastWinPoints(), 40, 150);

        g.setFont(new Font("Arial", Font.PLAIN, 15));
        if (game.isGameOver()) {
            g.drawString("STATUS: " + game.getEndMessage(), 40, 175);
        } else {
            g.drawString("STATUS: Choose Hit or Stand", 40, 175);
        }

        drawCards(g);
        drawPointsTable(g);
    }

    public void drawCards(Graphics g) {
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Your Cards:", 40, 210);

        int x = 40;
        int y = 230;

        for (int i = 0; i < game.getHandSize(); i++) {
            Card c = game.getHandCard(i);

            g.setColor(new Color(0, 0, 0, 60));
            g.fillRect(x + 4, y + 4, 110, 150);

            g.setColor(Color.WHITE);
            g.fillRect(x, y, 110, 150);

            g.setColor(Color.BLACK);
            g.drawRect(x, y, 110, 150);

            boolean red = c.getSuit().equals("Hearts") || c.getSuit().equals("Diamonds");
            g.setColor(red ? new Color(180, 20, 20) : Color.BLACK);

            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString(c.getRank(), x + 8, y + 22);

            String symbol = suitSymbol(c.getSuit());

            g.setFont(new Font("Arial", Font.PLAIN, 18));
            g.drawString(symbol, x + 8, y + 42);

            g.setFont(new Font("Arial", Font.PLAIN, 50));
            g.drawString(symbol, x + 35, y + 90);

            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString(c.getRank(), x + 80, y + 138);

            x += 125;
            if (x > 650) {
                x = 40;
                y += 170;
            }
        }
    }

    public void drawPointsTable(Graphics g) {
        int x = 720;
        int y = 200;

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Winning Points Table", x, y);

        g.setFont(new Font("Arial", Font.PLAIN, 14));
        String[] rows = {
            "21 -> 5 points",
            "20 -> 3 points",
            "19 -> 2 points",
            "18 -> 2 points",
            "17 -> 1 point",
            "16 -> 1 point"
        };

        int yy = y + 25;
        for (String row : rows) {
            g.setColor(new Color(255, 255, 255, 40));
            g.fillRect(x - 6, yy - 16, 200, 22);

            g.setColor(Color.WHITE);
            g.drawString(row, x, yy);
            yy += 26;
        }
    }

    public String suitSymbol(String suit) {
        if (suit.equals("Diamonds")) return "\u2666";
        if (suit.equals("Hearts")) return "\u2665";
        if (suit.equals("Spades")) return "\u2660";
        return "\u2663";
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == hitButton) game.hit();
        if (e.getSource() == standButton) game.stand();
        if (e.getSource() == playAgainButton) game.newGame();

        frame++;
        updateWinState();
        repaint();
    }

    private void initWinAnim() {
        winColors = new Color[] {
            new Color(25, 15, 80),
            new Color(60, 10, 95),
            new Color(15, 70, 120),
            new Color(120, 30, 80)
        };

        starX = new int[STAR_N];
        starY = new int[STAR_N];
        rnd = new Random();

        resetStars();
    }

    private void updateWinState() {
        boolean won = game.isGameOver() && game.getLastWinPoints() > 0;
        if (won) {
            if (!showWin) {
                showWin = true;
                resetStars();
            }
        } else {
            showWin = false;
        }
    }

    private void resetStars() {
        for (int i = 0; i < STAR_N; i++) {
            starX[i] = rnd.nextInt(W);
            starY[i] = rnd.nextInt(H);
        }
    }

    private void drawWin(Graphics g) {
		int idx = (int) ((System.currentTimeMillis() / 200) % winColors.length); // current time in milliseconds for, update for each 200 ms

		g.setColor(winColors[idx]);
		g.fillRect(0, 0, W, H);

		g.setColor(Color.WHITE);
		for (int i = 0; i < STAR_N; i++) {
			int size = 10 + (i % 4) * 2;
			drawStar(g, starX[i], starY[i], size);
		}

		g.setFont(new Font("Arial", Font.BOLD, 56));
		g.drawString("CONGRATULATIONS!", 150, 240);

		g.setFont(new Font("Arial", Font.PLAIN, 28));
		g.drawString("Total: " + game.handTotal() + "  |  Points +" + game.getLastWinPoints(), 250, 290);

		repaint();
	}


    private void drawStar(Graphics g, int cx, int cy, int size) {
        int n = 10;
        int[] xs = new int[n];
        int[] ys = new int[n];
        double a = -Math.PI / 2;

        for (int i = 0; i < n; i++) {
            double r = (i % 2 == 0) ? size : size / 2.5;
            xs[i] = (int) (cx + Math.cos(a) * r);
            ys[i] = (int) (cy + Math.sin(a) * r);
            a += Math.PI / 5;
        }

        g.fillPolygon(xs, ys, n);
    }
}
