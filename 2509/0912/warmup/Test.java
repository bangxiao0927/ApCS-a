import java.util.Scanner;
public class Test {
    private double pi = 0;
    private int radius = 0;
    private Scanner scan = new Scanner(System.in);

    public void setVaribles() {
        System.out.println("Enter the radius of the circle: ");
        radius = scan.nextInt();
        pi = Math.PI;
    }
    public void printCircumference() {
        double circumference = 2 * pi * radius;
        System.out.println("The circumference of the circle is: " + circumference);
    }
}
