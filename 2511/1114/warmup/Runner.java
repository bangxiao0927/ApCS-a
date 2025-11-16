import javax.swing.JFrame;

public class Runner {    public static void main(String[] args) {
        JFrame frame = new JFrame("Tree Screen");
        Screen screen = new Screen();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(screen);
        frame.setSize(800, 600); // Set the size of the window
        frame.setVisible(true);
    }
}
