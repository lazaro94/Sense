package interfaz;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;

public class Administracion extends JPanel {

	/**
	 * Create the panel.
	 */
	public Administracion() {
		setBorder(new TitledBorder(null, "Administracion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JButton btnSponsors = new JButton("Sponsors");
		add(btnSponsors);
		
		JLabel label = new JLabel("  ");
		add(label);
		
		JButton btnClientes = new JButton("Clientes  ");
		add(btnClientes);

	}

}
