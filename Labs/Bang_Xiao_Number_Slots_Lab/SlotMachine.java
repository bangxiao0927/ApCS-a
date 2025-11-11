import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class SlotMachine {
    private int num1;
    private int num2;
    private int num3;
    private int winnings;
    private int balance;
    
    public SlotMachine(int balance) {
        this.balance = balance;
        this.winnings = 0;
        this.num1 = 0;
        this.num2 = 0;
        this.num3 = 0;
    }
    
    public void spin() {
        // Only spin if balance is at least 1
        if (balance < 1) {
            return;
        }
        
        // Deduct cost to play
        balance -= 1;
        
        // Generate 3 random numbers from 1 to 9
        num1 = (int)(Math.random() * 9 + 1);
        num2 = (int)(Math.random() * 9 + 1);
        num3 = (int)(Math.random() * 9 + 1);
        
        // Calculate winnings
        calculateWinnings();
        
        // Update balance with winnings
        balance += winnings;
    }
    
    private void calculateWinnings() {
        // Check for 777
        if (num1 == 7 && num2 == 7 && num3 == 7) {
            winnings = 100;
        }
        // Check for 3 of a kind
        else if (num1 == num2 && num2 == num3) {
            winnings = 5;
        }
        // Check for 2 of a kind
        else if (num1 == num2 || num1 == num3 || num2 == num3) {
            winnings = 1;
        }
        // No match
        else {
            winnings = 0;
        }
    }
    
    public void drawMe(Graphics g) {
        // Draw the slot machine box
        g.setColor(Color.DARK_GRAY);
        g.fillRect(50, 50, 400, 350);
        
        g.setColor(Color.RED);
        g.fillRect(60, 60, 380, 50);
        
        // Draw title
        g.setColor(Color.WHITE);
        Font titleFont = new Font("Arial", Font.BOLD, 30);
        g.setFont(titleFont);
        g.drawString("SLOT MACHINE", 120, 95);
        
        // Draw the three numbers in the center
        g.setColor(Color.WHITE);
        g.fillRect(80, 130, 100, 100);
        g.fillRect(200, 130, 100, 100);
        g.fillRect(320, 130, 100, 100);
        
        // Draw numbers
        Font numFont = new Font("Arial", Font.BOLD, 60);
        g.setFont(numFont);
        g.setColor(Color.BLACK);
        g.drawString(String.valueOf(num1), 110, 200);
        g.drawString(String.valueOf(num2), 230, 200);
        g.drawString(String.valueOf(num3), 350, 200);
        
        // Draw balance
        Font infoFont = new Font("Arial", Font.PLAIN, 20);
        g.setFont(infoFont);
        g.setColor(Color.YELLOW);
        g.drawString("Balance: " + balance + " points", 140, 270);
        
        // Draw winnings
        g.setColor(Color.GREEN);
        g.drawString("You won: " + winnings + " points", 140, 300);
        
        // Draw payout table
        displayPayoutTable(g);
    }
    
    public void displayPayoutTable(Graphics g) {
        Font tableFont = new Font("Arial", Font.PLAIN, 16);
        g.setFont(tableFont);
        g.setColor(Color.WHITE);
        
        int startX = 520;
        int startY = 80;
        
        g.drawString("PAYOUT TABLE", startX, startY);
        g.drawString("------------------", startX, startY + 20);
        g.drawString("777 = 100 points", startX, startY + 45);
        g.drawString("3 of a kind = 5 points", startX, startY + 70);
        g.drawString("2 of a kind = 1 point", startX, startY + 95);
    }
    
    public int getBalance() {
        return balance;
    }
    
    public int getWinnings() {
        return winnings;
    }
    
    public boolean canSpin() {
        return balance >= 1;
    }
}