public class Runner {
    public static void main(String[] args) {
        MyMath math = new MyMath();
        math.countDown(5);
        math.countUp(10);
        System.out.println(math.factorial(5));
        System.out.println(math.summation(5));
    }
}
