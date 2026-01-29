public class Runner {
    public static void main(String[] args) {
        Test test = new Test();
        test.printList();

        int largest = test.searchLargest();
        System.out.println("Largest number: " + largest + "\n");

        test.searchAndReplace(5);
        System.out.println("After replacing 5 with 1000:");
        test.printList();

        test.searchAndRemove(2);
        System.out.println("After removing all 2s:");
        test.printList();

        test.sort();
        System.out.println("After sorting:");
        test.printList();
    }
}
