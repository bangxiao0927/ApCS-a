import java.util.ArrayList;

public class Runner {

    public static void main(String[] args) {

        ArrayList<Integer> myList = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            myList.add((int)(Math.random() * 100) + 1);
        }
        ArrayListTest.print(myList);
        ArrayListTest.sort(myList);
        ArrayListTest.print(myList);
    }
}