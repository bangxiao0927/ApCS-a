import javax.swing.JFrame;

public class Runner {
        public static void main(String[] args) {
        JFrame frame = new JFrame("Farm Scene");
        Screen panel = new Screen();
        frame.add(panel);
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}