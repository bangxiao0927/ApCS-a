import java.util.ArrayList;


public class Runner {


    public static void main(String[] args) {
        ArrayList<String> arr1 = new ArrayList<String>();


        // Test isWordChain
        System.out.println("Testing Part A: ");
        System.out.println("Test isWordChain():");
        arr1.add("an");
        arr1.add("band");
        arr1.add("band");
        arr1.add("abandon");
        WordChecker w1 = new WordChecker(arr1);
        System.out.println("\tisWordChain returns " + w1.isWordChain());


        arr1.clear();
        arr1.add("to");
        arr1.add("too");
        arr1.add("stool");
        arr1.add("tools");
        System.out.println("\tisWordChain returns " + w1.isWordChain());


        // Test createList
        System.out.println("\nTesting Part B: ");
        System.out.println("Test createList():");
        arr1.clear();
        arr1.add("catch");
        arr1.add("bobcat");
        arr1.add("catchacat");
        arr1.add("cat");
        arr1.add("at");


        ArrayList<String> newList1 = w1.createList("cat");
        System.out.print("\tcreateList(\"cat\") = ");
        System.out.println( newList1 );




        ArrayList<String> newList2 = w1.createList("catch");
        System.out.print("\tcreateList(\"catch\") = ");
        System.out.println( newList2 );




        ArrayList<String> newList3 = w1.createList("dog");
        System.out.print("\tcreateList(\"dog\") = ");
        System.out.println( newList3 );




    }
}
