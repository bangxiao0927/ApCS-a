import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Screen extends JPanel implements Runnable, ActionListener {
    private SlotMachine machine;
    private JButton spinButton;
    private JButton bet1Button;
    private JButton bet5Button;
    private JButton bet10Button;
    
    public Screen() {
        this.setLayout(null);
        this.setFocusable(true);
        this.setBackground(Color.WHITE);
        
        machine = new SlotMachine(100);
        
        // Start animation thread
        Thread t = new Thread(this);
        t.start();
        
        //bet 1 button
        bet1Button = new JButton("Bet 1");
        bet1Button.setBounds(50, 450, 100, 50);
        bet1Button.addActionListener(this);
        bet1Button.setBackground(Color.YELLOW);
        bet1Button.setForeground(Color.BLACK);
        bet1Button.setFont(new Font("Arial", Font.BOLD, 16));
        this.add(bet1Button);
        
        //bet 5 button
        bet5Button = new JButton("Bet 5");
        bet5Button.setBounds(160, 450, 100, 50);
        bet5Button.addActionListener(this);
        bet5Button.setBackground(Color.LIGHT_GRAY);
        bet5Button.setForeground(Color.BLACK);
        bet5Button.setFont(new Font("Arial", Font.BOLD, 16));
        this.add(bet5Button);
        
        //bet 10 button
        bet10Button = new JButton("Bet 10");
        bet10Button.setBounds(270, 450, 100, 50);
        bet10Button.addActionListener(this);
        bet10Button.setBackground(Color.LIGHT_GRAY);
        bet10Button.setForeground(Color.BLACK);
        bet10Button.setFont(new Font("Arial", Font.BOLD, 16));
        this.add(bet10Button);
        
        //spin button
        spinButton = new JButton("SPIN");
        spinButton.setBounds(380, 450, 150, 50);
        spinButton.addActionListener(this);
        spinButton.setBackground(Color.GREEN);
        spinButton.setForeground(Color.BLACK);
        spinButton.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(spinButton);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight()); //background
        
        machine.drawMe(g); //draw slot machine
        
        if (!(machine.canSpin()) && !(machine.isSpinning())) { // not enough points & not spinning
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("INSUFFICIENT POINTS - Lower your bet!", 60, 530);
        }
        
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 14)); // set the font using
        g.drawString("Select your bet amount, then press SPIN!", 50, 520);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == spinButton) {
            if (machine.canSpin()) {
                machine.spin();
                repaint();
            }
        } else if (e.getSource() == bet1Button) {
            if (!machine.isSpinning()) {
                machine.setBet(1);
                highlightSelectedBet(1);
                repaint();
            }
        } else if (e.getSource() == bet5Button) {
            if (!machine.isSpinning()) {
                machine.setBet(5);
                highlightSelectedBet(5);
                repaint();
            }
        } else if (e.getSource() == bet10Button) {
            if (!machine.isSpinning()) {
                machine.setBet(10);
                highlightSelectedBet(10);
                repaint();
            }
        }
    }
    
    private void highlightSelectedBet(int bet) {
        bet1Button.setBackground(Color.LIGHT_GRAY);
        bet5Button.setBackground(Color.LIGHT_GRAY);
        bet10Button.setBackground(Color.LIGHT_GRAY);
        
        switch (bet) {
            case 1:
                bet1Button.setBackground(Color.YELLOW);
                break;
            case 5:
                bet5Button.setBackground(Color.YELLOW);
                break;
            case 10:
                bet10Button.setBackground(Color.YELLOW);
                break;
            default:
                break;
        }
    }
    
    @Override
    public void run() {
        while (true) {
            machine.updateAnimation();
            repaint();
            
            try {
                Thread.sleep(16); // ~60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}