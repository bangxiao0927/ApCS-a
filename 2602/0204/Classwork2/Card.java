public class Card {
    private int value;

    public Card(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "value: " + value;
    }

    public void drawMe(Graphics g, int x, int y) {
        g.drawRect(x, y, 50, 70); // Draw the card outline
        g.drawString(Integer.toString(value), x + 20, y + 35); // Draw the card value
    }
}
