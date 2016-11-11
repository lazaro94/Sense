package publicidad;

import javax.swing.JFrame;

import generic.GenericAbm;

public class NewPago extends GenericAbm{

	private JFrame frame;

	public NewPago() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	protected void clickGuardar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void clickCancelar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Object mapearDeFormulario() {
		// TODO Auto-generated method stub
		return null;
	}

}
