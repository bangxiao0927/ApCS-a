import java.util.Scanner;

public class WarmUp {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter a verb: ");
        String verb = scan.next();
        System.out.println("Please enter a noun: ");
        String noun = scan.next();
        String output = ("The " + noun + " " + verb + "s.");
        System.out.println(output);
    }
}
