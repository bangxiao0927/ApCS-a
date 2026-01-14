public class Runner {
    public static void main(String[] args) {
        ArrayTest at = new ArrayTest();

        System.out.println("Number Array:");
        at.printNumArray();

        at.swapNumArray(0, 9);
        System.out.println("After swapping index 0 and 9:");
        at.printNumArray();

        System.out.println("String Array:");
        at.printStringArray();

        at.swapStringArray(1, 2);
        System.out.println("After swapping index 1 and 2:");
        at.printStringArray();

        String searchWord = "elephant";
        int searchResult = at.searchString(searchWord);
        System.out.println("Searching for '" + searchWord + "': Index " + searchResult);

        char letterE = 'e';
        int countE = at.countLetter(letterE);
        System.out.println("Count of letter '" + letterE + "': " + countE);

        char letterA = 'a';
        int countA = at.countLetter(letterA);
        System.out.println("Count of letter '" + letterA + "': " + countA);
    }
}