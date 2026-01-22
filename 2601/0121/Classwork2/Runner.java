public class Runner {
    public static void main(String[] args) {
        ArrayTest arrayTest2 = new ArrayTest();

        String[] words = {"banana", "apple", "orange", "grape", "pear"};

        System.out.println("Original array:");
        arrayTest2.printArray(words);

        arrayTest2.scramble(words);

        System.out.println("Scrambled array:");
        arrayTest2.printArray(words);

        arrayTest2.sort(words);

        System.out.println("Sorted array:");
        arrayTest2.printArray(words);
    }
}