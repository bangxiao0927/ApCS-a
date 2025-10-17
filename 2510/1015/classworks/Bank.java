public class Bank {
    private String name;
    private double balance;
    private int pin;
    private boolean access;

    public Bank(String name, double balance, int pin) {
        this.name = name;
        this.balance = balance;
        this.pin = pin;
        this.access = false;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        balance = Math.round(balance * 100.0) / 100.0;
        return balance;
    }

    public boolean getAccess() {
        return access;
    }

    public void checkPin(int inputPin) {
        if (inputPin == this.pin) {
            access = true;
        } else {
            access = false;
        }
    }

    public void withdraw(double amount) {
        if (access) {
            if (amount <= balance) {
                balance -= amount;
            } else {
                System.out.println("Insufficient funds");

            }
        } else {
            System.out.println("Access denied");
        }
    }

    public void deposit(double amount) {
        if (access) {
            if (amount > 0) {
                balance += amount;
            } else {
                System.out.println("Deposit amount must be positive");
            }
        } else {
            System.out.println("Access denied");
        }
    }

    public void logout() {
        this.access = false;
    }
}