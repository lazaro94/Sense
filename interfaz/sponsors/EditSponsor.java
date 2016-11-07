package sponsors;

import java.awt.EventQueue;

import javax.swing.JFrame;

import entidades.Sponsor;
import logica.LogicSponsors;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EditSponsor {

	private JFrame frmEdit;
	private JTextField txtRazonSocial;
	private JTextField txtCuit;
	private JTextField txtCalle;
	private JTextField txtNumero;
	private JLabel lblId;
	
	private LogicSponsors ls = null;
	private JTextField txtComentario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditSponsor window = new EditSponsor();
					window.frmEdit.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EditSponsor() {
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
	
	public void open(Sponsor s){
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
	
	private Sponsor mapearDeFormulario(){
		 Sponsor s = new Sponsor(Integer.parseInt(lblId.getText()), txtRazonSocial.getText(), txtCuit.getText(), txtCalle.getText(), txtNumero.getText(), txtComentario.getText());
		 return s;
	}
	
	private void clickCancelar(){
		frmEdit.dispose();
	}
	
	private void clickGuardar(){
		Sponsor s = new Sponsor();
		s=mapearDeFormulario();
		PanelSponsor abm = new PanelSponsor();
		try{
			ls = new LogicSponsors();
			if(s.getId()>0){
				ls.updateSponsor(s);
				informarUsuario("Sponsor actualizado correctamente", "Modificar Sponsors");
			}
			else {
				ls.InsertSponsor(s);
				informarUsuario("Sponsor ingresado correctamente", "Modificar Sponsors");
			}
			abm.actualizarTabla();
			frmEdit.dispose();
		}
		catch(SQLException sqlex){
			informarError(sqlex.getMessage(), "Modificar sponsors");
		}
		catch(Exception ex){
			informarError(ex.getMessage(), "Modificar sponsors");			
		}
	}
	
	private void informarError(String mensaje, String titulo){
		JOptionPane.showMessageDialog(frmEdit, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
	}
	private void informarUsuario(String mensaje, String titulo){
		JOptionPane.showMessageDialog(frmEdit, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
	}
}
