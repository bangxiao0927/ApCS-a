import java.util.Scanner;

public class AgeCalc {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Birth Year: ");
        int birthyear = scan.nextInt();
        System.out.println("Enter Birth Month: ");
        int birthmonth = scan.nextInt();
        int age = 2025 - birthyear;
        if( birthmonth > 8 ){
      	    age = age -1;
        }
        System.out.println("Your age is : " + age);
    }
}
