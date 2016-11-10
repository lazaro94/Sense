package publicidad;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.BoxLayout;

public class Publicidades extends JPanel {

	private JTabbedPane tabbedPane;
	
	public Publicidades() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);
		addTabs();

	}
	
	private void addTabs(){
		ViewContratos vc = new ViewContratos();
		tabbedPane.addTab("Contratos", vc);
		
		tabbedPane.addTab("Informes", null);
	}

}
