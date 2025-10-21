import java.util.Scanner;
public class MathLibraryRunner {
    public static void main(String[] args) {
        MathLibrary ml = new MathLibrary();
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter Radius: ");
        double r = keyboard.nextDouble();

        double ci = ml.circumference(r);
        System.out.println("Circumference: " + ci);
        double ac = ml.areaCircle(r);
        System.out.println("Area of circle: " + ac);
        double as = ml.volSphere(r);
        System.out.println("Volume of sphere: " + as);

        System.out.println("Enter Height: ");
        double h = keyboard.nextDouble();
        double volc = ml.volCylinder(r,h);
        System.out.println("Volume of cylinder: " + volc);
        double volco = ml.volCone(r,h);
        System.out.println("Volume of cone: " + volco);
    }
}


