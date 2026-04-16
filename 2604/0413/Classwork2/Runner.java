public class Runner {
    public static void main(String[] args) {
        MyMath math = new MyMath();
        int[] arr = new int[]{1, 3, 5, 7, 9, 11, 13, 15};
        System.out.println(math.binarySearch(arr, 5, 0, arr.length - 1));
        System.out.println(math.binarySearch(arr, 6, 0, arr.length - 1));
    }
}
