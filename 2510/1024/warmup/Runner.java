public class Runner {
    public static void main(String[] args) {
        Rectangle rect = new Rectangle();
        double volume = rect.volRect(5, 2, 10);
        System.out.println("Volume of the rectangle: " + volume);
    }
}