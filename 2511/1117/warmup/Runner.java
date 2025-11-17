public class Runner {
    public static void main(String[] args) {

        // Create an array of size 5 with random numbers between 1 and 5 inclusive

        int[] numbers = new int[5];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (int)(Math.random() * 5) + 1;
        }

        Test test = new Test();

        test.print(numbers);

        int index = test.find(numbers, 3);
        System.out.println("Index of 3: " + index);

        int sum = test.getSummation(numbers);
        System.out.println("Sum of array: " + sum);

        int smallest = test.getSmallest(numbers);
        System.out.println("Smallest number in array: " + smallest);
    }
}