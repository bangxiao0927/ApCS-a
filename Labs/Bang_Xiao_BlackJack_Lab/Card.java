public class Card {
    private final String rank;   // "2".."10","J","Q","K","A"
    private final String suit;   // "Diamonds","Hearts","Spades","Clubs"
    private final int value;     // Ace fixed at 11 for this lab

    public Card(String rank, String suit, int value) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
    }

    public String getRank() { return rank; }
    public String getSuit() { return suit; }
    public int getValue() { return value; }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
