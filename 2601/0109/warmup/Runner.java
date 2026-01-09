public class Runner {
    public static void main(String[] args) {
        StringReview stringReview = new StringReview();

        // Call getChar with index 2
        char charAtIndex2 = stringReview.getChar("Hello World!", 2);
        System.out.println("Character at index 2: " + charAtIndex2);

        // Call getChar with index 1
        char charAtIndex1 = stringReview.getChar("Hello World!", 1);
        System.out.println("Character at index 1: " + charAtIndex1);

        // Call countChar for character 'o'
        int countO = stringReview.countChar("Hello World!", 'o');
        System.out.println("Count of 'o': " + countO);

        // Call countChar for character 'l'
        int countL = stringReview.countChar("Hello World!", 'l');
        System.out.println("Count of 'l': " + countL);
    }
}