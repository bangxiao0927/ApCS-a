import java.util.Scanner;
import javax.swing.JFrame;

public class Runner {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //user input

        //day or night
        System.out.println("Enter time of day (day/night): ");
        String dayOrNight = scan.nextLine();
        if (!dayOrNight.equals("day") && !dayOrNight.equals("night")) {
            System.out.println("Invalid input. Please enter 'day' or 'night'.");
            return;
        }

        System.out.println("Enter weather (sunny/rainy/cloudy): ");
        String weather = scan.nextLine();
        if (!weather.equals("sunny") && !weather.equals("rainy") && !weather.equals("cloudy")) {
            System.out.println("Invalid input. Please enter 'sunny', 'rainy', or 'cloudy'.");
            return;
        }

        if (dayOrNight.equals("night") && weather.equals("sunny")) {
            System.out.println("Invalid combination: 'sunny' weather cannot occur at 'night'.");
            return;
        }

        //screen size
        System.out.println("Please enter the width of the window(0 for default 1920): ");
        int width = scan.nextInt();
        if (width == 0) {
            width = 1920;
        }
        System.out.println("Please enter the height of the window(0 for default 1080): ");
        int height = scan.nextInt();
        if (height == 0) {
            height = 1080;
        }

        System.out.println("Generating scenery...");
        
        //show the scenery
        JFrame frame = new JFrame("first quarter project");

        //the size of the window could be change because the scenery will adapt to different size and relative ratio
        Scenery s1 = new Scenery(dayOrNight, weather, width, height);

        frame.add(s1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
