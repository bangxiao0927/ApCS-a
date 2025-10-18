public class Runner {
    public static void main(String[] args) {
        Number num = new Number();

        boolean isOdd = num.checkOdd(5);
        System.out.println("Is 5 odd? " + isOdd);

        double area = num.areaTriangle(4.5, 4.0);
        System.out.println("Area of triangle: " + area);
    }
}
