package sponsor;

import java.sql.SQLException;

import javax.swing.JFrame;

import entidades.Contacto;
import generic.GenericABM;
import logica.LogicContacto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class EditContacto extends GenericABM{

	private JFrame frameEditContacto;
	private LogicContacto lc;
	private Contacto contactoAct;
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
		frameEditContacto.setBounds(100, 100, 499, 336);
		frameEditContacto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameEditContacto.getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(30, 30, 70, 15);
		frameEditContacto.getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(30, 69, 70, 15);
		frameEditContacto.getContentPane().add(lblApellido);
		
		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setBounds(30, 114, 70, 15);
		frameEditContacto.getContentPane().add(lblCargo);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setBounds(242, 114, 70, 15);
		frameEditContacto.getContentPane().add(lblDni);
		
		JLabel lblTelefono = new JLabel("Telefono 1:");
		lblTelefono.setBounds(30, 173, 79, 15);
		frameEditContacto.getContentPane().add(lblTelefono);
		
		JLabel lblTelefono_1 = new JLabel("Telefono 2:");
		lblTelefono_1.setBounds(242, 173, 92, 15);
		frameEditContacto.getContentPane().add(lblTelefono_1);
		
		JLabel lblMail = new JLabel("Mail:");
		lblMail.setBounds(30, 223, 70, 15);
		frameEditContacto.getContentPane().add(lblMail);
	}
	
	public void open(Contacto c){
		frameEditContacto.setVisible(true);
		contactoAct=c;
		mapearAFormulario(c);
	}
	
	private void mapearAFormulario(Contacto c){
		
	}
	
	protected Contacto mapearDeFormulario(){
		
		return contactoAct;
	}
	
	protected void clickGuardar(){
		try{
			lc = new LogicContacto();
			if(contactoAct.getIdContacto()>0){
				lc.updateContacto(contactoAct);
			}
			else {
				lc.insertContacto(contactoAct);
			}
		}
		catch(SQLException sqlex){
			informarError(sqlex.getMessage(), "Editar Contacto");
		}
		catch(Exception ex){
			informarError(ex.getMessage(), "Editar contacto");
		}
	}
	protected void informarError(String mensaje, String titulo){
		JOptionPane.showMessageDialog(frameEditContacto, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
	}
	protected void informarUsuario(String mensaje, String titulo){
		JOptionPane.showMessageDialog(frameEditContacto, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	protected void clickCancelar() {
		// TODO Auto-generated method stub
		frameEditContacto.dispose();		
	}
}
