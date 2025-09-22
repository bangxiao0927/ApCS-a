public class Runner {
    public static void main(String[] args) {
        Circle c1 = new Circle();
        Circle c2 = new Circle(10.0);

        c1.printCylinder(5);
        c2.printCylinder(5);
    }
}
