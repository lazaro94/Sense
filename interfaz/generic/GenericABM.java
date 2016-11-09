package generic;

import javax.swing.JOptionPane;

public abstract class GenericABM {
	
	protected void informarError(String mensaje, String titulo){
		JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
	}

	protected void informarUsuario(String mensaje, String titulo){
		JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
	}
	
	protected abstract void clickGuardar();
	
	protected abstract void clickCancelar();
	
	protected abstract Object mapearDeFormulario();
}
