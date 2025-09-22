public class Runner {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Student student = new Student();
        Toy toy = new Toy();
        dog.Speak();
        student.speak();
        student.sayID();
        toy.type();
        toy.maker();
        toy.price();
    }
}
