public class Runner
{
    public static void main(String[] args)
    {
    
        String[] wordList = {"apple", "pear", "this", "cat"};
        WordScrambler ws = new WordScrambler( wordList );
        
        System.out.println("Testing Part A");
        System.out.println("apple : pear -> " + ws.testPartA("apple", "pear"));
        System.out.println("pear : apple -> " + ws.testPartA("pear", "apple"));
        System.out.println();
        
        System.out.println("Testing Part B");
        ws.testPartB();
        
    }
}