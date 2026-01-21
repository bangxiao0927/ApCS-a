public class Runner {
    public static void main(String[] args) {
        // Instantiate ArrayTest
        ArrayTest arrayTest = new ArrayTest();
        
        // Call printWords()
        arrayTest.printWords();
        
        // Call swapWords(int, int) passing in 0 and 4
        arrayTest.swapWords(0, 4);
        
        // Call printWords() again to see the result of the swap
        arrayTest.printWords();
    }
}