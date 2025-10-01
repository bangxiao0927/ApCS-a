import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        MyMath myMath = new MyMath();
        
        System.out.println("Choose an option:");
        System.out.println("-------------------");
        System.out.println("1. Odd or Even");
        System.out.println("2. Solve Quadratic Equation");
        System.out.println("-------------------");
        System.out.print("Enter 1 or 2: ");
        int choice = keyboard.nextInt();
        if (choice == 1) {
            myMath.oddOrEven();
        }
        else if (choice == 2) {
            myMath.solveQuadratic();
        }
        else {
            System.out.println("Invalid choice.");
        }
    }
}
