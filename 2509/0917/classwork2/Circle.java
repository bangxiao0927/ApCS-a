
public class Circle {
    private double radius;
    private double pi;

    public Circle() {
        radius = 0;
        pi = 3.14;
    }

    public Circle(double r) {
        radius = r;
        pi = 3.14;
    }

    public void printArea() {
        System.out.println("The area of the circle is " + (pi * radius * radius));
    }
    
    public void printVariables() {
        System.out.println("radius: " + radius + "\npi: " + pi);
    }
}
