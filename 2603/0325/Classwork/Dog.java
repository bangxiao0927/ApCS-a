public class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    public void printInfo() {
        super.speak();
        System.out.println(getName());
        System.out.print("I make the sound ");
        this.speak();
    }

    @Override
    public void speak() {
        System.out.println("woof");
    }
}
