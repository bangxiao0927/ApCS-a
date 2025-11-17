public class Runner {
    public static void main(String[] args) {
        ArrayTest at = new ArrayTest();
        int[] nums = new int[10];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (int)(Math.random() * 9) + 1;
        }
        
        at.printArray(nums);
        
        boolean foundFive1 = at.searchNumber1(5, nums);
        System.out.println("Search Number 1 found 5: " + foundFive1);
        
        int foundFive2 = at.searchNumber2(5, nums);
        System.out.println("Search Number 2 found 5: " + foundFive2);
        
        int largest = at.findLargest(nums);
        System.out.println("Largest number: " + largest);
        
        int smallest = at.findSmallest(nums);
        System.out.println("Smallest number: " + smallest);
        
        int sum = at.sum(nums);
        System.out.println("Sum of numbers: " + sum);
        
        int product = at.product(nums);
        System.out.println("Product of numbers: " + product);
    }
}