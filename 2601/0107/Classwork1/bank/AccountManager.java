package bank;
public class AccountManager {
    private Account[] account;

    // Constructor
    public AccountManager() {
        account = new Account[3];
        account[0] = new Account("John", 1234, 10.10);
        account[1] = new Account("Jen", 1111, 99.99);
        account[2] = new Account("Jay", 4321, 11.11);
    }

    // Method to print account information
    public void printInfo() {
        for (int i = 0; i < account.length; i++) {
            System.out.println(account[i].toString());
        }
    }
}