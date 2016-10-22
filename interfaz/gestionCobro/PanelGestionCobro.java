package gestionCobro;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class PanelGestionCobro extends JPanel {

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
		panelMenu.add(btnRegistrarPago);
		

	}

}
