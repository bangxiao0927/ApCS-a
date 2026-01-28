public class Runner {
    public static void main(String[] args) {
        ArrayTest arrayTest = new ArrayTest(5);
        arrayTest.printArray();
        System.out.println();
        
        arrayTest.scramble();
        arrayTest.printArray();
        System.out.println();
        
        int searchResult = arrayTest.search(0);
        System.out.println("Search result for 0: " + searchResult);
        
        arrayTest.sort();
        arrayTest.printArray();
    }
}
