package publicidad;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;

import util.Validate;
import entidades.Contrato;
import entidades.Sponsor;
import generic.GenericAbm;
import logica.LogicContrato;
import logica.LogicSponsor;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;

public class EditContrato extends GenericAbm {

	private JFrame frameEditContrato;

	private Contrato contratoAct;
	private LogicContrato lc;
	private LogicSponsor ls;
	
	//Controles
	private JTextField textCodigo;
	private JTextField textMonto;
	private JTextField textDescripcion;
	private JTextField textComentario;
	private JLabel lblSponsor;
	private JComboBox<Sponsor> comboSponsors;
	private JDateChooser dateIni;
	private JDateChooser dateFin;
	private JDayChooser daySelect;
	/**
	 * Create the application.
	 */
	public EditContrato() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameEditContrato = new JFrame();
		frameEditContrato.setBounds(100, 100, 578, 421);
		frameEditContrato.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frameEditContrato.getContentPane().setLayout(springLayout);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		springLayout.putConstraint(SpringLayout.NORTH, lblCodigo, 24, SpringLayout.NORTH, frameEditContrato.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblCodigo, 10, SpringLayout.WEST, frameEditContrato.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblCodigo, -507, SpringLayout.EAST, frameEditContrato.getContentPane());
		frameEditContrato.getContentPane().add(lblCodigo);
		
		textCodigo = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textCodigo, -2, SpringLayout.NORTH, lblCodigo);
		springLayout.putConstraint(SpringLayout.WEST, textCodigo, 23, SpringLayout.EAST, lblCodigo);
		frameEditContrato.getContentPane().add(textCodigo);
		textCodigo.setColumns(10);
		
		JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
		springLayout.putConstraint(SpringLayout.NORTH, lblFechaInicio, 33, SpringLayout.SOUTH, textCodigo);
		springLayout.putConstraint(SpringLayout.WEST, lblFechaInicio, 10, SpringLayout.WEST, frameEditContrato.getContentPane());
		frameEditContrato.getContentPane().add(lblFechaInicio);
		
		JLabel lblFechaFin = new JLabel("Fecha Fin:");
		springLayout.putConstraint(SpringLayout.NORTH, lblFechaFin, 0, SpringLayout.NORTH, lblFechaInicio);
		frameEditContrato.getContentPane().add(lblFechaFin);
		
		JLabel lblMonto = new JLabel("Monto:");
		frameEditContrato.getContentPane().add(lblMonto);
		
		textMonto = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, textMonto, 420, SpringLayout.WEST, frameEditContrato.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textMonto, -81, SpringLayout.EAST, frameEditContrato.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblMonto, 2, SpringLayout.NORTH, textMonto);
		springLayout.putConstraint(SpringLayout.EAST, lblMonto, -6, SpringLayout.WEST, textMonto);
		frameEditContrato.getContentPane().add(textMonto);
		textMonto.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		springLayout.putConstraint(SpringLayout.WEST, lblDescripcion, 10, SpringLayout.WEST, frameEditContrato.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblDescripcion, 0, SpringLayout.EAST, lblFechaInicio);
		frameEditContrato.getContentPane().add(lblDescripcion);
		
		textDescripcion = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textDescripcion, -3, SpringLayout.NORTH, lblDescripcion);
		springLayout.putConstraint(SpringLayout.WEST, textDescripcion, 14, SpringLayout.EAST, lblDescripcion);
		springLayout.putConstraint(SpringLayout.EAST, textDescripcion, 0, SpringLayout.EAST, lblMonto);
		frameEditContrato.getContentPane().add(textDescripcion);
		textDescripcion.setColumns(10);
		
		JLabel lblComentario = new JLabel("Comentario:");
		springLayout.putConstraint(SpringLayout.NORTH, lblComentario, 306, SpringLayout.NORTH, frameEditContrato.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblComentario, 10, SpringLayout.WEST, frameEditContrato.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblDescripcion, -27, SpringLayout.NORTH, lblComentario);
		frameEditContrato.getContentPane().add(lblComentario);
		
		textComentario = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textComentario, -3, SpringLayout.NORTH, lblComentario);
		springLayout.putConstraint(SpringLayout.WEST, textComentario, 16, SpringLayout.EAST, lblComentario);
		springLayout.putConstraint(SpringLayout.EAST, textComentario, 0, SpringLayout.EAST, lblMonto);
		frameEditContrato.getContentPane().add(textComentario);
		textComentario.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		springLayout.putConstraint(SpringLayout.WEST, btnGuardar, 0, SpringLayout.WEST, lblMonto);
		springLayout.putConstraint(SpringLayout.SOUTH, btnGuardar, -10, SpringLayout.SOUTH, frameEditContrato.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnGuardar, -81, SpringLayout.EAST, frameEditContrato.getContentPane());
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickGuardar();
			}
		});
		frameEditContrato.getContentPane().add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		springLayout.putConstraint(SpringLayout.WEST, btnCancelar, 75, SpringLayout.WEST, frameEditContrato.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnCancelar, -10, SpringLayout.SOUTH, frameEditContrato.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnCancelar, 0, SpringLayout.EAST, textCodigo);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}
		});
		frameEditContrato.getContentPane().add(btnCancelar);
		
		JLabel lblDiaPago = new JLabel("Dia Pago:");
		springLayout.putConstraint(SpringLayout.NORTH, lblDiaPago, 43, SpringLayout.SOUTH, lblFechaInicio);
		springLayout.putConstraint(SpringLayout.EAST, lblDiaPago, 0, SpringLayout.EAST, lblFechaInicio);
		frameEditContrato.getContentPane().add(lblDiaPago);
		
		dateIni = new JDateChooser();
		springLayout.putConstraint(SpringLayout.WEST, dateIni, 6, SpringLayout.EAST, lblFechaInicio);
		springLayout.putConstraint(SpringLayout.EAST, dateIni, -317, SpringLayout.EAST, frameEditContrato.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblFechaFin, 6, SpringLayout.EAST, dateIni);
		springLayout.putConstraint(SpringLayout.SOUTH, dateIni, 0, SpringLayout.SOUTH, lblFechaInicio);
		frameEditContrato.getContentPane().add(dateIni);
		
		dateFin = new JDateChooser();
		springLayout.putConstraint(SpringLayout.NORTH, textMonto, 41, SpringLayout.SOUTH, dateFin);
		springLayout.putConstraint(SpringLayout.WEST, dateFin, 345, SpringLayout.WEST, frameEditContrato.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, dateFin, -81, SpringLayout.EAST, frameEditContrato.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, dateFin, 0, SpringLayout.SOUTH, lblFechaInicio);
		frameEditContrato.getContentPane().add(dateFin);
		
		daySelect = new JDayChooser();
		springLayout.putConstraint(SpringLayout.WEST, daySelect, 6, SpringLayout.EAST, lblDiaPago);
		springLayout.putConstraint(SpringLayout.SOUTH, daySelect, -14, SpringLayout.NORTH, textDescripcion);
		frameEditContrato.getContentPane().add(daySelect);
		
		comboSponsors = new JComboBox<Sponsor>();
		springLayout.putConstraint(SpringLayout.NORTH, comboSponsors, 21, SpringLayout.NORTH, frameEditContrato.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, comboSponsors, -81, SpringLayout.EAST, frameEditContrato.getContentPane());
		
		frameEditContrato.getContentPane().add(comboSponsors);
		
		lblSponsor = new JLabel("Sponsor:");
		springLayout.putConstraint(SpringLayout.NORTH, lblSponsor, 24, SpringLayout.NORTH, frameEditContrato.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblSponsor, -249, SpringLayout.EAST, frameEditContrato.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, comboSponsors, 6, SpringLayout.EAST, lblSponsor);
		frameEditContrato.getContentPane().add(lblSponsor);
		
		addSponsors();
	}

//Con este metodo lleno el ComboBox con todos los sponsors.	
	private void addSponsors(){
		ArrayList<Sponsor> sponsors = new ArrayList<Sponsor>();
		ls = new LogicSponsor();
		
		try{
			sponsors=ls.getSponsors();
			for(Sponsor s : sponsors){
				comboSponsors.addItem(s);
			}
			comboSponsors.setSelectedIndex(-1);
		}
		catch(SQLException sqlex){
			super.informarError(sqlex.getMessage(), "Consultar Sponsors.");
		}
		catch(Exception ex){
			super.informarError(ex.getMessage(), "Consultar Sponsors.");
		}
	}
	@Override
	protected void clickGuardar() {
		// TODO Auto-generated method stub
		lc = new LogicContrato();
		if(!validarSeleccion()){
			return;
		}
		try{
			Sponsor s;
			s = (Sponsor)comboSponsors.getSelectedItem(); // Viene el objeto completo con todos los atributos.
			contratoAct=mapearDeFormulario();
			contratoAct.setSponsor(s);
			if(contratoAct.getId()>0){
				lc.updateContrato(contratoAct);
			}
			else {
				lc.createContrato(contratoAct);
				super.informarUsuario("Contrato guardado correctamente", "Modificar Contratos");
			}						
		}
		catch(SQLException sqlex){
			super.informarError(sqlex.getMessage(), "Modificar Contratos");
		}
		catch(Exception ex){
			super.informarError(ex.getMessage(), "Modificar Contratos");
		}
	}

	@Override
	protected void clickCancelar() {
		// TODO Auto-generated method stub
		frameEditContrato.dispose();
	}

	@Override
	protected Contrato mapearDeFormulario() {
		// TODO Auto-generated method stub
		try{
			contratoAct.setCodigo(textCodigo.getText());
			contratoAct.setComentario(textComentario.getText());
			contratoAct.setFechaInicio(dateIni.getDate());
			contratoAct.setFechaFin(dateFin.getDate());
			contratoAct.setDiaPago(daySelect.getDay());
			contratoAct.setMonto(Float.valueOf(textMonto.getText()));
			contratoAct.setDescripcion(textDescripcion.getText());
		}
		catch(Exception ex){
			super.informarError(ex.getMessage(), "Mapear Contrato");
		}		
		return contratoAct;
	}
	private void mapearAFormulario(Contrato c) {
		textCodigo.setText(c.getCodigo());
		textMonto.setText(String.valueOf(c.getMonto()));
		textComentario.setText(c.getComentario());
		textDescripcion.setText(c.getDescripcion());
		dateIni.setDate(c.getFechaInicio());
		dateFin.setDate(c.getFechaFin());
		daySelect.setDay(c.getDiaPago());
	}
	private boolean validarSeleccion(){
		Validate v = new Validate();
		if(!v.notEmpty(new String[] {textCodigo.getText(), textMonto.getText(), dateFin.getDateFormatString(), dateIni.getDateFormatString()})){
			super.informarError("Estos campos no pueden quedar vacíos", "Modificar Contrato.");
			return false;
		}
		if(!v.numeroDecimal(new String[] {textMonto.getText()})){
			super.informarError("Este campo debe contener sólo números", "Modificar Contrato.");
			return false;
		}
		if(contratoAct.getId()<=0 && comboSponsors.getSelectedIndex()<0){
			super.informarError("Debe seleccionar un sponsor", "Modificar Contrato.");
			return false;
		}
		if(dateFin.getDate().getTime() <= dateIni.getDate().getTime()){
			super.informarError("La fecha de inicio no puede ser mayor a la de fin", "Modificar Contrato.");
			return false;
		}
		return true;
	}
	private void cancelar(){
		frameEditContrato.dispose();
	}
	public void open(Contrato c){
		contratoAct=c;
		mapearAFormulario(contratoAct);
		if(c.getId()>0){
			comboSponsors.setVisible(false);
			lblSponsor.setText("Sponsor :" + c.getSponsor());
		}
		frameEditContrato.setVisible(true);
	}
}
