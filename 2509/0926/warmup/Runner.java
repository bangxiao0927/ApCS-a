public class Runner {
    public static void main(String[] args) {
        One obj1 = new One();
        obj1.printArea();
        One obj2 = new One(5, 2);
        obj2.printArea();
        obj2.printArea(6, 2);
    }
}
