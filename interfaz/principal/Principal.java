package principal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;

import contratos.PanelContrato;
import gestionCobro.PanelGestionCobro;
import sponsors.PanelSponsor;

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
		panelizq.add(btnSponsors);
		
		JLabel label_1 = new JLabel("  ");
		panelizq.add(label_1);
		
		JButton btnContratos = new JButton("Contratos");
		btnContratos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickPublicidades();
			}
		});
		panelizq.add(btnContratos);
		
		JLabel label_2 = new JLabel("  ");
		panelizq.add(label_2);
		
		JButton btnGestionCobros = new JButton("Gestion Cobros");
		btnGestionCobros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestionCobros();
			}
		});
		panelizq.add(btnGestionCobros);
		
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
		PanelContrato publicidades = new PanelContrato();
		panelCentral.removeAll();
		panelCentral.add(publicidades);
		panelCentral.revalidate();
		panelCentral.repaint();
	}
	private void gestionCobros(){
		PanelGestionCobro gc = new PanelGestionCobro();
		panelCentral.removeAll();
		panelCentral.add(gc);
		panelCentral.revalidate();
		panelCentral.repaint();
	}

}
