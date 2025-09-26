import javax.swing.JFrame;

public class Runner {
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("classwork3");

        // Create the Circle panel
        Scenery s1 = new Scenery();

        // Add panel to frame and set up
        frame.add(s1); // add the thing
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//set the exit keybind
        frame.pack(); //pack the thing together
        frame.setVisible(true);//visible the thing
    }
}