public class Runner {
    public static void main(String[] args) {
        ForTest test = new ForTest();
        test.printCount();
        test.printCount(10);
        test.printCount(5, 20);
        int factResult = test.getFactorial(5);
        System.out.println("The factorial of 5 is: " + factResult);
        test.drawBox();
    }
}