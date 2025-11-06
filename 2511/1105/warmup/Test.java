public class Test {
    public void randomSquare() {
        int side = (int)(Math.random() * (10 - 5 + 1)); // Random number between 5 and 10
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    
    public void randomCount() {
        int countLimit = (int)(Math.random() * (20 - 10 + 1)); // Random number between 10 and 20
        for (int i = 1; i <= countLimit; i++) {
            System.out.println(i);
        }
    }
}
