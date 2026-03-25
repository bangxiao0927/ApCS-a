public class Runner {
    public static void main(String[] args) {
        Animal animal = new Animal("blank");
        animal.speak();

        Dog dog = new Dog("Fido");
        dog.printInfo();

        Bird bird = new Bird("Tweety");
        bird.printInfo();
    }
}
