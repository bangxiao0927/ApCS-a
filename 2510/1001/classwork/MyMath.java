import java.util.Scanner;

public class MyMath {
    private Scanner scanner = new Scanner(System.in);

    public void oddOrEven() {
        System.out.print("Enter a number: ");
        double number = scanner.nextDouble();
        if (number % 2 == 0) {
            int numberInt = (int) number;
            System.out.println(numberInt + " is even.");
        }
        else if (number % 1 != 0) {
            int numberInt = (int) number;
            System.out.println(numberInt + " is odd.");
        }
        else {
            System.out.println(number + " is not even or odd.");
        }
    }

    public void solveQuadratic() {
        System.out.print("Enter a: ");
        double a = scanner.nextDouble();

        System.out.print("Enter b: ");
        double b = scanner.nextDouble();

        System.out.print("Enter c: ");
        double c = scanner.nextDouble();

        double discriminant = b * b - 4 * a * c;
        if (discriminant > 0) {
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            System.out.println("Two real roots: " + root1 + " and " + root2);
        }
        else if (discriminant == 0) {
            double root = -b / (2 * a);
            System.out.println("One real root: " + root);
        }
        else {
            System.out.println("No real roots.");
        }
    }
}
