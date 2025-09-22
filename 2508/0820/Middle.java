public class Middle {
    public static void main(String[] args) {
        int numOne = 5;
        int numTwo = 10;
        System.out.println("numOne = " + numOne);
        System.out.println("numTwo = " + numTwo);

        int middle1 = (numOne + numTwo) / 2;
        System.out.println("middle1 : " + middle1);

        double middle2 = (numOne + numTwo) / 2.0;
        System.out.println("middle2 : " + middle2);

        double toAdd1 = 3.0;
        double toAdd2 = 4.0;
        double toAdd3 = 5.0;
        double average = (toAdd1 + toAdd2 + toAdd3) / 3.0;
        System.out.println("average : " + average);
    }
}
