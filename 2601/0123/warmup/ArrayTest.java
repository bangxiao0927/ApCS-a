public class ArrayTest {
    // Instance variable
    private static int[] nums;

    // Constructor
    public ArrayTest() {
        nums = new int[10];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (int)(Math.random() * 99) + 1; // Random number between 1 and 99 inclusive
        }
    }

    // Method to create a string representation of the array
    public String toString() {
        String result = "";
        for (int num : nums) {
            result += num + " ";
        }
        return result;
    }

    // Method to sort the nums array using bubble sort
    public void sort() {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    // Swap nums[j] and nums[j+1]
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
    }
}