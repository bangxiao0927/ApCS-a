import javax.swing.JFrame;

public class Runner {
    public static void main(String[] args) {
        JFrame frame = new JFrame("BlackJack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Table panel = new Table();
        frame.add(panel);

        frame.setSize(900, 600);
        frame.setVisible(true);
    }
}
