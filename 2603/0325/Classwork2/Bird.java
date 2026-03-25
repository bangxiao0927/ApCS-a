import java.awt.Graphics;

public class Bird extends Animal {
    public Bird(String name) {
        super(name);
    }

    public void printInfo() {
        super.speak();
        System.out.println(getName());
        System.out.print("I make the sound ");
        this.speak();
    }

    @Override
    public void speak() {
        System.out.println("tweet");
    }

    @Override
    public void drawMe(Graphics g, int x, int y) {
        int bodyX = x + 20;
        int bodyY = y;
        int bodyWidth = 60;
        int bodyHeight = 85;
        int wingX = x - 10;
        int wingY = y + 10;
        int wingWidth = 150;
        int wingHeight = 45;

        g.setColor(new java.awt.Color(198, 220, 242));
        g.fillRect(bodyX, bodyY, bodyWidth, bodyHeight);
        g.fillOval(wingX, wingY, wingWidth, wingHeight);
        g.setColor(java.awt.Color.BLACK);
        g.drawRect(bodyX, bodyY, bodyWidth, bodyHeight);
        g.drawOval(wingX, wingY, wingWidth, wingHeight);
    }
}
