public class ArrayTest {

    private int[] numbers;
    
    public ArrayTest(int size) {
        numbers = new int[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = (int)(Math.random() * 9) + 1;
        }
    }
    
    public void print() {
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();
    }
    
    public boolean search(int target) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == target) {
                return true;
            }
        }
        return false;
    }
}
