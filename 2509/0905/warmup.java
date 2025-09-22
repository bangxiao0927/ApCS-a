import java.util.Scanner;

public class warmup {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter the radius: ");
        double radius = keyboard.nextDouble();
        System.out.println("Enter the height: ");
        double height = keyboard.nextDouble();
        double volume = Math.PI * Math.pow(radius, 2) * height;
        System.out.println("The volume of the cylinder is: " + volume);

        System.out.println("Where is New York?");
        keyboard.nextLine();
        String location = keyboard.nextLine();
        if(location.equals("In New York State")) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect");
        }
    }
}
