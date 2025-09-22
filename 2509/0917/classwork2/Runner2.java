import java.util.Scanner;

public class Runner2 {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("======================");
        System.out.println("Select a shape:");
        System.out.println("1. Square");
        System.out.println("2. Rectangle");
        System.out.println("3. Triangle");
        System.out.println("4. Circle");
        System.out.println("======================");
        System.out.print("Enter your selection: ");
        int selection = keyboard.nextInt();


        if(selection == 1) {
            System.out.println("Enter in a side: ");
            double side = keyboard.nextDouble();
            Square sq = new Square(side);
            sq.printArea();
        }
        else if(selection == 2) {
            System.out.println("Enter in a length: ");
            double length = keyboard.nextDouble();
            System.out.println("Enter in a width: ");
            double width = keyboard.nextDouble();
            Rectangle rect = new Rectangle(length, width);
            rect.printArea();
        }
        else if(selection == 3) {
            System.out.println("Enter in a base: ");
            double base = keyboard.nextDouble();
            System.out.println("Enter in a height: ");
            double height = keyboard.nextDouble();
            Triangle tri = new Triangle(base, height);
            tri.printArea();
        }
        else if(selection == 4) {
            System.out.println("Enter in a radius: ");
            double radius = keyboard.nextDouble();
            Circle cir = new Circle(radius);
            cir.printArea();
        }
        else {
            System.out.println("Invalid selection.");
        }
    }
}
