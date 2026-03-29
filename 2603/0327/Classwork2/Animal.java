public abstract class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public abstract String speak();

    public abstract String getColor();

    public String getName() {
        return "I am " + name;
    }

    public String toString() {
        return getName() + ", I say " + speak() + ", and my color is " + getColor() + ".";
    }
}
