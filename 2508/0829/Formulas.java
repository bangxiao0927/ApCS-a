import java.util.Scanner;

public class Formulas{
	public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter the loop times: ");
        int amount = keyboard.nextInt();
        for(int i = 0 ; i < amount; i ++) {
            System.out.println("\nloop time: " + (i + 1));
            System.out.println("\n==========================");
            System.out.print("""
            1.Area of Right Triangle
            2.Area of Polynomial
            3.Area of Trapezoid
            4 Hypotenuse of Right Triangle 
            5.Perimeter of any regular polygon
            ==========================
            """);

            int choose = keyboard.nextInt();
            double result;

            if (choose == 1) {
                System.out.println("Please Enter the base and height of the right triangle, seperate with space:\n");
                double trianglebase = keyboard.nextDouble();
                double triangleheight = keyboard.nextDouble();
                result = 0.5 * trianglebase * triangleheight;
                System.out.println("The Area of the triangle is : " + result);
                continue;
            }
            
            if (choose == 2) {
                System.out.println("Please Enter the side amout: \n");
                int sideAmount = keyboard.nextInt();
                System.out.println("Please Enter the side length: \n");
                double sideLength = keyboard.nextDouble();
                System.out.println("Please Enter the side amout: \n");
                double polygonApothem = keyboard.nextDouble();
                result = 0.5 * polygonApothem * sideAmount * sideLength;
                System.out.println("The Area of the " + sideAmount + "polygon " + "is : " + result);
                continue;
            }
            
            if (choose == 3) {
                System.out.print("Enter first base of trapezoid: ");
                double trapside1 = keyboard.nextDouble();
                System.out.print("Enter second base of trapezoid: ");
                double trapside2 = keyboard.nextDouble();
                System.out.print("Enter height of trapezoid: ");
                double trapheight = keyboard.nextDouble();
                result = ((trapside1+trapside2)/2.0) * (trapheight);
                System.out.println("Trapezoid area:" + result);
                continue;
            }
            
            // Pythagorean Theorem
            if (choose == 4) {
                System.out.print("Enter a side of the right triangle (Not Hypotenuse): ");
                double side1 = keyboard.nextDouble();
                System.out.print("Enter another side of the right triangle (Not Hypotenuse): ");
                double side2 = keyboard.nextDouble();
                result = Math.sqrt(Math.pow(side1,2.0) + Math.pow(side2,2.0));
                System.out.println("Hypotenuse Length:" + result);
                continue;
            }

            if (choose == 5) {
                System.out.print("Enter the amount of sides: ");
                double amountsides = keyboard.nextDouble();
                System.out.print("Enter side length: ");
                double sidelength = keyboard.nextDouble();
                double perimeter = amountsides * sidelength;
                System.out.println("Perimeter:" + perimeter);
            }
        }
    }
}