import javax.swing.JFrame;

public class Runner {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pixel Art");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(560, 690);

        Screen screen = new Screen();
        frame.add(screen);

        frame.setResizable(false);
        frame.setVisible(true);
    }
}
