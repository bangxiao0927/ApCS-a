import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class Screen extends JPanel implements ActionListener{		
	private JTextField pinInput;
	private JButton loginButton;
	private JTextField withdrawOrDepositInput;
	private JButton withdrawButton;
	private JButton depositButton;
	private JButton logoutButton;

	private Bank customer;


	public Screen() {
		setLayout( null );

		customer = new Bank("John", 100.99, 1234);

		//pin input
		pinInput = new JTextField();
		pinInput.setBounds( 50, 30, 100, 30 );
		add( pinInput );

		//login button
		loginButton = new JButton("Login");
		loginButton.setBounds( 200, 30, 100, 30 );

		add( loginButton );

		//listener
		loginButton.addActionListener( this ::actionPerformed );

		//login button
		logoutButton = new JButton("Logout");
		logoutButton.setBounds( 310, 30, 100, 30 );

		add( logoutButton );

		//listener
		logoutButton.addActionListener( this ::actionPerformed );

		//withdraw/deposit input
		withdrawOrDepositInput = new JTextField();
		withdrawOrDepositInput.setBounds( 50, 60, 100, 30 );

		//withdraw button
		withdrawButton = new JButton("Withdraw");
		withdrawButton.setBounds( 200, 60, 100, 30 );


		//withdraw button
		depositButton = new JButton("Deposit");
		depositButton.setBounds( 310, 60, 100, 30 );

		
		//listener
		withdrawButton.addActionListener( this ::actionPerformed );
		depositButton.addActionListener( this ::actionPerformed );

	}

	@Override
	public Dimension getPreferredSize(){
		return new Dimension(800,600);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		if (customer.getAccess() == true ) {
			g.drawString(customer.getName(), 50, 220);
			g.drawString(customer.getBalance() + "", 80, 220);
		}
	}

	public void actionPerformed(ActionEvent e) {
		//login button trigger
		if (e.getSource() == loginButton) {
			//grab pin input
			String inputPin = pinInput.getText();
			customer.checkPin(Integer.parseInt(inputPin));
			//display name and balance if pin correct
			repaint();
			//add withdraw/deposit input and buttons
			if (customer.getAccess() == true ) {
				add( depositButton );
				add( withdrawButton );
				add( withdrawOrDepositInput );
				repaint();
			}
		}
		if (e.getSource() == logoutButton) {
			customer.logout();
			removeAll();
			add( pinInput );
			add( loginButton );
			repaint();
		}
		if (e.getSource() == withdrawButton) {
			String inputAmount = withdrawOrDepositInput.getText();
			customer.withdraw(Double.parseDouble(inputAmount));
			repaint();
		}
		if (e.getSource() == depositButton) {
			String inputAmount = withdrawOrDepositInput.getText();
			customer.deposit(Double.parseDouble(inputAmount));
			repaint();
		}
	}
}














