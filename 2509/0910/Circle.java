import java.util.Scanner;

public class Circle {
    private Scanner scan = new Scanner(System.in);
    
    public void printCircumference() {
        System.out.print("Enter the radius of the circle: ");
        double radius = scan.nextDouble();
        double circumference = 2 * Math.PI * radius;
        circumference = Math.round(circumference * 100.0) / 100.0;
        System.out.println("Circumference: " + circumference);
    }

    public void printArea() {
        System.out.print("Enter the radius of the circle: ");
        double radius = scan.nextDouble();
        double area = Math.PI * Math.pow(radius, 2);
        area = Math.round(area * 100.0) / 100.0;
        System.out.println("Area: " + area);
    }
}
