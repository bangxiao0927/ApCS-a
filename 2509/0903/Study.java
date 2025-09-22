import java.util.Scanner;

public class Study {
  public static void main(String[] args) {
    int score = 0;
    Scanner keyboard = new Scanner(System.in);
    System.out.println("==========================");
    System.out.print("""
        1. Math
        2. History
        3. Art
        """);
    System.out.println("==========================");
    double choice = keyboard.nextDouble();
  	
    // Math
    if (choice == 1) {
      double mathAnswer;
      System.out.print("If the base is 10.0 and height is 5.0, what is the Area of a Triangle? :");
      mathAnswer = keyboard.nextDouble();
      if( mathAnswer == 25.0 ){
        System.out.println("correct");
        score++;
      } else {
        System.out.println("incorrect");
      }

      System.out.print("If the sides are 10.0 and there are 5 sides, with Apothem of 67 what is the Area of a Triangle? :");
      mathAnswer = keyboard.nextDouble();
      if( mathAnswer == 335.0 ){
        System.out.println("correct");
        score++;
      } else {
        System.out.println("incorrect");
      }

      System.out.print("If a trapezoid has base lengths 4 and 5 and a height of 6, what is the area?:");
      mathAnswer = keyboard.nextDouble();
      if( mathAnswer == 27.0 ){
        System.out.println("correct");
        score++;
      } else {
        System.out.println("incorrect");
      }
    }
                       
  	// History
    if (choice == 2) {
      String historyAnswer;
      System.out.print("Who is the present president of USA ?:");
      keyboard.nextLine();
      historyAnswer = keyboard.nextLine();
      if( historyAnswer.equals("Donald Trump") ){
        System.out.println("correct");
        score++;
      } else {
        System.out.println("incorrect");
      }

      System.out.print("What date did USA independent? :");
      historyAnswer = keyboard.nextLine();
      
      if( historyAnswer.equals("July fourth") ){
        System.out.println("correct");
        score++;
      } else {
        System.out.println("incorrect");
      }
      System.out.print("Whose our first president:");
      historyAnswer = keyboard.nextLine();
      
      if( historyAnswer.equals("George Washington")){
        System.out.println("correct");
        score++;
      } else {
        System.out.println("incorrect");
      }
    }
    // Art
  	if (choice == 3) {
      // Question 1
      System.out.println("What is this?");
      System.out.println("  -------- ");
      System.out.println(" ----------- ");
      System.out.println("--------------");
      System.out.println("  |-------|");
      System.out.println("  |  _    |");
      System.out.println("  | | |   |");
      System.out.println("  |  -    |");
      System.out.println("  |       |");
      System.out.println("  |-------|");
      keyboard.nextLine();
      String artanswer1 = keyboard.nextLine();
      if (artanswer1.equals("house")) {
        System.out.println("Correct!");
        score++;
      }
      else {
        System.out.println("Incorrect");
      }
      // Question 2
      System.out.println("What is this?");
      System.out.println("---------");
      System.out.println("| O  O  |");
      System.out.println("|       |");
      System.out.println("| ----  |");
      System.out.println("--------");
      String artanswer2 = keyboard.nextLine();
      if (artanswer2.equals("person")) {
        System.out.println("Correct!");
        score++;
      }
      else {
        System.out.println("Incorrect");
      }
    	// Question 3
      System.out.println("What is this? (Hint: Math Symbol) ");
      System.out.println("------->");
      System.out.println("-");
      System.out.println("  -");
      System.out.println("    -");
      System.out.println("  -");       
      System.out.println("-");
      System.out.println("------->");
      String artanswer3 = keyboard.nextLine();
      if (artanswer3.equals("sigma")) {
        System.out.println("Correct!");
        score++;
      }
      else {
        System.out.println("Incorrect");
      }
    }      
    System.out.println("Your total score is " + score);    
  }
}
