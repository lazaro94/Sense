package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal {

	private JFrame frame;
	private JPanel panelCentral;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel label = new JLabel("   ");
		panel.add(label);
		
		JButton btnSponsors = new JButton("Sponsors");
		btnSponsors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickSponsors();
			}
		});
		panel.add(btnSponsors);
		
		panelCentral = new JPanel();
		frame.getContentPane().add(panelCentral, BorderLayout.CENTER);
	
	}
	
	private void clickSponsors(){
		PanelABMSponsor abm = new PanelABMSponsor();
		panelCentral.removeAll();
		panelCentral.add(abm, BorderLayout.CENTER);
		panelCentral.revalidate();
		panelCentral.repaint();
	}

}
