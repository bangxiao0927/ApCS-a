public class Runner {
    public static void main(String[] args) {
        ArrayTest arrayTest = new ArrayTest();
        ArrayList<Card> cardList = new ArrayList<>();

        // Add 5 Cards with random numbers from 1 to 9 inclusive
        for (int i = 0; i < 5; i++) {
            int randomValue = (int)(Math.random() * 9) + 1;
            cardList.add(new Card(randomValue));
        }

        // Print the list
        arrayTest.printList(cardList);

        // Find and print total
        int total = arrayTest.findTotal(cardList);
        System.out.println("Total Value: " + total);

        // Scramble the list
        arrayTest.scramble(cardList);
        arrayTest.printList(cardList);

        // Search and delete value 2
        System.out.println("search and delete");
        arrayTest.searchAndDelete(cardList, 2);
        arrayTest.printList(cardList);

        // Search and replace value 4
        System.out.println("search and replace");
        arrayTest.searchAndReplace(cardList, 4);
        arrayTest.printList(cardList);

        // Sort the list
        arrayTest.sort(cardList);
        arrayTest.printList(cardList);
    }
}
