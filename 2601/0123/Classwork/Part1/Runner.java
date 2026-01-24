public class Runner
{
    public static void main(String[] args)
    {
        System.out.println("Part A");
        Phrase phrase1 = new Phrase("A cat ate late.");
        phrase1.replaceNthOccurrence("at", 1, "rane");
        System.out.println(phrase1);
        
        Phrase phrase2 = new Phrase("A cat ate late.");
        phrase2.replaceNthOccurrence("at", 6, "xx");
        System.out.println(phrase2);

        Phrase phrase3 = new Phrase("A cat ate late.");
        phrase3.replaceNthOccurrence("bat", 2, "xx");
        System.out.println(phrase3);
        
        Phrase phrase4 = new Phrase("aaaa");
        phrase4.replaceNthOccurrence("aa", 1, "xx");
        System.out.println(phrase4);
        
        Phrase phrase5 = new Phrase("aaaa");
        phrase5.replaceNthOccurrence("aa", 2, "bbb");
        System.out.println(phrase5);
        
        System.out.println("\nPart B");
        phrase1 = new Phrase("A cat ate late.");
        System.out.println( phrase1.findLastOccurrence("at") );
        System.out.println( phrase1.findLastOccurrence("cat") );
        System.out.println( phrase1.findLastOccurrence("bat") );
    }
}