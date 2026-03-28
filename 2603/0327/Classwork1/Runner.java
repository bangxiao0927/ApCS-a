import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {
        Dog d1 = new Dog("Fido");
        System.out.println(d1.speak());

        try {
            BigDog d2 = (BigDog) d1;
            System.out.println(d2.speak());
        } catch (ClassCastException e) {
            System.out.println("Casting d1 to BigDog does not work because d1 refers to a Dog object, not a BigDog.");
        }

        BigDog bd1 = new BigDog("Biggy");
        System.out.println(bd1.speak());

        Dog bd2 = bd1;
        System.out.println(bd2.speak());

        Husky h1 = new Husky("Snow");
        System.out.println(h1.speak());

        Dog h2 = h1;
        System.out.println(h2.speak());

        Husky h3 = (Husky) h2;
        System.out.println(h3.speak());

        BigDog h4 = h1;
        System.out.println(h4.speak());
        System.out.println("Casting h1 to BigDog works because a Husky is also a BigDog.");

        ArrayList<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog("Fido"));
        dogs.add(new BigDog("Biggy"));
        dogs.add(new Husky("Snow"));

        for (Dog dog : dogs) {
            System.out.println(dog.speak());
        }
    }
}
