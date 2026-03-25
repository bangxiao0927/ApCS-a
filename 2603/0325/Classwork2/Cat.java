import java.awt.Graphics;

public class Cat extends Animal {
    private static final int HEAD_WIDTH = 90;
    private static final int HEAD_HEIGHT = 40;

    public Cat(String name) {
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
        System.out.println("meow");
    }

    @Override
    public void drawMe(Graphics g, int x, int y) {
        super.drawMe(g, x, y);
        int headX = x - 10;
        int headY = y - 40;

        g.setColor(new java.awt.Color(198, 220, 242));
        g.fillOval(headX, headY, HEAD_WIDTH, HEAD_HEIGHT);
        g.setColor(java.awt.Color.BLACK);
        g.drawOval(headX, headY, HEAD_WIDTH, HEAD_HEIGHT);

        g.drawLine(headX + 15, headY + 5, headX + 50, headY + 25);
        g.drawLine(headX + 90, headY + 5, headX + 55, headY + 25);
        g.drawLine(headX + 5, headY + 20, headX + 35, headY + 17);
        g.drawLine(headX + 80, headY + 17, headX + 100, headY + 30);
    }
}
