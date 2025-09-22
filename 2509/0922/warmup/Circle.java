public class Circle {
    private double pi = 3.14;
    private double radius;

    public Circle() {
        this.pi = 3.14;
        this.radius = 0;
    }

    public Circle(double radius) {
        this.pi = 3.14;
        this.radius = radius;
    }

    public void printCylinder(double height) {
        double vol = pi * radius * radius * height;
        System.out.println("The volume of the cylinder is: " + vol);

    }
}
