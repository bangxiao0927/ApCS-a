public class Runner {
    public static void main(String[] args) {
        int[] numbers = new int[5];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (int)(Math.random() * 5) + 1; // random number between 1 and 5
        }

        PracticeQuiz quiz = new PracticeQuiz();

        quiz.print(numbers);

        boolean containsThree = quiz.contains(numbers, 3);
        System.out.println("Contains 3: " + containsThree);

        int product = quiz.getProduct(numbers);
        System.out.println("Product: " + product);

        int largest = quiz.getLargest(numbers);
        System.out.println("Largest: " + largest);
    }
}
