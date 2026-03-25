import javax.swing.JPanel;
import java.awt.Graphics;

public class Screen extends JPanel {
    private Dog dog;
    private Cat cat;
    private Bird bird;

    public Screen() {
        dog = new Dog("Fido");
        cat = new Cat("Felix");
        bird = new Bird("Tweety");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int dogX = 25;
        int dogY = 80;
        dog.drawMe(g, dogX, dogY);
        String dogName = dog.getName();
        g.drawString(dogName, dogX, dogY - 10);

        int catX = 245;
        int catY = 90;
        cat.drawMe(g, catX, catY);
        String catName = cat.getName();
        g.drawString(catName, catX, catY - 10);

        int birdX = 460;
        int birdY = 80;
        bird.drawMe(g, birdX, birdY);
        String birdName = bird.getName();
        g.drawString(birdName, birdX, birdY - 10);
    }
}
