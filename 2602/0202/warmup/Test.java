import java.util.ArrayList;

public class Test {
    public void scramble(ArrayList<String> list) { 
        for (int i = 0; i < list.size() - 1; i++) {
            if (i % 2 == 0) {
                String temp = list.get(i);
                list.set(i, list.get(i + 1));
                list.set(i + 1, temp);
            }
        }
    }

    public void changeNum(int num) { 
        num = 99;
    }
     
}