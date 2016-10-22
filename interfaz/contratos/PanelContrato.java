package contratos;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import entidades.Sponsor;
import logica.LogicContrato;
import logica.LogicSponsors;
import util.AppException;
import util.Validate;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JComboBox;

public class PanelContrato extends JPanel {
	private JTextField txtFechaIni;
	private JTextField txtFechaFin;
	private JTextField txtMonto;
	private JLabel lblSponsor;
	private JComboBox comboSponsors;
	private LogicContrato lc = null;
	private JComboBox<String> comboDias;
	private JTextField txtComentario;
	private JTextField txtCodigo;

	/**
	 * Create the panel.
	 */
	public PanelContrato() {
		
		txtFechaIni = new JTextField();
		txtFechaIni.setColumns(10);
		
		JLabel lblFechaDeInicio = new JLabel("Fecha de Inicio:");
		
		JLabel lblFechaDeFin = new JLabel("Fecha de fin:");
		
		txtFechaFin = new JTextField();
		txtFechaFin.setColumns(10);
		
		JLabel lblMonto = new JLabel("Monto:");
		
		txtMonto = new JTextField();
		txtMonto.setColumns(10);
		
		JLabel lblFechaDePago = new JLabel("Dia de pago:");
		
		lblSponsor = new JLabel("Sponsor:");
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickGuardar();
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickCancelar();
			}
		});
		
		comboSponsors = new JComboBox();
		
		comboDias = new JComboBox<String>();
		for(int i=1; i<=30; i++){
			comboDias.addItem(String.valueOf(i));
		}
		
		JLabel lblComentario = new JLabel("Comentario:");
		
		txtComentario = new JTextField();
		txtComentario.setColumns(10);
		
		JLabel label = new JLabel("<Nuevo Contrato>");
		
		JLabel lblCodigo = new JLabel("Codigo:");
		
		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblFechaDeInicio)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(txtFechaIni, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblFechaDeFin)
							.addGap(17)
							.addComponent(txtFechaFin, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblMonto)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(12)
									.addComponent(btnCancelar))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtMonto, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblFechaDePago)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboDias, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)))
							.addGap(139)))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(292, Short.MAX_VALUE)
					.addComponent(btnGuardar)
					.addGap(94))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblSponsor)
						.addComponent(lblComentario))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(comboSponsors, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblCodigo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtComentario, GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(98)
					.addComponent(label)
					.addContainerGap(249, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(39)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSponsor)
						.addComponent(comboSponsors, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCodigo)
						.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtComentario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblComentario))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFechaFin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtFechaIni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFechaDeInicio)
						.addComponent(lblFechaDeFin))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMonto)
						.addComponent(txtMonto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFechaDePago)
						.addComponent(comboDias, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGuardar)
						.addComponent(btnCancelar))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
		llenarCombo();
	}

	private void llenarCombo(){
		ArrayList<Sponsor> sponsors = new ArrayList<Sponsor>();
		LogicSponsors ls = new LogicSponsors();
		try{
			sponsors = ls.getSponsors();
			for (int i=0; i<sponsors.size(); i++){
				comboSponsors.addItem(sponsors.get(i));
			}
		}
		catch(SQLException sqlex){
			informarError(sqlex.getMessage(), "Consultar Sponsors");
		}
		catch(Exception ex){
			informarError(ex.getMessage(), "Consultar Sponsors");
		}
		
		
	}
	private void informarError(String mensaje, String titulo){
		JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
	}
	private void informarUsuario(String mensaje, String titulo){
		JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void clickCancelar(){
		this.removeAll();
	}
	private void clickGuardar(){
		/*
		 * Validar que los campos no estén vacíos.
		 * Validar que se haya seleccionado un Sponsor.
		 * Validar que el formato de fechas sea correcto.
		 * Validar que la fecha fin no sea menor que la fecha inicio.
		 * Validar que se haya seleccionado un elemento del combo de dias.
		 * Validar que el monto contenga sólo numeros.
		 */
		if(!validarSeleccion()){
			return;
		}
		lc= new LogicContrato();
		Sponsor s = new Sponsor();
		Double monto;
		try{
			monto = Double.parseDouble(txtMonto.getText());
			s.setRazonSocial(String.valueOf(comboSponsors.getSelectedItem()));
			lc.createPublicidad(s, convertFecha(txtFechaIni.getText()), convertFecha(txtFechaFin.getText()), Integer.parseInt(comboDias.getSelectedItem().toString()), monto, txtCodigo.getText());
			informarUsuario("Contrato guardado correctamente", "Agregar Contrato");
		}
		catch(ParseException pex){
			informarError(pex.getMessage(), "Agregar Contrato");
		}
		catch(SQLException sqlex){
			informarError(sqlex.getMessage(), "Agregar Contrato");
		}
		catch(Exception ex){
			informarError(ex.getMessage(), "Agregar Contrato");
		}		
	}
	
	private boolean validarSeleccion(){
		Validate v = new Validate();
		String[] campos = {txtFechaIni.getText(), txtFechaFin.getText(), txtMonto.getText()};
		String[] monto = {txtMonto.getText()};
		if(!v.notEmpty(campos)){
			informarError("Estos campos no pueden quedar vacios.", "Guardar Contrato.");
			return false;
		}
		if(comboSponsors.getSelectedIndex()<0){
			informarError("Debe seleccionar un sponsor.", "Guardar Contrato.");
			return false;
		}
		if(comboDias.getSelectedIndex()<0){
			informarError("Debe seleccionar un dia de pago.", "Guardar Contrato.");
		}
		if(!v.numeroDecimal(monto)){
			informarError("El campo monto debe contener un numero decimal", "Guardar Contrato.");
			return false;
		}
		if(txtCodigo.getText().equals("")){
			informarError("Debe ingresar un código de contrato", "Guardar Contrato.");
			return false;
		}
		return true;
	}
	
	private java.sql.Date convertFecha(String fecha) throws Exception{
		java.sql.Date sqlDate = null;
		try{
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date date = format.parse(fecha);
			sqlDate = new java.sql.Date(date.getTime());
		}
		catch(ParseException pex){
			throw new AppException("Error al intentar convertir las fechas");
		}
		catch(Exception ex){
			throw new Exception("Error no controlado al intentar convertir las fechas");
		}
		return sqlDate;	
 
	}
}
