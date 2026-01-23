public class Runner {
    public static void main(String[] args) {
        ArrayTest at = new ArrayTest();
        System.out.println("Before sorting: " + at.toString());
        at.sort();
        System.out.println("After sorting: " + at.toString());
    }
}