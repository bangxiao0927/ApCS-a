import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Table extends JPanel implements ActionListener {

    private Blackjack game;

    private JButton hitButton;
    private JButton standButton;
    private JButton playAgainButton;

    public Table() {
        game = new Blackjack();

        hitButton = new JButton("Hit");
        hitButton.setBounds(50, 520, 100, 30);
        add(hitButton);
        hitButton.addActionListener(this);

        standButton = new JButton("Stand");
        standButton.setBounds(160, 520, 100, 30);
        add(standButton);
        standButton.addActionListener(this);

        playAgainButton = new JButton("Play Again");
        playAgainButton.setBounds(270, 520, 120, 30);
        add(playAgainButton);
        playAgainButton.addActionListener(this);
    }

    public Dimension getPreferredSize() {
        return new Dimension(1000, 600);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //background
        g.setColor(new Color(20, 100, 60));   // green felt
        g.fillRect(0, 0, 1000, 600);

        g.setColor(Color.WHITE);

        //title
        g.setFont(new Font("Arial", Font.BOLD, 22));
        g.drawString("Blackjack", 40, 40);

        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString("Hit = take card | Stand = finish | Bust over 21 | Lose under 16", 40, 65);

        
		
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Card Total: " + game.handTotal(), 40, 100);
        g.drawString("Player Points: " + game.getTotalPoints(), 40, 125);
        g.drawString("Last Win: " + game.getLastWinPoints(), 40, 150);

        //status
        g.setFont(new Font("Arial", Font.PLAIN, 15));

        if (game.isGameOver()) {
            g.drawString("STATUS: " + game.getEndMessage(), 40, 175);
        } else {
            g.drawString("STATUS: Choose Hit or Stand", 40, 175);
        }

        // gameover
        hitButton.setEnabled(!game.isGameOver());
        standButton.setEnabled(!game.isGameOver());

        // draw cards
        drawCards(g);

        //points
        drawPointsTable(g);
    }


    public void drawCards(Graphics g) {

        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Your Cards:", 40, 210);

        int x = 40;
        int y = 230;

        for (int i = 0; i < game.getHandSize(); i++) {

            Card c = game.getHandCard(i);

            // card shadow
            g.setColor(new Color(0, 0, 0, 60));
            g.fillRect(x + 4, y + 4, 110, 150);

            // card face
            g.setColor(Color.WHITE);
            g.fillRect(x, y, 110, 150);

            // border
            g.setColor(Color.BLACK);
            g.drawRect(x, y, 110, 150);

            // decide color by suit
            boolean red = false;
            if (c.getSuit().equals("Hearts") || c.getSuit().equals("Diamonds")) {
                red = true;
            }

            if (red) {
                g.setColor(new Color(180, 20, 20));
            } else {
                g.setColor(Color.BLACK);
            }

            // rank
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString(c.getRank(), x + 8, y + 22);

            // suit symbol
            String symbol = suitSymbol(c.getSuit());

            g.setFont(new Font("Arial", Font.PLAIN, 18));
            g.drawString(symbol, x + 8, y + 42);

            // big center suit
            g.setFont(new Font("Arial", Font.PLAIN, 50));
            g.drawString(symbol, x + 35, y + 90);

            // bottom right rank
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString(c.getRank(), x + 80, y + 138);

            // next pos
            x = x + 125;

            // next line
            if (x > 650) {
                x = 40;
                y = y + 170;
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

        for (int i = 0; i < rows.length; i++) {

            // simple box
            g.setColor(new Color(255, 255, 255, 40));
            g.fillRect(x - 6, yy - 16, 200, 22);

            g.setColor(Color.WHITE);
            g.drawString(rows[i], x, yy);

            yy = yy + 26;
        }
    }


    public String suitSymbol(String suit) {

        if (suit.equals("Diamonds")) {
            return "\u2666";
        }

        if (suit.equals("Hearts")) {
            return "\u2665";
        }

        if (suit.equals("Spades")) {
            return "\u2660";
        }

        return "\u2663";  // clubs
    }

   

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == hitButton) {
            game.hit();
        }

        if (e.getSource() == standButton) {
            game.stand();
        }

        if (e.getSource() == playAgainButton) {
            game.newGame();
        }

        repaint();
    }
}
