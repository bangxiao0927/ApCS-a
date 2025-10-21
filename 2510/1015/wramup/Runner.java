public class Runner {
    public static void main(String[] args) {
        Pythagorean pythagorean = new Pythagorean();
        double hypotenuse = pythagorean.findC(5, 12);
        System.out.println("The hypotenuse is: " + hypotenuse);
    }
}
