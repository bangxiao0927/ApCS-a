public class StringTest {
    private String email;

    public StringTest(String email) {
        this.email = email;
    }

    public String toString() {
        return email + " " + email.length();
    }

    public int countChar(char ch) {
        int count = 0;
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }

    public String getDomain() {
        int index1 = email.indexOf("@");
        int index2 = email.indexOf(".");
        return email.substring(index1 + 1, index2);
    }
}
