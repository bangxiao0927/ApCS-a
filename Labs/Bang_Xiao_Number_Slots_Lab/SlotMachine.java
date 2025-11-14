import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class SlotMachine {
    private int num1;
    private int num2;
    private int num3;
    private int winnings;
    private int balance;
    private int currentBet;
    private boolean isSpinning;
    private int spinFrame;
    private static final int SPIN_DURATION = 30;
    
    public SlotMachine(int balance) {
        this.balance = balance;
        this.winnings = 0;
        this.num1 = 0;
        this.num2 = 0;
        this.num3 = 0;
        this.currentBet = 1;
        this.isSpinning = false;
        this.spinFrame = 0;
    }
    
    public void setBet(int bet) {
        this.currentBet = bet;
    }
    
    public int getBet() {
        return currentBet;
    }
    
    public boolean isSpinning() {
        return isSpinning;
    }
    
    public void updateAnimation() {
        if (isSpinning) {
            spinFrame++;
            if (spinFrame >= SPIN_DURATION) {
                isSpinning = false;
                spinFrame = 0;
                calculateWinnings();
                balance += winnings;
            }
        }
    }
    
    public void spin() {
        if (balance < currentBet || isSpinning) {
            return;
        }
        balance -= currentBet;
        isSpinning = true;
        spinFrame = 0;
        num1 = (int)(Math.random() * 9 + 1);
        num2 = (int)(Math.random() * 9 + 1);
        num3 = (int)(Math.random() * 9 + 1);
    }
    
    private void calculateWinnings() {
        int baseWinnings = 0;
        if (num1 == 7 && num2 == 7 && num3 == 7) {
            baseWinnings = 100;
        } else if (num1 == num2 && num2 == num3) {
            baseWinnings = 5;
        } else if (num1 == num2 || num1 == num3 || num2 == num3) {
            baseWinnings = 1;
        } else {
            baseWinnings = 0;
        }
        winnings = baseWinnings * currentBet;
    }
    
    private int getDisplayNumber(int slotNumber, int frameOffset) {
        if (!isSpinning) {
            return slotNumber;
        }
        return (int)(Math.random() * 9 + 1);
    }
    
    public void drawMe(Graphics g) {
        g.setColor(new Color(200, 200, 200));
        g.fillRect(50, 50, 400, 380);
        g.setColor(new Color(220, 50, 50));
        g.fillRect(60, 60, 380, 50);
        g.setColor(Color.WHITE);
        Font titleFont = new Font("Arial", Font.BOLD, 30);
        g.setFont(titleFont);
        g.drawString("SLOT MACHINE", 120, 95);
        g.setColor(Color.WHITE);
        g.fillRect(80, 130, 100, 100);
        g.fillRect(200, 130, 100, 100);
        g.fillRect(320, 130, 100, 100);
        g.setColor(Color.BLACK);
        g.drawRect(80, 130, 100, 100);
        g.drawRect(200, 130, 100, 100);
        g.drawRect(320, 130, 100, 100);
        Font numFont = new Font("Arial", Font.BOLD, 60);
        g.setFont(numFont);
        g.setColor(Color.BLACK);
        int display1 = getDisplayNumber(num1, 0);
        int display2 = getDisplayNumber(num2, 5);
        int display3 = getDisplayNumber(num3, 10);
        g.drawString(String.valueOf(display1), 110, 200);
        g.drawString(String.valueOf(display2), 230, 200);
        g.drawString(String.valueOf(display3), 350, 200);
        Font infoFont = new Font("Arial", Font.PLAIN, 20);
        g.setFont(infoFont);
        g.setColor(new Color(0, 100, 200));
        g.drawString("Current Bet: " + currentBet + " points", 130, 270);
        g.setColor(new Color(200, 150, 0));
        g.drawString("Balance: " + balance + " points", 140, 300);
        if (winnings > 0 && !isSpinning) {
            g.setColor(new Color(0, 150, 0));
            g.drawString("You won: " + winnings + " points!", 130, 330);
        } else if (!isSpinning) {
            g.setColor(new Color(150, 0, 0));
            g.drawString("You won: " + winnings + " points", 130, 330);
        } else {
            g.setColor(Color.BLACK);
            g.drawString("Spinning...", 170, 330);
        }
        g.setColor(Color.BLACK);
        Font smallFont = new Font("Arial", Font.PLAIN, 14);
        g.setFont(smallFont);
        g.drawString("Winnings = Payout × Bet", 120, 360);
        displayPayoutTable(g);
    }
    
    public void displayPayoutTable(Graphics g) {
        Font tableFont = new Font("Arial", Font.PLAIN, 16);
        g.setFont(tableFont);
        g.setColor(Color.BLACK);
        int startX = 520;
        int startY = 80;
        g.drawString("BASE PAYOUT TABLE", startX, startY);
        g.drawString("----------------------", startX, startY + 20);
        g.drawString("777 = 100× bet", startX, startY + 45);
        g.drawString("3 of a kind = 5× bet", startX, startY + 70);
        g.drawString("2 of a kind = 1× bet", startX, startY + 95);
        Font exampleFont = new Font("Arial", Font.PLAIN, 14);
        g.setFont(exampleFont);
        g.setColor(new Color(0, 100, 200));
        g.drawString("EXAMPLES (Bet: " + currentBet + ")", startX, startY + 145);
        g.drawString("----------------------", startX, startY + 160);
        g.setColor(Color.BLACK);
        g.drawString("777 = " + (100 * currentBet) + " points", startX, startY + 180);
        g.drawString("3 of a kind = " + (5 * currentBet) + " points", startX, startY + 200);
        g.drawString("2 of a kind = " + (1 * currentBet) + " points", startX, startY + 220);
    }
    
    public int getBalance() {
        return balance;
    }
    
    public int getWinnings() {
        return winnings;
    }
    
    public boolean canSpin() {
        return balance >= currentBet && !isSpinning;
    }
}