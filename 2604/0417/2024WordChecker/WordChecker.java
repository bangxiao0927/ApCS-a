import java.util.ArrayList;
public class WordChecker {

    // ArrayList of strings
    private ArrayList<String> wordList;


    // For testing purposes
    public WordChecker(ArrayList<String> wordList) {
        this.wordList = wordList;
    }
    
    /**
    * Returns true if each element of wordList (except the first) contains the previous
    * element as a substring and returns false otherwise, as described in part (a)
    * Precondition: wordList contains at least two elements.
    * Postcondition: wordList is unchanged.
    */
    public boolean isWordChain() {
        /* to be implemented in part (a) */
        int idx = 0;
        for (String word : wordList) {
            if (wordList.indexOf(word) == -1 && idx != 0) { return false; }
            else {
                idx++;
            }
        }
        return true;
    }

    /**
    * Returns an ArrayList<String> based on strings from wordList that start
    * with target, as described in part (b). Each element of the returned ArrayList has had
    * the initial occurrence of target removed.
    * Postconditions: wordList is unchanged.
    * Items appear in the returned list in the same order as they appear in wordList.
    */
    public ArrayList<String> createList(String target) {
        /* to be implemented in part (b) */
        
    }
    
}