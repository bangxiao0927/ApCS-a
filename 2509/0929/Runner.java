public class Runner {
    public static void main(String[] args) {
        Student obj1 = new Student();
        obj1.printName();
        obj1.printName("15");

        Student obj2 = new Student("Martin");
        obj2.printName();
        obj2.printName("22");
    }
}
