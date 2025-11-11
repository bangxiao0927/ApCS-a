import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Screen extends JPanel implements ActionListener {
    private final SlotMachine machine;
    private JButton spinButton;
    
    public Screen() {
        this.setLayout(null);
        this.setFocusable(true);
        
        // Initialize the slot machine with starting balance of 100
        machine = new SlotMachine(100);
        
        // Create spin button
        spinButton = new JButton("SPIN");
        spinButton.setBounds(175, 450, 150, 50);
        spinButton.addActionListener(this);
        spinButton.setBackground(Color.GREEN);
        spinButton.setForeground(Color.BLACK);
        this.add(spinButton);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Set background
        g.setColor(new Color(20, 100, 20));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        // Draw the slot machine
        machine.drawMe(g);
        
        // Display message if out of points
        if (!machine.canSpin()) {
            g.setColor(Color.RED);
            g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
            g.drawString("GAME OVER - OUT OF POINTS!", 100, 530);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == spinButton) {
            if (machine.canSpin()) {
                machine.spin();
                repaint();
            }
        }
    }
}