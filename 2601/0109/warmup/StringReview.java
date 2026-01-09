public class StringReview {
    public int getLength(String str) {
        return str.length();
    }

    public char getChar(String str, int index) {
        if (index >= 0 && index < str.length()) {
            return str.charAt(index);
        } else {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
    }

    public int countChar(String str, char ch) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }
}