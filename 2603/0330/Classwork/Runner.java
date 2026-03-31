public class Runner {
    public static void main(String[] args) {
        Monster vampire = new Vampire("Vlad");
        vampire.getInfo();
        vampire.addCoins(5);

        Monster mummy = new Mummy("Imhotep");
        mummy.getInfo();
        mummy.addCoins(8);

        Monster witch = new Witch("Morgana");
        witch.getInfo();
        witch.addCoins(3);

        System.out.println("Monster count: " + Monster.getCount());
        System.out.println("Group coins: " + Monster.getCoins());
    }
}
