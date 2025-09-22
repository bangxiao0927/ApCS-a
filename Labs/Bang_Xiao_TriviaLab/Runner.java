import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Runner {
    private static Runner runner = new Runner();
    private static Scanner keyboard = new Scanner(System.in);
    private static Random random = new Random();
    
    private static double answer;
    private static double mathAnswer;
    private static String geographyAnswer;
    private static int totalScore = 0;
    private static int currentIndex = 0;
    private static int randomNumber1 = 0;
    private static int randomNumber2 = 0;
    private static boolean hardMode = false;

    private String scoreGrader(int totalScore) {
        if(0 <= totalScore && totalScore <= 2) {
            return "F";
        }
        else if(totalScore == 3) {
            return "D";
        }
        else if(totalScore == 4) {
            return "C";
        }
        else if(totalScore == 5) {
            return "B";
        }
        else if(totalScore == 6) {
            return "A";
        }
        return "Invalid";
    }

    private static double mathEasyQuestionAnswer(int theIndex) {
        switch (theIndex) {
            case 0 -> {
                answer = randomNumber1 * randomNumber2;
            }
            case 1 -> {
                answer = 0.5 * randomNumber1 * randomNumber2;
            }
            case 2 -> {
                answer = 0.5 * randomNumber1 * randomNumber1;
            }
            case 3 -> {
                answer = randomNumber1 * randomNumber1 * randomNumber1;
            }
            case 4 -> {
                answer = Math.PI * randomNumber1 * randomNumber1;
            }
            case 5 -> {
                answer = Math.PI * randomNumber1;
            }
        }
        if(0 <= theIndex && theIndex <= 5) {
            answer = Math.round(answer * 100.0) / 100.0;
            return answer;
        }
        return 0;
    }

    private static double mathHardQuestionsAnswer(int theIndex) {
        switch (theIndex) {
            case 0 -> {
                answer = (Math.sqrt(3) / 4.0) * randomNumber1 * randomNumber1;
            }
            case 1 -> {
                answer = 2 * randomNumber2;
            }
            case 2 -> {
                answer = randomNumber1 + 3 * randomNumber2 * randomNumber2;
            }
        }
        if(0 <= theIndex && theIndex <= 2) {
            answer = Math.round(answer * 100.0) / 100.0;
            return answer;
        }
        return 0;
    }

    private static void chooseRandomQuestion(List<Integer> pickedQuestions) {
        while(pickedQuestions.size() < 6) {
            for(int i = 0; i < 6; i++) {
                if(random.nextBoolean() && !pickedQuestions.contains(i)) {
                    pickedQuestions.add(i);
                }
            }
        }
    }

    public static void main(String[] args) {
        List<String> mathEasyQuestions = new ArrayList<>(List.of(
            "Given a rectangle with the length of $1 and a height of $2, what is the area?",
            "Given the right triangle with a base of $1 and a height of $2, what is the area?",
            "Given the side of a Isosceles right triangle is $1, what is the area?",
            "Given the side of a cube is $1, what is the volume?",
            "Given the radius of a circle is $1, what is the area?",
            "Given the diameter of a circle is $1, what is the circumference?"
        ));

        List<String> mathHardQuestions = new ArrayList<>(List.of(
            "Given the side of a regular triangle is $1, what is the area?",
            "What is the derivative when y = $1 + x^2 , and x = $2?",
            "What is the derivative when y = $1*x + x^3 , and x = $2?"
        ));

        List<String> geographyEasyQuestions = new ArrayList<>(List.of(
            "What is the capital of France?",
            "What is the country that USA sent nuclear weapons to during WWII?",
            "What is the capital of United States?",
            "What is the capital of Chinese Republic?",
            "What is the capital of United Kingdom?",
            "Which state is Mountain View located in?"
        ));

        List<String> geographyEasyQuestionsChoiceList = new ArrayList<>(List.of(
            "Choice1: Paris\nChoice2: London\nChoice3: Berlin",
            "Choice1: China\nChoice2: Japan\nChoice3: Korea",
            "Choice1: New York\nChoice2: Mountain View\nChoice3: Washington D.C.",
            "Choice1: Shanghai\nChoice2: Beijing\nChoice3: Taiwan",
            "Choice1: London\nChoice2: Paris\nChoice3: Berlin",
            "Choice1: California\nChoice2: Texas\nChoice3: Florida"
        ));

        String[] geographyEasyQuestionsAnswers = new String[] {"Paris", "Japan", "Washington D.C.", "Beijing", "London", "California"};
        List<String> geographyHardQuestions = new ArrayList<>(List.of(
            "What is the capital of Canada?",
            "Which country in the world have most populations?",
            "What is the smallest country in the world?"
        ));
        List<String> geographyHardQuestionsChoiceList = new ArrayList<>(List.of(
            "Choice1: Vancouver\nChoice2: Toronto\nChoice3: Ottawa",
            "Choice1: China\nChoice2: USA\nChoice3: India",
            "Choice1: Vatican\nChoice2: Monaco\nChoice3: New Zealand"
        ));
        String[] geographyHardQuestionsAnswers = new String[] {"Ottawa", "China", "Vatican"};
        List<Integer> pickedQuestions = new ArrayList<>();
        System.out.println("""
        =================
        1,Math Formulas
        2,Geography
        =================""");
        System.out.println("Please select a question type:");
        int type = keyboard.nextInt();
        keyboard.nextLine();
        // Math Formulas Questions
        if(type == 1) {
            System.out.println("You selected Math Formulas.");
            runner.chooseRandomQuestion(pickedQuestions);
            for(int i = 0; i < 6; i++) {
                System.out.println("======WARNNING: ALL NUMBERS SHOULD BE ROUND TO 2 DECIMAL PLACES======");
                randomNumber1 = random.nextInt(1, 20);
                randomNumber2 = random.nextInt(1, 20);
                if(hardMode == false && totalScore == 3 && i == 3) {
                    hardMode = true;
                    for(int j = 0; j < 3; j++) {
                        pickedQuestions.add(j);
                    }
                }
                String outputQuestion;
                if(hardMode == false) {
                    currentIndex = pickedQuestions.get(i);
                    outputQuestion = mathEasyQuestions.get(currentIndex);
                    outputQuestion = outputQuestion.replace("$1", String.valueOf(randomNumber1)).replace("$2", String.valueOf(randomNumber2));
                    System.out.println(outputQuestion);
                    mathAnswer = keyboard.nextDouble();
                    if(mathAnswer == mathEasyQuestionAnswer(currentIndex)) {
                        totalScore++;
                        System.out.println("======Correct!======");
                    }
                    else {
                        System.out.println("======Wrong! The correct answer is " + mathEasyQuestionAnswer(currentIndex) + "======");
                    }
                }
                else if(hardMode == true) {
                    currentIndex = pickedQuestions.get(i+4);
                    outputQuestion = mathHardQuestions.get(currentIndex);
                    outputQuestion = outputQuestion.replace("$1", String.valueOf(randomNumber1)).replace("$2", String.valueOf(randomNumber2));
                    System.out.println(outputQuestion);
                    mathAnswer = keyboard.nextDouble();
                    if(mathAnswer == mathHardQuestionsAnswer(currentIndex)) {
                        totalScore++;
                        System.out.println("======Correct!======");
                    }
                    else {
                        System.out.println("======Wrong! The correct answer is " + mathHardQuestionsAnswer(currentIndex) + "======");
                    }
                }
            }
        }
        // Geography Questions
        else if (type == 2) {
            System.out.println("You selected Geography.");
            runner.chooseRandomQuestion(pickedQuestions);
            for(int i = 0; i < 6; i++) {
                System.out.println("======PLEASE TYPE THE ANSWER======");
                if(hardMode == false && totalScore == 3 && i == 3) {
                    hardMode = true;
                    for(int j = 0; j < 3; j++) {
                        pickedQuestions.add(j);
                    }
                }
                String outputQuestion;
                if(hardMode == false) {
                    currentIndex = pickedQuestions.get(i);
                    outputQuestion = geographyEasyQuestions.get(currentIndex);
                    
                    System.out.println(outputQuestion);
                    //print choices
                    System.out.println(geographyEasyQuestionsChoiceList.get(currentIndex));
                    geographyAnswer = keyboard.nextLine();
                    if(geographyAnswer.equals(geographyEasyQuestionsAnswers[currentIndex])) {
                        totalScore++;
                        System.out.println("======Correct!======");
                    }
                    else {
                        System.out.println("======Wrong! The correct answer is " + geographyEasyQuestionsAnswers[currentIndex] + "======");
                    }
                }
                else if(hardMode == true) {
                    currentIndex = pickedQuestions.get(i+4);
                    outputQuestion = geographyHardQuestions.get(currentIndex);
                    System.out.println(outputQuestion);
                    //print choices
                    System.out.println(geographyHardQuestionsChoiceList.get(currentIndex));
                    geographyAnswer = keyboard.nextLine();
                    if(geographyAnswer.equals(geographyHardQuestionsAnswers[currentIndex])) {
                        totalScore++;
                        System.out.println("======Correct!======");
                    }
                    else {
                        System.out.println("======Wrong! The correct answer is " + geographyHardQuestionsAnswers[currentIndex] + "======");
                    }
                }
            }
        }
        // Validating User Choices
        else {
            System.out.println("Invalid selection.");
            return;
        }
        //output score
        System.out.println("======TEST ENDED======");
        System.out.println("Your score is: " + totalScore);
        System.out.println("Your grade is: " + runner.scoreGrader(totalScore));
    }
}
