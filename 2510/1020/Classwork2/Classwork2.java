import java.util.Scanner;

public class Classwork2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        int i = 8;
        while (i <= 30) {
            System.out.println(i);
            i += 2;
        }
        System.out.println();

        i = 8;
        do {
            System.out.println(i);
            i += 2;
        } while (i <= 30);
        System.out.println();

        int sum = 0;
        i = 2;
        while (i <= 19) {
            sum += i;
            i++;
        }
        System.out.println("Sum using while loop: " + sum);
        System.out.println();

        sum = 0;
        i = 2;
        do {
            sum += i;
            i++;
        } while (i <= 19);
        System.out.println("Sum using do-while loop: " + sum);
        System.out.println();

        System.out.print("Enter a number: ");
        int number = input.nextInt();
        i = 1;

        if (number > 0) {
            do {
                System.out.println(i);
                i++;
            } while (i <= number);
        } else {
            i = -1;
            do {
                System.out.println(i);
                i--;
            } while (i >= number);
        }
    }
}
