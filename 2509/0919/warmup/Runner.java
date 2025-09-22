import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.printInfo();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter animal type: ");
        String type = scanner.nextLine();
        System.out.print("Enter animal age: ");
        int age = scanner.nextInt();

        Animal customAnimal = new Animal(type, age);
        customAnimal.printInfo();
    }
}
