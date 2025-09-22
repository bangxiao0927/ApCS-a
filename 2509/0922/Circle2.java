public class Circle2 {
    private double pi = 3.14;
    private double radius;

    public Circle2() {
        this.pi = 3.14;
        this.radius = 0;
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

    public void printArea(double r) {
        double area = pi * r * r;
        System.out.println("The area of the circle is: " + area);
    }

    public void printVol(double r) {
        double vol = pi * r * r * r * 1/3.0;
        System.out.println("The volume of the sphere is: " + vol);
    }

    public void printVol(double r,double height) {
        double vol = pi * r * r * height;
        System.out.println("The volume of the cylinder is: " + vol);
    }
}
