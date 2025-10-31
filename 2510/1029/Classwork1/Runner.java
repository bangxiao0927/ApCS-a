import java.util.Scanner;
public class Runner {
    public static void main(String[] args) {
        NestedLoops drawer = new NestedLoops();
        Scanner scanner = new Scanner(System.in);

        System.out.println("(10x10):");
        drawer.drawFilledBox();

        System.out.print("Enter width for filled box: ");
        int width = scanner.nextInt();

        System.out.print("Enter height for filled box: ");
        int height = scanner.nextInt();

        System.out.println("box:");
        drawer.drawFilledBox(width, height);

        System.out.println("Drawmultiplication chart:");
        drawer.drawMultiChart();

        System.out.println("Drawtriangle:");
        drawer.drawTriangle();
    }
}

