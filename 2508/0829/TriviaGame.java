import java.util.Scanner;

public class TriviaGame {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int score = 0;
        //1st question
        System.out.print("What color is the sky? :");
        String answer = scan.next();
        if(  answer.equals("blue") ){
            System.out.println("good job");
            score += 1;
        } else {
            System.out.println("sorry wrong answer");
        }
        //2nd question
        System.out.print("What are the 2 words that make up 67? :");
        scan.nextLine();
        answer = scan.nextLine();
        if(  answer.equals("six seven") ){
            System.out.println("good job");
            score += 1;
        } else {
            System.out.println("sorry wrong answer");
        }
        //3rd question
        System.out.print("Are you human? : ");
        answer = scan.next();
        if(  answer.equals("yes") ){
            System.out.println("good job");
            score += 1;
        } else {
            System.out.println("sorry wrong answer");
        }
        //4th question
        System.out.print("What is the name of this city? :");
        scan.nextLine();
        answer = scan.nextLine();
        if(  answer.equals("mountain view") ){
            System.out.println("good job");
            score += 1;
        } else {
            System.out.println("sorry wrong answer");
        }
        //5th question
        System.out.print("Are you bot? : ");
        answer = scan.next();
        if(  answer.equals("4") ){
            System.out.println("good job");
            score += 1;
        } else {
            System.out.println("sorry wrong answer");
        }
        //6th question
        System.out.print("Who is our president? :");
        scan.nextLine();
        answer = scan.nextLine();
        if(  answer.equals("Donald Trump") ){
            System.out.println("good job");
            score += 1;
        } else {
            System.out.println("sorry wrong answer");
        }
        System.out.println("Your word score is " + score);
    }
}
