public class Monster {
    public String name;
    public static int count = 0;
    public static int coins = 0;

    public Monster(String name) {
        this.name = name;
        count++;
    }

    public String getFavFood() {
        return "I like to eat brains.";
    }   

    public String getName() {
        return "My name is " + name;
    }

    public static int getCount() {
        return count;
    }

    public static void addCoins(int amount) {
        coins += amount;
    }

    public static int getCoins() {
        return coins;
    }

    public void getInfo() {
        System.out.println(getName());
        System.out.println(getFavFood());
    }
}