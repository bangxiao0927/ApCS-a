public class ArrayTest {
    
    public ArrayTest() {
    }
    
    public void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    public boolean searchNumber1(int target, int[] arr) {
        for (int num : arr) {
            if (num == target) {
                return true;
            }
        }
        return false;
    }
    
    public int searchNumber2(int target, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
    
    public int findLargest(int[] arr) {
        int largest = arr[0];
        for (int num : arr) {
            if (num > largest) {
                largest = num;
            }
        }
        return largest;
    }
    
    public int findSmallest(int[] arr) {
        int smallest = arr[0];
        for (int num : arr) {
            if (num < smallest) {
                smallest = num;
            }
        }
        return smallest;
    }
    
    public int sum(int[] arr) {
        int total = 0;
        for (int num : arr) {
            total += num;
        }
        return total;
    }
    
    public int product(int[] arr) {
        int prod = 1;
        for (int num : arr) {
            prod *= num;
        }
        return prod;
    }
}