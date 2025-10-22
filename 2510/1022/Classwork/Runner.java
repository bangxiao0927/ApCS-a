import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Selection selection = new Selection();
        Scanner scan = new Scanner(System.in);
    
        System.out.println("Possible zip codes: ");
        System.out.println("===================");
        System.out.println("94040");
        System.out.println("94115");
        System.out.println("95051");
        System.out.println("95129");
        System.out.println("94607");
        System.out.println("95035");
        System.out.println("===================");

        System.out.print("Enter your grade (0-100): ");
        int grade = scan.nextInt();

        selection.gradeCalculator(grade);

        while (true) { 
            System.out.print("Enter a zip code to look up: ");
            int zipCode = scan.nextInt();

            String city1 = selection.zipCode1(zipCode);
            String city2 = selection.zipCode2(zipCode);

            System.out.println("Lookup 1: " + city1);
            System.out.println("Lookup 2: " + city2);

            System.out.print("Do you want to look up another zip code? (yes/no): ");
            String response = scan.next();

            if (response.equalsIgnoreCase("no")) {
                break;
            }
        }
    }
}
