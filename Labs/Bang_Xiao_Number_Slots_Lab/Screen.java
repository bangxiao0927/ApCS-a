import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Screen extends JPanel implements ActionListener {
    private SlotMachine machine;
    private JButton spinButton;
    private JButton bet1Button;
    private JButton bet5Button;
    private JButton bet10Button;
    private Timer animationTimer;
    
    public Screen() {
        this.setLayout(null);
        this.setFocusable(true);
        this.setBackground(Color.WHITE);
        
        // Initialize the slot machine with starting balance of 100
        machine = new SlotMachine(100);
        
        // Create animation timer (runs at ~60 FPS)
        animationTimer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                machine.updateAnimation();
                repaint();
            }
        });
        animationTimer.start();
        
        // Create bet selection buttons
        bet1Button = new JButton("Bet 1");
        bet1Button.setBounds(50, 450, 100, 50);
        bet1Button.addActionListener(this);
        bet1Button.setBackground(Color.YELLOW);
        bet1Button.setForeground(Color.BLACK);
        bet1Button.setFont(new Font("Arial", Font.BOLD, 16));
        this.add(bet1Button);
        
        bet5Button = new JButton("Bet 5");
        bet5Button.setBounds(160, 450, 100, 50);
        bet5Button.addActionListener(this);
        bet5Button.setBackground(Color.LIGHT_GRAY);
        bet5Button.setForeground(Color.BLACK);
        bet5Button.setFont(new Font("Arial", Font.BOLD, 16));
        this.add(bet5Button);
        
        bet10Button = new JButton("Bet 10");
        bet10Button.setBounds(270, 450, 100, 50);
        bet10Button.addActionListener(this);
        bet10Button.setBackground(Color.LIGHT_GRAY);
        bet10Button.setForeground(Color.BLACK);
        bet10Button.setFont(new Font("Arial", Font.BOLD, 16));
        this.add(bet10Button);
        
        // Create spin button
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
        
        // Set white background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        // Draw the slot machine
        machine.drawMe(g);
        
        // Display message if out of points
        if (!machine.canSpin() && !machine.isSpinning()) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("INSUFFICIENT POINTS - Lower your bet!", 60, 530);
        }
        
        // Draw instructions
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 14));
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
        // Reset all buttons to light gray
        bet1Button.setBackground(Color.LIGHT_GRAY);
        bet5Button.setBackground(Color.LIGHT_GRAY);
        bet10Button.setBackground(Color.LIGHT_GRAY);
        
        // Highlight the selected bet button
        if (bet == 1) {
            bet1Button.setBackground(Color.YELLOW);
        } else if (bet == 5) {
            bet5Button.setBackground(Color.YELLOW);
        } else if (bet == 10) {
            bet10Button.setBackground(Color.YELLOW);
        }
    }
}