import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Table extends JPanel implements ActionListener {

	private JButton hitButton;


	public Table() {
		setLayout(null);


		hitButton = new JButton("Hit");
    	hitButton.setBounds(50, 500, 100, 30); //x, y, width, height
        add( hitButton ); //add it to the JPanel
        hitButton.addActionListener( this );


	}
	
	public Dimension getPreferredSize() {
		//Sets the size of the panel
		return new Dimension(1000,600);
	}


	public void paintComponent(Graphics g){	
		super.paintComponent(g);


	
	}


	public void actionPerformed(ActionEvent e){
        //When a button gets pressed, this method gets called
        if( e.getSource() == hitButton ){
            
 
        }




		//refresh the screen
    	repaint();
        System.out.println("nigger");


    }


	
	
}
