/*
    1. Print "Area of a Triangle"
    2. Create a variable called base
    3. Create a variable called height
    4. Create a variable called area
    5. Print ("Enter in the base of the triangle: ")
    6. Get a number from the user and set it to the variable base
    7. Print ("Enter in the height of the triangle: ")
    8. Get a number from the user and set it to the variable height
    9. Do the calculation (base*height)/2 and set it to the variable area
    10. Print ("Area:" + area)

*/

import java.util.Scanner;

public class MathSolver {
    public static void main(String[] args) {
    	  // 
        Scanner scan = new Scanner(System.in);
        System.out.println("Pythagorean Theorem");
        
        System.out.println("Enter right angle side 1 of a Triangle: ");
        double sidea = scan.nextDouble();
        
        System.out.println("Enter right angle side 2 of a Triangle: ");
        double sideb = scan.nextDouble();

        double pythagorean = Math.sqrt((sidea*sidea) + (sideb*sideb));
        System.out.println("Pythagorean : " + pythagorean);
        
        // circle area
        System.out.println("circle area");
        System.out.println("Enter the radius of the circle : ");
        double radius = scan.nextDouble();

        double Circle_Area = Math.pow(radius, 2) * Math.PI;
        System.out.println("Your circle's area is :" + Circle_Area);
        
        // circle circumference
        System.out.println("circle circumference");
        System.out.println("Enter the radius of the circle : ");
        double radius2 = scan.nextDouble();

        double Circle_Circumference = 2 * radius2 * Math.PI;
        System.out.println("Your circle's circumference is :" + Circle_Circumference);
        
    }
}

