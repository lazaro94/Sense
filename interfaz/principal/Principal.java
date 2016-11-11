package principal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;


import gestionCobro.PanelGestionCobro;
import publicidad.Publicidades;
import sponsor.PanelSponsor;
import sponsor.ViewSponsor;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal {

	private JFrame frmSenseFilms;
	private JPanel panelCentral;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frmSenseFilms.setVisible(true);
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
		frmSenseFilms = new JFrame();
		frmSenseFilms.setTitle("Sense Films");
		frmSenseFilms.setBounds(100, 100, 1020, 740);
		frmSenseFilms.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSenseFilms.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelizq = new JPanel();
		frmSenseFilms.getContentPane().add(panelizq, BorderLayout.WEST);
		panelizq.setLayout(new BoxLayout(panelizq, BoxLayout.Y_AXIS));
		
		JLabel label = new JLabel("   ");
		panelizq.add(label);
		
		JButton btnSponsors = new JButton("Sponsors");
		btnSponsors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickSponsors();
			}
		});
		
		JLabel lblAdministracion = new JLabel(" Administracion");
		panelizq.add(lblAdministracion);
		panelizq.add(btnSponsors);
		
		JLabel label_1 = new JLabel("  ");
		panelizq.add(label_1);
		
		JButton btnPublicidades = new JButton("Publicidades");
		btnPublicidades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickPublicidades();
			}
		});
		
		JLabel lblGestiones = new JLabel(" Gestiones");
		panelizq.add(lblGestiones);
		panelizq.add(btnPublicidades);
		panelCentral = new JPanel();
		frmSenseFilms.getContentPane().add(panelCentral, BorderLayout.CENTER);
	
	}
	
	private void clickSponsors(){
		PanelSponsor abm = new PanelSponsor();
		panelCentral.removeAll();
		panelCentral.add(abm, BorderLayout.CENTER);
		panelCentral.revalidate();
		panelCentral.repaint();
	}
	private void clickPublicidades(){
		Publicidades publi = new Publicidades();
		panelCentral.removeAll();
		panelCentral.add(publi, BorderLayout.CENTER);
		panelCentral.revalidate();
		panelCentral.repaint();
	}

}
