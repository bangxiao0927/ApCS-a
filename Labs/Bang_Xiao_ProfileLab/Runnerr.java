public class Runnerr {
    public static void main(String[] args) {
        Profile p1 = new Profile("Joseph", 15, "Math", "Coding", "Sushi");
        Profile p2 = new Profile("Ishan", 15, "Science", "Hiking", "Sushi");
        Profile p3 = new Profile("Angela", 16, "Math", "Coding", "Pizza");
        
        p1.printInfo();
        p2.printInfo();
        p3.printInfo();
    }
}
