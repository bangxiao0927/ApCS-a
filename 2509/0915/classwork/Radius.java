public class Radius {
    private double pi = 3.14;

    public void printArea(double radius) {
        double area = pi * radius * radius;
        System.out.println("The area is: " + area);
    }

    public void printCir(double radius) {
        double cir = 2 * pi * radius;
        System.out.println("The circumference is: " + cir);
    }

    public void printConeVol(double radius, double height) {
        double vol = (1.0/3.0) * pi * radius * radius * height;
        System.out.println("The volume of the cone is: " + vol);
    }
}
