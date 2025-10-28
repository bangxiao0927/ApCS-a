public class Runner {
    public static void main(String[] args) {
        Count counter = new Count();
        int result = counter.getTotal(9);
        System.out.println("The total is: " + result);
    }
}
