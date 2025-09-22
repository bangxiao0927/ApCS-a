/*
 * 1, Print area of a triangle
 * 2, Create a variable called base 
 * 3, height
 * 4, Calculate the area: area = 0.5 * base * height
 */


import java.util.Scanner;

public class Triangle2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter your base number: \n");
        double base = scan.nextDouble();
        System.out.println("\nYou entered: " + base + "\n");

        System.out.println("Please enter your height number: ");
        double height = scan.nextDouble();
        System.out.println("\nYou entered: " + height);

        double area = 0.5 * base * height;
        System.out.println("\nThe area of the triangle is: " + area);
    }
}
