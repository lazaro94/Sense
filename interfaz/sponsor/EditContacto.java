package sponsor;

import java.sql.SQLException;

import javax.swing.JFrame;

import entidades.Contacto;
import generic.GenericAbm;
import logica.LogicContacto;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditContacto extends GenericAbm{

	private JFrame frameEditContacto;
	private LogicContacto lc;
	private Contacto contactoAct;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textCargo;
	private JTextField textDni;
	private JTextField textTel1;
	private JTextField textTel2;
	private JTextField textMail;
	private JTextField textDireccion;
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
		frameEditContacto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		lblTelefono.setBounds(10, 145, 79, 15);
		frameEditContacto.getContentPane().add(lblTelefono);
		
		JLabel lblTelefono_1 = new JLabel("Telefono 2:");
		lblTelefono_1.setBounds(242, 145, 92, 15);
		frameEditContacto.getContentPane().add(lblTelefono_1);
		
		JLabel lblMail = new JLabel("Mail:");
		lblMail.setBounds(30, 223, 70, 15);
		frameEditContacto.getContentPane().add(lblMail);
		
		textNombre = new JTextField();
		textNombre.setBounds(78, 27, 190, 20);
		frameEditContacto.getContentPane().add(textNombre);
		textNombre.setColumns(10);
		
		textApellido = new JTextField();
		textApellido.setBounds(78, 66, 190, 20);
		frameEditContacto.getContentPane().add(textApellido);
		textApellido.setColumns(10);
		
		textCargo = new JTextField();
		textCargo.setBounds(78, 111, 113, 20);
		frameEditContacto.getContentPane().add(textCargo);
		textCargo.setColumns(10);
		
		textDni = new JTextField();
		textDni.setBounds(278, 111, 102, 20);
		frameEditContacto.getContentPane().add(textDni);
		textDni.setColumns(10);
		
		textTel1 = new JTextField();
		textTel1.setBounds(88, 142, 128, 20);
		frameEditContacto.getContentPane().add(textTel1);
		textTel1.setColumns(10);
		
		textTel2 = new JTextField();
		textTel2.setBounds(306, 142, 128, 20);
		frameEditContacto.getContentPane().add(textTel2);
		textTel2.setColumns(10);
		
		textMail = new JTextField();
		textMail.setBounds(63, 220, 332, 20);
		frameEditContacto.getContentPane().add(textMail);
		textMail.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickGuardar();
			}
		});
		btnGuardar.setBounds(306, 263, 89, 23);
		frameEditContacto.getContentPane().add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickCancelar();
			}
		});
		btnCancelar.setBounds(109, 263, 89, 23);
		frameEditContacto.getContentPane().add(btnCancelar);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(10, 182, 57, 14);
		frameEditContacto.getContentPane().add(lblDireccion);
		
		textDireccion = new JTextField();
		textDireccion.setBounds(63, 179, 332, 20);
		frameEditContacto.getContentPane().add(textDireccion);
		textDireccion.setColumns(10);
	}
	
	public void open(Contacto c){
		frameEditContacto.setVisible(true);
		contactoAct=c;
		mapearAFormulario(c);
	}
	
	private void mapearAFormulario(Contacto c){
		textNombre.setText(c.getNombre());
		textApellido.setText(c.getApellido());
		textCargo.setText(c.getCargo());
		textDni.setText(c.getDni());
		textTel1.setText(c.getTelefono1());
		textTel2.setText(c.getTelefono2());
		textMail.setText(c.getMail());
		textDireccion.setText(c.getDireccion());
	}
	
	protected Contacto mapearDeFormulario(){
		contactoAct.setApellido(textApellido.getText());
		contactoAct.setCargo(textCargo.getText());
		contactoAct.setDireccion(textDireccion.getText());
		contactoAct.setDni(textDni.getText());
		contactoAct.setMail(textMail.getText());
		contactoAct.setNombre(textNombre.getText());
		contactoAct.setTelefono1(textTel1.getText());
		contactoAct.setTelefono2(textTel2.getText());
		return contactoAct;
	}
	
	protected void clickGuardar(){
		try{
			lc = new LogicContacto();
			contactoAct = mapearDeFormulario();
			if(contactoAct.getIdContacto()>0){
				lc.updateContacto(contactoAct);
			}
			else {
				lc.insertContacto(contactoAct);
			}
			super.informarUsuario("Contacto guardado correctamente.", "Editar Contacto");
		}
		catch(SQLException sqlex){
			super.informarError(sqlex.getMessage(), "Editar Contacto");
		}
		catch(Exception ex){
			super.informarError(ex.getMessage(), "Editar Contacto");
		}
	}

	@Override
	protected void clickCancelar() {
		// TODO Auto-generated method stub
		frameEditContacto.dispose();		
	}
}
