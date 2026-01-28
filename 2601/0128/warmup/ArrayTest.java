public class ArrayTest {
    private int[] numArray;

    public ArrayTest(int size) {
        numArray = new int[size];
        for (int i = 0;i < size;i++) {
            numArray[i] = (int)(Math.random() * 4);
        }
    }

    public void printArray() {
        for (int num : numArray) {
            System.out.print(num + " ");
        }
    }

    public int search(int target) {
        int i = 0;
        for (int num : numArray) {
            i++;
            if (num == target) {
                return i;
            }
        }
        return -1;
    }

    public void scramble() {
        for (int i = 0; i < numArray.length; i++) {
            int randIndex = (int)(Math.random() * numArray.length);
            int temp = numArray[i];
            numArray[i] = numArray[randIndex];
            numArray[randIndex] = temp;
        }
    }

    public void sort() {
        for (int i = 0; i < numArray.length - 1; i++) {
            for (int j = 0; j < numArray.length - 1 - i; j++) {
                if (numArray[j] > numArray[j + 1]) {
                    int temp = numArray[j];
                    numArray[j] = numArray[j + 1];
                    numArray[j + 1] = temp;
                }
            }
        }
    }
}
