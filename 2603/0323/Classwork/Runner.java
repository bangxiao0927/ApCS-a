import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {
        ArrayList<Language> myLanguages = new ArrayList<Language>();

        myLanguages.add(new Spanish());
        myLanguages.add(new French());
        myLanguages.add(new German());
        myLanguages.add(new Chinese());

        for (Language each : myLanguages) {
            System.out.println(each.getAuthor());
            System.out.println(each.getHello());
            System.out.println(each.getBye());
            System.out.println(each.getThankYou());
            System.out.println();
        }
    }
}
