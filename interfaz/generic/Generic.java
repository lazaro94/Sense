package generic;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public abstract class Generic extends JPanel {
	
	protected void informarError(String mensaje, String titulo){
		JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
	}

	protected void informarUsuario(String mensaje, String titulo){
		JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
	}
	
	protected boolean confirmarUsuario(String mensaje, String titulo){
		int result = JOptionPane.showConfirmDialog (null, mensaje, titulo, JOptionPane.WARNING_MESSAGE);
		if(result==JOptionPane.YES_OPTION){
			return true;
		}
		else{
			return false;
		}
	}
}
