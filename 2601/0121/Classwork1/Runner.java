import java.util.Scanner;
public class Runner {
    public static void main(String[] args) {
        ArrayTest2 arrayTest2 = new ArrayTest2();
        Scanner scanner = new Scanner(System.in);

        int[] nums = new int[10];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (int)(Math.random() * 10) + 1; // Random number between 1 and 10
        }

        System.out.println("Original array:");
        arrayTest2.printArray(nums);

        arrayTest2.scramble(nums);
        System.out.println("Scrambled array:");
        arrayTest2.printArray(nums);

        System.out.print("Enter a number to search for (1-10): ");
        int numberToSearch = scanner.nextInt();

        int searchResult = arrayTest2.search(numberToSearch, nums);
        if (searchResult != -1) {
            System.out.println("Number " + numberToSearch + " found at index: " + searchResult);
        } else {
            System.out.println("Number " + numberToSearch + " not found in the array.");
        }

        arrayTest2.sort(nums);
        System.out.println("Sorted array:");
        arrayTest2.printArray(nums);
    }
}