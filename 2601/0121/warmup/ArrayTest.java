public class ArrayTest {
    // Instance Variables
    private String[] words;

    // Constructor
    public ArrayTest() {
        words = new String[]{"Lion", "Tiger", "Elephant", "Giraffe", "Zebra"};
    }

    // Methods
    public void printWords() {
        for (String word : words) {
            System.out.print(word + " ");
        }
        System.out.println(); // Print a new line at the end
    }

    public void swapWords(int index1, int index2) {
        if (index1 >= 0 && index1 < words.length && index2 >= 0 && index2 < words.length) {
            String temp = words[index1];
            words[index1] = words[index2];
            words[index2] = temp;
        } else {
            System.out.println("Invalid indices provided for swapping.");
        }
    }
}