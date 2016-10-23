package gestionCobro;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelGestionCobro extends JPanel {
	private JPanel panelCentral;

	/**
	 * Create the panel.
	 */
	public PanelGestionCobro() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelMenu = new JPanel();
		add(panelMenu, BorderLayout.NORTH);
		panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.X_AXIS));
		
		JLabel label = new JLabel("  ");
		panelMenu.add(label);
		
		JButton btnCobrosPendientes = new JButton("Cobros Pendientes");
		panelMenu.add(btnCobrosPendientes);
		
		JLabel label_1 = new JLabel("   ");
		panelMenu.add(label_1);
		
		JButton btnRegistrarPago = new JButton("Registrar Pago");
		btnRegistrarPago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrarPago();
			}
		});
		panelMenu.add(btnRegistrarPago);
		
		panelCentral = new JPanel();
		add(panelCentral, BorderLayout.CENTER);
		

	}
	
	private void registrarPago(){
		RegistrarCobro rc = new RegistrarCobro();
		panelCentral.removeAll();
		panelCentral.add(rc);
		panelCentral.revalidate();
		panelCentral.repaint();
	}

}
