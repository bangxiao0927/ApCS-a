import java.awt.Graphics;

public class Dog extends Animal {
    private static final int HEAD_WIDTH = 90;
    private static final int HEAD_HEIGHT = 40;

    public Dog(String name) {
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
        System.out.println("woof");
    }

    @Override
    public void drawMe(Graphics g, int x, int y) {
        super.drawMe(g, x, y);
        g.setColor(new java.awt.Color(198, 220, 242));
        g.fillOval(x - 10, y - 40, HEAD_WIDTH, HEAD_HEIGHT);
        g.setColor(java.awt.Color.BLACK);
        g.drawOval(x - 10, y - 40, HEAD_WIDTH, HEAD_HEIGHT);
    }
}
