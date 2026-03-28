public class Husky extends BigDog {
    public Husky(String name) {
        super(name);
    }

    @Override
    public String speak() {
        return super.speak() + " + howl!!!";
    }
}
