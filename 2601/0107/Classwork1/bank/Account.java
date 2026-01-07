package bank;

public class Account {
    private int pin;
    protected double balance;
    protected String name;

    // Constructor
    public Account(String name, int pin, double balance) {
        this.name = name;
        this.pin = pin;
        this.balance = balance;
    }

    // toString method
    public String toString() {
        return "Name: " + name + ", Balance: $" + balance;
    }
}