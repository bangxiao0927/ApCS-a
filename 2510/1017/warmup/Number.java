public class Number {
    public boolean checkOdd(int num) {
        if (num > 0) {
            return num % 2 != 0;
        }
        else {
            System.out.println("Input must be positive.");
            return false;
        }
    }

    public double areaTriangle(double base, double height) {
        return 0.5 * base * height;
    }
}
