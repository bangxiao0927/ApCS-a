import java.util.Scanner;

public class Hello {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your first name: ");
        String name = scan.next();
        System.out.println("Hello, " + name + "!");
        scan.nextLine();
        System.out.print("Enter your full name: ");
        name = scan.nextLine();
        System.out.println("Hello, " + name + "!");
    }
}
