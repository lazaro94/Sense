package sponsor;

import javax.swing.JFrame;

import entidades.Sponsor;
import generic.GenericAbm;
import logica.LogicSponsor;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EditSponsor extends GenericAbm {

	private JFrame frmEdit;
	private JTextField txtRazonSocial;
	private JTextField txtCuit;
	private JTextField txtCalle;
	private JTextField txtNumero;
	private JLabel lblId;
	
	private LogicSponsor ls = null;
	private JTextField txtComentario;

	/**
	 * Create the application.
	 */
	public EditSponsor(){
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEdit = new JFrame();
		frmEdit.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
			}
		});
		frmEdit.setBounds(100, 100, 450, 300);
		frmEdit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEdit.getContentPane().setLayout(null);
		
		lblId = new JLabel("Id");
		lblId.setBounds(368, 12, 70, 15);
		frmEdit.getContentPane().add(lblId);
		lblId.setVisible(false);
		
		JLabel lblRazonSocial = new JLabel("Razon Social:");
		lblRazonSocial.setBounds(12, 30, 104, 15);
		frmEdit.getContentPane().add(lblRazonSocial);
		
		txtRazonSocial = new JTextField();
		txtRazonSocial.setBounds(120, 28, 205, 19);
		frmEdit.getContentPane().add(txtRazonSocial);
		txtRazonSocial.setColumns(10);
		
		JLabel lblCuit = new JLabel("CUIT:");
		lblCuit.setBounds(12, 70, 70, 15);
		frmEdit.getContentPane().add(lblCuit);
		
		txtCuit = new JTextField();
		txtCuit.setBounds(55, 68, 140, 19);
		frmEdit.getContentPane().add(txtCuit);
		txtCuit.setColumns(10);
		
		JLabel lblCalle = new JLabel("Calle:");
		lblCalle.setBounds(12, 106, 70, 15);
		frmEdit.getContentPane().add(lblCalle);
		
		txtCalle = new JTextField();
		txtCalle.setBounds(65, 104, 182, 19);
		frmEdit.getContentPane().add(txtCalle);
		txtCalle.setColumns(10);
		
		JLabel lblNumero = new JLabel("Numero:");
		lblNumero.setBounds(265, 106, 70, 15);
		frmEdit.getContentPane().add(lblNumero);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(335, 104, 88, 19);
		frmEdit.getContentPane().add(txtNumero);
		txtNumero.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickGuardar();
			}
		});
		btnGuardar.setBounds(247, 206, 117, 37);
		frmEdit.getContentPane().add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickCancelar();
			}
		});
		btnCancelar.setBounds(94, 206, 117, 37);
		frmEdit.getContentPane().add(btnCancelar);
		
		JLabel lblComentario = new JLabel("Comentario:");
		lblComentario.setBounds(12, 152, 104, 15);
		frmEdit.getContentPane().add(lblComentario);
		
		txtComentario = new JTextField();
		txtComentario.setBounds(105, 150, 333, 19);
		frmEdit.getContentPane().add(txtComentario);
		txtComentario.setColumns(10);
	}
	
	protected void open(Sponsor s){
		mapearAFormulario(s);
		frmEdit.setVisible(true);		
	}
	
	private void mapearAFormulario(Sponsor s){
		lblId.setText(Integer.toString(s.getId()));
		txtCuit.setText(s.getCuit());
		txtCalle.setText(s.getCalle());
		txtRazonSocial.setText(s.getRazonSocial());
		txtNumero.setText(s.getNumero());
		txtComentario.setText(s.getComentario());
	}
	
	@Override
	protected Sponsor mapearDeFormulario(){
		 Sponsor s = new Sponsor(Integer.parseInt(lblId.getText()), txtRazonSocial.getText(), txtCuit.getText(), txtCalle.getText(), txtNumero.getText(), txtComentario.getText());
		 return s;
	}
	
	@Override
	protected void clickCancelar(){
		frmEdit.dispose();
	}
	
	@Override
	protected void clickGuardar(){
		Sponsor s = new Sponsor();
		s=mapearDeFormulario();
		ViewSponsor abm = new ViewSponsor();
		try{
			ls = new LogicSponsor();
			if(s.getId()>0){
				ls.updateSponsor(s);
				super.informarUsuario("Sponsor actualizado correctamente", "Modificar Sponsors");
			}
			else {
				ls.InsertSponsor(s);
				super.informarUsuario("Sponsor ingresado correctamente", "Modificar Sponsors");
			}
			abm.actualizarTabla();
			frmEdit.dispose();
		}
		catch(SQLException sqlex){
			super.informarError(sqlex.getMessage(), "Modificar sponsors");
		}
		catch(Exception ex){
			super.informarError(ex.getMessage(), "Modificar sponsors");			
		}
	}
}
