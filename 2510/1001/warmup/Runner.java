public class Runner {
    public static void main(String[] args) {
        Profile p1 = new Profile("David", 16);
        Profile p2 = new Profile();

        p1.printProfile();
        p2.printProfile();
    }
}