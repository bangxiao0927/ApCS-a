public class Runner {
    public static void main(String[] args) {
        Formulas formulas = new Formulas();
        while(true) {
            formulas.displayMenu();

            if (formulas.choice.equals("0")) {
                System.out.println("Exiting the program.");
                break;
            }

            formulas.formulaDecider(formulas.choice);

            formulas.scan.nextLine(); // debug
            System.out.println("================================");
            System.out.print("Enter anything to continue...(0 to exit): ");
            String exitRunner = formulas.scan.nextLine(); // Wait for user to Enter or continue
            if (exitRunner.equals("0")) {
                System.out.println("Exiting the program.");
                break;
            }
        }
    }
}
