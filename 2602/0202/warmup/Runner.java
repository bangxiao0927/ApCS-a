import java.util.ArrayList;
public class Runner {
    public static void main(String[] args) {
        ArrayList<String> animals = new ArrayList<String>();
        animals.add("cat");
        animals.add("dog");
        animals.add("bird");
        animals.add("cow");
        animals.add("bear");

        int num = 0;

        Test test = new Test();

        System.out.println(animals);
        test.scramble(animals);
        System.out.println(animals);
        System.out.println(num);
        test.changeNum(num);
        System.out.println(num);
    }
}