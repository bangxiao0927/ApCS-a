public class Runner2 {
    public static void main(String[] args) {
        Circle2 c1 = new Circle2();
        Circle2 c2 = new Circle2(10.0);

        c1.printArea();
        c1.printArea(5);
        c2.printArea();
        c2.printArea(5);

        c2.printVol(7);
        c2.printVol(7,10);
    }
}
