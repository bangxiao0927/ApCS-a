import java.util.Scanner;

public class Classwork1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int i = 1;
        while (i <= 12) {
            System.out.println(i);
            System.out.println();
            i++;
        }
        
        i = 5;
        while (i <= 31) {
            System.out.println();
            i++;
        }

        i = 39;
        while (i >= 21) {
            System.out.println(i);
            i--;
        }

        i = 2;
        while (i <= 20) {
            System.out.println();
            i += 2;
        }

        i = 15;
        while (i >= -10) {
            System.out.println();
            i -= 5;
        }

        i = 1;
        System.out.print("Enter a number: ");
        int number = input.nextInt();

        if (number > 0) {
            while (i <= number) {
                System.out.println(i);
                i++;
            }
        } else {
            System.out.println("The number is negative or 0.");
        }

        System.out.print("Enter a number less than 10: ");
        int numLessThan10 = input.nextInt();
        if (numLessThan10 < 10) {
            i = 50;
            while (i >= numLessThan10) {
                System.out.println(i);
                i--;
            }
        } else {
            System.out.println("The number is not less than 10.");
        }

        System.out.print("Enter a number greater than 20: ");
        int numGreaterThan20 = input.nextInt();
        System.out.print("Enter a count by number: ");
        int countBy = input.nextInt();
        if (numGreaterThan20 > 20) {
            i = 0;
            while (i <= numGreaterThan20) {
                System.out.println(i);
                i += countBy;
            }
        } else {
            System.out.println("The number is not greater than 20.");
        }

        System.out.print("Enter a number greater than 1: ");
        int numGreaterThan1 = input.nextInt();
        if (numGreaterThan1 > 1) {
            int sum = 0;
            i = 1;
            while (i <= numGreaterThan1) {
                sum += i;
                i++;
            }
            System.out.println("The summation is: " + sum);
        } else {
            System.out.println("The number is not greater than 1.");
        }
    }
}
