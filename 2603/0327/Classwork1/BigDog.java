public class BigDog extends Dog {
    public BigDog(String name) {
        super(name);
    }

    @Override
    public String speak() {
        return super.speak() + "!!!!";
    }
}
