import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Table extends JPanel implements ActionListener {

    private Blackjack game;

    private JButton hitButton;
    private JButton standButton;
    private JButton playAgainButton;

    private static final int CARD_START_X = 50;
    private static final int CARD_START_Y = 240;
    private static final int CARD_SPACING_X = 130;
    private static final int CARD_SPACING_Y = 90;
    private static final int CARD_WRAP_LIMIT = 800;
    private static final int CARD_WIDTH = 110;
    private static final int CARD_HEIGHT = 70;

    private final Timer animationTimer;
    private boolean animatingCard;
    private int animatingIndex = -1;
    private float animationProgress;
    private int animationStartY;
    private int animationTargetY;
    private int animationX;

    public Table() {
        setLayout(null);

        // Create the game engine
        game = new Blackjack();

        // Buttons share the same setup; helper keeps the constructor tidy
        hitButton = createButton("Hit", 50, 500, 100, 30);
        standButton = createButton("Stand", 170, 500, 100, 30);
        playAgainButton = createButton("Play Again", 290, 500, 120, 30);

        animationTimer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stepAnimation();
            }
        });
    }

    private JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.addActionListener(this);
        add(button);
        return button;
    }

    private void stepAnimation() {
        if (!animatingCard) {
            animationTimer.stop();
            return;
        }

        animationProgress += 0.08f;
        if (animationProgress >= 1f) {
            animationProgress = 1f;
            animatingCard = false;
            animationTimer.stop();
        }

        repaint();
    }

    private void startCardAnimation(int cardIndex) {
        resetAnimation();
        Point position = getCardPosition(cardIndex);
        animationX = position.x;
        animationTargetY = position.y;
        animationStartY = Math.max(80, animationTargetY - 120);
        animatingIndex = cardIndex;
        animationProgress = 0f;
        animatingCard = true;
        animationTimer.start();
    }

    private void resetAnimation() {
        animatingCard = false;
        animatingIndex = -1;
        animationProgress = 0f;
        animationTimer.stop();
    }

    private Point getCardPosition(int index) {
        int x = CARD_START_X;
        int y = CARD_START_Y;

        for (int i = 0; i < index; i++) {
            x += CARD_SPACING_X;
            if (x > CARD_WRAP_LIMIT) {
                x = CARD_START_X;
                y += CARD_SPACING_Y;
            }
        }

        return new Point(x, y);
    }

    private void drawCard(Graphics g, Card card, int x, int y) {
        g.drawRect(x, y, CARD_WIDTH, CARD_HEIGHT);
        g.drawString(card.getRank(), x + 10, y + 22);
        g.drawString(card.getSuit(), x + 10, y + 42);
        g.drawString("Value: " + card.getValue(), x + 10, y + 62);
    }

    public Dimension getPreferredSize() {
        // Sets the size of the panel
        return new Dimension(1000, 600);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Background (optional)
        // g.setColor(new Color(30, 120, 30));
        // g.fillRect(0, 0, getWidth(), getHeight());
        // g.setColor(Color.BLACK);

        // ----- Title / Instructions -----
        Font originalFont = g.getFont();
        Font titleFont = originalFont.deriveFont(Font.BOLD, originalFont.getSize() + 4f);
        g.setFont(titleFont);
        g.drawString("Blackjack (1 Player)", 50, 35);
        g.setFont(originalFont);

        g.drawString("Hit = take a card. Stand = end the round. Bust if total > 21. Lose if total < 16.", 50, 60);

        // ----- Core Status -----
        g.drawString("Card Total: " + game.handTotal(), 50, 90);
        g.drawString("Total Points: " + game.getTotalPoints(), 50, 115);
        g.drawString("Last Win: " + game.getLastWinPoints(), 50, 140);

        // ----- Message -----
        if (game.isGameOver()) {
            g.drawString("STATUS: " + game.getEndMessage(), 50, 175);
        } else {
            g.drawString("STATUS: Choose Hit or Stand", 50, 175);
        }

        // ----- Disable hitting/standing after game ends -----
        hitButton.setEnabled(!game.isGameOver());
        standButton.setEnabled(!game.isGameOver());

        // ----- Draw Cards -----
        g.drawString("Your Cards:", 50, 220);

        for (int i = 0; i < game.getHandSize(); i++) {
            Card c = game.getHandCard(i);
            Point position = getCardPosition(i);

            if (animatingCard && i == animatingIndex) {
                int animatedY = (int) (animationStartY + (animationTargetY - animationStartY) * animationProgress);
                drawCard(g, c, animationX, animatedY);
            } else {
                drawCard(g, c, position.x, position.y);
            }
        }

        // ----- Winning Table (Graphical) -----
        int tableX = 700;
        int tableY = 220;

        g.drawString("Winning Points Table", tableX, tableY);
        String[] rows = {
            "21  →  5 points",
            "20  →  3 points",
            "19  →  2 points",
            "18  →  2 points",
            "17  →  1 point",
            "16  →  1 point"
        };

        int boxY = tableY + 20;
        for (int i = 0; i < rows.length; i++) {
            g.drawRect(tableX, boxY - 15, 200, 22);
            g.drawString(rows[i], tableX + 10, boxY);
            boxY += 26;
        }
    }

    public void actionPerformed(ActionEvent e) {
        // When a button gets pressed, this method gets called

        if (e.getSource() == hitButton) {
            int previousHandSize = game.getHandSize();
            game.hit();
            if (game.getHandSize() > previousHandSize) {
                startCardAnimation(game.getHandSize() - 1);
            }
        } 
        else if (e.getSource() == standButton) {
            game.stand();
        } 
        else if (e.getSource() == playAgainButton) {
            resetAnimation();
            game.newGame();
        }

        // refresh the screen
        repaint();
    }
}
