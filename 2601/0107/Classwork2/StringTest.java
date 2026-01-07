public class StringTest {
    private String myText;

    public StringTest(String myText) {
        this.myText = myText;
    }

    public int getLength() {
        return myText.length();
    }

    public void printInfo() {
        System.out.println("Text: " + myText);
        System.out.println("Length: " + getLength());
    }

    public void printChar(int index) {
        if (index >= 0 && index < myText.length()) {
            System.out.println("Character at index " + index + ": " + myText.charAt(index));
        } else {
            System.out.println("Index out of bounds.");
        }
    }

    public void printLocation(String substring) {
        int index = myText.indexOf(substring);
        if (index != -1) {      
            System.out.println("Substring \"" + substring + "\" found at index: " + index);
        } else {
            System.out.println("Substring \"" + substring + "\" not found.");
        }
    }

    public int countChar(char ch) {
        int count = 0;
        for (int i = 0; i < myText.length(); i++) {
            if (myText.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }
    public boolean contains(String substring) {
        return myText.indexOf(substring) != -1;
    }
    public int countVowels() {
        int count = 0;          
        for (int i = 0; i < myText.length(); i++) {
            char ch = Character.toLowerCase(myText.charAt(i));
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++;
            }
        }
        return count;
    }
}