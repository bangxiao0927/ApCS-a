public class Square {
    private double side;

    public Square() {
        side = 0;
    }

    public Square(double s) {
        side = s;
    }

    public void printArea() {
        System.out.println("The area of the square is " + (side * side));
    }

    public void printVariables() {
        System.out.println("side: " + side);
    }
}
