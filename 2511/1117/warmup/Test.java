public class Test {
    public void print(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    public int find(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public int getSummation(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
    }

    public int getSmallest(int[] arr) {
        if (arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty");
        }

        int smallest = arr[0]; //start with the first elements

        for (int num : arr) {
            if (num < smallest) {
                smallest = num;
            }
        }
        return smallest;
    }
}