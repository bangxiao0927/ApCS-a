public class Runner {
    public static void main(String[] args) {
        ForTest2 tester = new ForTest2();

        System.out.println("Print count from 1 to 10:");
        tester.printCount1(10);

        int sum1 = tester.getSum1(5);
        System.out.println("The sum from 1 to 5 using getSum1 is: " + sum1);

        System.out.println("Print count from 3 to 10:");
        tester.printCount2(3, 10);

        int sum2 = tester.getSum2(5);
        System.out.println("The sum from 1 to 5 using getSum2 is: " + sum2);
    }
}
