
public class Triangle {
    private double base;
    private double height;

    public Triangle() {
        base = 0;
        height = 0;
    }

    public Triangle(double b, double h) {
        base = b;
        height = h;
    }

    public void printArea() {
        System.out.println("The area of the triangle is " + (0.5 * base * height));
    }

    public void printVariables() {
        System.out.println("base: " + base + "\nheight: " + height);
    }
}
