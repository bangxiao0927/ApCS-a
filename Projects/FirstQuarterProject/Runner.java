import java.util.Scanner;
import javax.swing.JFrame;

public class Runner {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        JFrame frame = new JFrame("first quarter project");

        Scenery s1 = new Scenery("day", "sunny", 1920, 1080);

        frame.add(s1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
