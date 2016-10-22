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
		
		JPanel panelizq = new JPanel();
		frame.getContentPane().add(panelizq, BorderLayout.WEST);
		panelizq.setLayout(new BoxLayout(panelizq, BoxLayout.Y_AXIS));
		
		JLabel label = new JLabel("   ");
		panelizq.add(label);
		
		JButton btnSponsors = new JButton("Sponsors");
		btnSponsors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickSponsors();
			}
		});
		panelizq.add(btnSponsors);
		
		JLabel label_1 = new JLabel("  ");
		panelizq.add(label_1);
		
		JButton btnPublicidades = new JButton("Publicidades");
		btnPublicidades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickPublicidades();
			}
		});
		panelizq.add(btnPublicidades);
		
		panelCentral = new JPanel();
		frame.getContentPane().add(panelCentral, BorderLayout.CENTER);
	
	}
	
	private void clickSponsors(){
		PanelSponsor abm = new PanelSponsor();
		panelCentral.removeAll();
		panelCentral.add(abm, BorderLayout.CENTER);
		panelCentral.revalidate();
		panelCentral.repaint();
	}
	
	private void clickPublicidades(){
		PanelPublicidades publicidades = new PanelPublicidades();
		panelCentral.removeAll();
		panelCentral.add(publicidades);
		panelCentral.revalidate();
		panelCentral.repaint();
	}

}
