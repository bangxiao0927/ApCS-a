public class MyMath {
    public int binarySearch(int[] array, int value, int start, int end) {
        if (start > end) {
            return -1;
        }
        int middle = (start + end) / 2;
        if (array[middle] == value) { //Found number
                return middle;
        }
        else if (array[middle] > value) { //search beginning half
                return binarySearch(array, value, start, middle - 1);
        }
        else { //Search the end half
                return binarySearch(array, value, middle + 1, end);
        }
    }
}
