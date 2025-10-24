public class Rectangle {
    private double area(double width, double length) {
        return width * length;
    }
    
    public double volRect(double width, double length, double height) {
        double area = area(width, length);
        return area * height;
    }
}
