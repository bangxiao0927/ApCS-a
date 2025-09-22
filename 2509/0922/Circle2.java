public class Circle2 {
    private double pi = 3.14;
    private double radius;

    public Circle2() {
        this.pi = 3.14;
        this.radius = 1;
    }

    public Circle2(double r) {
        this.pi = 3.14;
        this.radius = r;
    }

    //method overloading

    public void printArea() {
        double area = pi * radius * radius;
        System.out.println("The area of the circle is: " + area);
    }

    public void printArea(double radius) {
        double area = pi * radius * radius;
        System.out.println("The area of the circle is: " + area);
    }

    public void printVol(double radius) {
        double vol = pi * radius * radius * radius * 1/3.0;
        System.out.println("The volume of the sphere is: " + vol);
    }

    public void printVol(double radius,double height) {
        double vol = pi * radius * radius * height;
        System.out.println("The volume of the cylinder is: " + vol);
    }
}
