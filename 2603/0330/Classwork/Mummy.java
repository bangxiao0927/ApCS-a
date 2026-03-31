public class Mummy extends Monster {
    public Mummy(String name) {
        super(name);
    }

    @Override
    public String getFavFood() {
        return "I like to eat dust.";
    }
}
