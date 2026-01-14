public class ArrayTest {

    private int[] numArray;
    private String[] stringArray;

    public ArrayTest() {
        numArray = new int[10];
        for (int i = 0; i < numArray.length; i++) {
            numArray[i] = (int)(Math.random() * 20) + 1;
        }

        stringArray = new String[]{"cat", "dog", "elephant", "giraffe", "lion"};
    }

    public void printNumArray() {
        for (int num : numArray) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public void printStringArray() {
        for (String str : stringArray) {
            System.out.print(str + " ");
        }
        System.out.println();
    }

    public void swapNumArray(int index1, int index2) {
        int temp = numArray[index1];
    
        numArray[index1] = numArray[index2];
        numArray[index2] = temp;
    }

    public void swapStringArray(int index1, int index2) {
        String temp = stringArray[index1];
        
        stringArray[index1] = stringArray[index2];
        stringArray[index2] = temp;
    }

    public int searchString(String target) {
        for (int i = 0; i < stringArray.length; i++) {
            if (stringArray[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public int countLetter(char ch) {
        int count = 0;
        for (String str : stringArray) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == ch) {
                    count++;
                }
            }
        }
        return count;
    }
}