public class Runner {
    public static void main(String[] args) {
        Profile p1 = new Profile();
        Profile p2 = new Profile("Jennifer",16);

        p1.printInfo();
        p2.printInfo();

        p1.printInfo(4321);
        p2.printInfo(1234);
    }
}