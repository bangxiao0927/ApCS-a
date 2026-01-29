import java.util.ArrayList;
public class Test {
    private ArrayList<Integer> numList = new ArrayList<Integer>();

    public Test() {
        for (int i = 0; i< 10 ; i++) {
            numList.add((int)(Math.random() * 9) + 1);
        }
    }

    public void printList() {
        for(int i = 0; i < numList.size(); i++) {
            System.out.print(numList.get(i) + " ");
        }
        System.out.println("\n");
    }

    public int searchLargest() {
        int largest = numList.get(0);
        for (int i = 0; i < numList.size(); i++) {
            if (numList.get(i) >= largest) {
                largest = numList.get(i);
            }
        }
        return largest;
    }

    public void searchAndReplace(int target) {
        for (int i = 0; i < numList.size(); i++) {
            if (numList.get(i) == target) {
                numList.set(i, 1000);
            }
        }
    }

    public void searchAndRemove(int target) {
        for (int i = 0; i < numList.size(); i++) {
            if (numList.get(i) == target) {
                numList.remove(i);
                i--;
            }
        }
    }

    public void sort() {
        for (int i = 0; i < numList.size() - 1; i++) {
            for (int j = 0; j < numList.size() - 1 - i; j++) {
                if (numList.get(j) > numList.get(j + 1)) {
                    int temp = numList.get(j);
                    numList.set(j, numList.get(j + 1));
                    numList.set(j + 1, temp);
                }
            }
        }
    }
}
