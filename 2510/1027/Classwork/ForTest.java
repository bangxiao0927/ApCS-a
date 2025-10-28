public class ForTest {
    public static void printCount() {
        for (int i = 1; i <= 100; i++) {
            System.out.println(i);
        }
    }

    public static void printCount(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.println(i);
        }
    }

    public static void printCount(int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.println(i);
        }
    }
    public static int getFactorial(int n) {
        int factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }
    public static void drawBox() {
        int size = 5; 
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
}