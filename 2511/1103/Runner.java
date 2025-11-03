import java.util.Random;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Random rand = new Random();

        // random numbers from 0 to 19
        for (int i = 0; i < 5; i++) {
            int num = rand.nextInt(20); // 0 to 19
            System.out.println(num);
        }

        // random numbers from 5 to 10
        for (int i = 0; i < 5; i++) {
            int num = rand.nextInt(6) + 5; // 5 to 10
            System.out.println(num);
        }

        // random numbers from 15 to 50
        for (int i = 0; i < 5; i++) {
            int num = rand.nextInt(36) + 15; // 15 to 50
            System.out.println(num);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number greater than 10: ");
        int upperLimit = scanner.nextInt();

        // random numbers from 1 to upperLimit
        for (int i = 0; i < 5; i++) {
            int num = rand.nextInt(upperLimit) + 1; // 1 to upperLimit
            System.out.println(num);
        }

        // rolling two dice
        for (int i = 0; i < 10; i++) {
            int die1 = rand.nextInt(6) + 1; // 1 to 6
            int die2 = rand.nextInt(6) + 1; // 1 to 6
            System.out.println("Die 1: " + die1 + ", Die 2: " + die2);

            if (die1 == 1 && die2 == 1) {
                System.out.println("snake eyes");
            }
            if (die1 == die2) {
                System.out.println("same");
            }
            if ((die1 + die2) % 2 == 0) {
                System.out.println("even sum");
            }
        }
    }
}
