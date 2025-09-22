import java.util.Scanner;

public class AnimalRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Animals animal = new Animals();

        System.out.println("Enter in an animal type: ");
        String type = scanner.next();
        System.out.println("Enter in an animal age: ");
        int age = scanner.nextInt();
        animal.setVariables(type, age);
        animal.printInfo();
    }
}
