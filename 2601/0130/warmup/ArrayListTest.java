import java.util.ArrayList;

public class ArrayListTest {

    public static void print(ArrayList<Integer> list) {
        for (int num : list) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void sort(ArrayList<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    int temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }
}