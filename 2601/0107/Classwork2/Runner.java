public class Runner {
    public static void main(String[] args) {
        StringTest test = new StringTest("The quick brown fox jumps over the lazy dog");
        
        test.printInfo();
        
        test.printChar(0);
        test.printChar(5);
        test.printChar(10);
        test.printChar(15);
        
        test.printLocation("h");
        test.printLocation("e");
        test.printLocation("jumps");
        test.printLocation("dog");
        
        int countO = test.countChar('o');
        System.out.println("Number of 'o' characters: " + countO);
        
        System.out.println("Contains 'dog': " + test.contains("dog"));
        System.out.println("Contains 'java': " + test.contains("java"));
        
        int vowelCount = test.countVowels();
        System.out.println("Number of vowels: " + vowelCount);
    }
}