public class Runner {
    public static void main(String[] args){
        CubePair p1 = new CubePair("number", "color");
        System.out.println(p1.rollCubes());

        CubePair p2 = new CubePair("number");
        System.out.println(p2.rollCubes());

        CubePair p3 = new CubePair("letter");
        System.out.println(p3.rollCubes());
    }
}