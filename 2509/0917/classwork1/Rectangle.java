
public class Rectangle {
    private double length;
    private double width;

    public Rectangle() {
        length = 0;
        width = 0;
    }

    public Rectangle(double l, double w) {
        length = l;
        width = w;
    }

    public void printArea() {
        System.out.println("The area of the rectangle is " + (length * width));
    }

    public void printVariables() {
        System.out.println("length: " + length + "\nwidth: " + width);
    }
}
