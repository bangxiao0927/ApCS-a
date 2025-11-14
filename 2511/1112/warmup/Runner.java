
public class Runner {
    public static void main(String[] args) {
        ArrayTest arrayTest = new ArrayTest(10);
        arrayTest.print();
        
        boolean foundFive = arrayTest.search(5);
        System.out.println("Found 5: " + foundFive);
        
        boolean foundNine = arrayTest.search(9);
        System.out.println("Found 9: " + foundNine);
    }
}