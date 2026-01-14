public class Runner {
    public static void main(String[] args) {
        StringTest st = new StringTest("tom@mvla.net");
        System.out.println(st.toString());
        System.out.println("Number of 'm': " + st.countChar('m'));
        System.out.println("Domain: " + st.getDomain());
    }
}
