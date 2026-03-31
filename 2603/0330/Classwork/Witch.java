public class Witch extends Monster {
    public Witch(String name) {
        super(name);
    }

    @Override
    public String getFavFood() {
        return "I like to drink potions.";
    }
}
