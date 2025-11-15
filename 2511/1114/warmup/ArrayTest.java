public class ArrayTest {
    private int[] numbers;

    public ArrayTest(int size) {
        numbers = new int[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = (int)(Math.random() * 9) + 1;
        }
    }

    public void print() {
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public int getSum() {
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return sum;
    }

    public int getLargest() {
        int largest = numbers[0];
        for (int num : numbers) {
            if (num > largest) {
                largest = num;
            }
        }
        return largest;
    }
}