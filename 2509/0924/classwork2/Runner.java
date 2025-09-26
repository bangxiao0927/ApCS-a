import javax.swing.JFrame;

public class Runner {
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Yellow Circle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the Circle panel
        Circle c1 = new Circle();

        // Add panel to frame and set up
        frame.add(c1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}