import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Blank acc= new Blank("Bang X", 10000);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""

                ========== Menu =========
                1. Print Name
                2. Print Balance.
                3. Deposit.
                4. Withdrawal
                ==========================
                """);

            System.out.print("Please choose an option (1-4): ");
            int choice = scanner.nextInt();
            System.out.println("==========================");

            switch(choice) {
                case 1 -> System.out.println(acc.getName());
                case 2 -> System.out.println(acc.getBalance());
                case 3 -> {
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    acc.deposit(depositAmount);
                }
                case 4 -> {
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    acc.withdraw(withdrawalAmount);
                }
            }
        }
    }
}
