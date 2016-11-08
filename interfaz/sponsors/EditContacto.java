package sponsors;

import java.awt.EventQueue;

import javax.swing.JFrame;

import entidades.Contacto;

public class EditContacto {

	private JFrame frameEditContacto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditContacto window = new EditContacto();
					window.frameEditContacto.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EditContacto() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameEditContacto = new JFrame();
		frameEditContacto.setBounds(100, 100, 450, 300);
		frameEditContacto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void open(Contacto c){
		frameEditContacto.setVisible(true);
		mapearAFormulario(c);
	}
	
	private void mapearAFormulario(Contacto c){
		
	}

}
