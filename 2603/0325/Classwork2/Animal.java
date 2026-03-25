import java.awt.Color;
import java.awt.Graphics;

public class Animal {
    private String name;
    private static final Color ANIMAL_COLOR = new Color(198, 220, 242);
    private static final int BODY_WIDTH = 80;
    private static final int BODY_HEIGHT = 50;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void speak() {
        System.out.println("Hello, my name is " + name);
    }

    public void drawMe(Graphics g, int x, int y) {
        g.setColor(ANIMAL_COLOR);
        g.fillRect(x, y, BODY_WIDTH, BODY_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, BODY_WIDTH, BODY_HEIGHT);
    }
}
