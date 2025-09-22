import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    System.out.println("\n==========================");
    System.out.print("""
    1.Area of Right Triangle
    2.Area of Polynomial
    3.Area of Trapezoid
    4 Hypotenuse of Right Triangle 
    5.Perimeter of any regular polygon
    ==========================
    """);
    int choice = keyboard.nextInt();
		double answer;
    if( choice == 1 ){
      System.out.print("If the base is 10.0 and height is 5.0, what is the Area of a Triangle? :");
      answer = keyboard.nextDouble();
      if( answer == 25.0 ){
        System.out.println("correct");
      } else {
        System.out.println("incorrect");
      }
    }
                     
    if( choice == 2 ){
      System.out.print("If the sides are 10.0 and there are 5 sides, with Apothem of 67 what is the Area of a Triangle? :");
      answer = keyboard.nextDouble();
      if( answer == 335.0 ){
        System.out.println("correct");
      } else {
        System.out.println("incorrect");
      }
    }
                    
    if( choice == 3 ){
      System.out.print("If a trapezoid has base lengths 4 and 5 and a height of 6, what is the area?:");
      answer = keyboard.nextDouble();
      if( answer == 27.0 ){
        System.out.println("correct");
      } else {
        System.out.println("incorrect");
      }
    }
                     
    if( choice == 4 ){
			System.out.print("If the 2 non-hypotenuse sides of a right triangle are 3 and 4, how long is the hypotenuse? :");
    	answer = keyboard.nextDouble();
      if (answer == 5.0) {
        System.out.println("Correct!");
      }
      else {
        System.out.println("Incorrect");
      }
    }
    
    if( choice == 5 ){
    	System.out.print("If a polygon has 5 sides and each side length is 6 units long, what is the perimeter? :");
    	answer = keyboard.nextDouble();
      if (answer == 30.0) {
        System.out.println("Correct!");
      }
      else {
        System.out.println("Incorrect");
      }
    }

	}
}