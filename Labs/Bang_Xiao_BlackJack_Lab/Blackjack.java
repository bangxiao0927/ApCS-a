import java.util.Random;

public class Blackjack {
    private Card[] deck;         // 52 cards
    private int deckPos;         // next card index to deal

    private Card[] hand;         // player hand (max 52, but practically small)
    private int handSize;

    private boolean gameOver;
    private String endMessage;

    private int totalPoints;     // starts at 20
    private int lastWinPoints;   // points won in the last finished round

    private final Random rng = new Random();

    public Blackjack() {
        totalPoints = 20;
        buildDeck();
        hand = new Card[52];
        newGame();
    }

    public void newGame() {
        // cost 1 point to start a new game (even if it goes negative, depends on your teacher)
        totalPoints -= 1;

        buildDeck();
        shuffleDeck();

        handSize = 0;
        deckPos = 0;
        gameOver = false;
        endMessage = "";
        lastWinPoints = 0;

        // deal 2 cards
        dealOne();
        dealOne();

        // if already bust (unlikely), end
        if (handTotal() > 21) {
            endGameBust();
        }
    }

    private void buildDeck() {
        deck = new Card[52];
        String[] suits = {"Diamonds", "Hearts", "Spades", "Clubs"};
        String[] ranks = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
        int[] values   = { 2,  3,  4,  5,  6,  7,  8,  9,  10, 10, 10, 10, 11};

        int idx = 0;
        for (int s = 0; s < suits.length; s++) {
            for (int r = 0; r < ranks.length; r++) {
                deck[idx] = new Card(ranks[r], suits[s], values[r]);
                idx++;
            }
        }
    }

    private void shuffleDeck() {
        // Fisher-Yates
        for (int i = deck.length - 1; i > 0; i--) {
            int j = rng.nextInt(i + 1);
            Card tmp = deck[i];
            deck[i] = deck[j];
            deck[j] = tmp;
        }
    }

    private void dealOne() {
        if (deckPos < deck.length) {
            hand[handSize] = deck[deckPos];
            handSize++;
            deckPos++;
        }
    }

    public void hit() {
        if (gameOver) return;

        dealOne();

        int total = handTotal();
        if (total > 21) {
            endGameBust();
        }
    }

    public void stand() {
        if (gameOver) return;

        int total = handTotal();
        if (total < 16) {
            gameOver = true;
            lastWinPoints = 0;
            endMessage = "You lose: total is under 16 (" + total + ").";
        } else if (total > 21) {
            endGameBust();
        } else {
            int win = pointsWonForTotal(total);
            totalPoints += win;
            lastWinPoints = win;
            gameOver = true;
            endMessage = "You stand at " + total + " and win " + win + " point(s)!";
        }
    }

    private void endGameBust() {
        gameOver = true;
        lastWinPoints = 0;
        endMessage = "Bust! Total is " + handTotal() + " (over 21).";
    }

    public int pointsWonForTotal(int total) {
        if (total == 21) return 5;
        if (total == 20) return 3;
        if (total == 19) return 2;
        if (total == 18) return 2;
        if (total == 17) return 1;
        if (total == 16) return 1;
        return 0;
    }

    public int handTotal() {
        int sum = 0;
        for (int i = 0; i < handSize; i++) {
            sum += hand[i].getValue();
        }
        return sum;
    }

    
    public int getTotalPoints() { return totalPoints; }
    public int getLastWinPoints() { return lastWinPoints; }
    public boolean isGameOver() { return gameOver; }
    public String getEndMessage() { return endMessage; }
    public int getHandSize() { return handSize; }
    public Card getHandCard(int i) { return hand[i]; }
}
