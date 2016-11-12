package publicidad;

import java.sql.SQLException;

import javax.swing.JFrame;

import entidades.Contrato;
import generic.GenericAbm;
import logica.LogicContrato;
import util.Parse;

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
	private JTextField textCodigo;
	private JTextField textMonto;
	private JTextField textDescripcion;
	private JTextField textComentario;
	private JComboBox<String> comboDias;
	private JLabel labelSponsor;
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
		frameEditContrato.setBounds(100, 100, 450, 300);
		frameEditContrato.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frameEditContrato.getContentPane().setLayout(springLayout);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		springLayout.putConstraint(SpringLayout.NORTH, lblCodigo, 24, SpringLayout.NORTH, frameEditContrato.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblCodigo, 10, SpringLayout.WEST, frameEditContrato.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblCodigo, 58, SpringLayout.WEST, frameEditContrato.getContentPane());
		frameEditContrato.getContentPane().add(lblCodigo);
		
		textCodigo = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textCodigo, -3, SpringLayout.NORTH, lblCodigo);
		springLayout.putConstraint(SpringLayout.WEST, textCodigo, 6, SpringLayout.EAST, lblCodigo);
		frameEditContrato.getContentPane().add(textCodigo);
		textCodigo.setColumns(10);
		
		labelSponsor = new JLabel("<sponsor>");
		springLayout.putConstraint(SpringLayout.NORTH, labelSponsor, 0, SpringLayout.NORTH, lblCodigo);
		springLayout.putConstraint(SpringLayout.WEST, labelSponsor, 47, SpringLayout.EAST, textCodigo);
		frameEditContrato.getContentPane().add(labelSponsor);
		
		JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
		springLayout.putConstraint(SpringLayout.NORTH, lblFechaInicio, 35, SpringLayout.SOUTH, lblCodigo);
		springLayout.putConstraint(SpringLayout.WEST, lblFechaInicio, 0, SpringLayout.WEST, lblCodigo);
		frameEditContrato.getContentPane().add(lblFechaInicio);
		
		JLabel lblFechaFin = new JLabel("Fecha Fin:");
		springLayout.putConstraint(SpringLayout.NORTH, lblFechaFin, 0, SpringLayout.NORTH, lblFechaInicio);
		springLayout.putConstraint(SpringLayout.WEST, lblFechaFin, 122, SpringLayout.EAST, lblFechaInicio);
		frameEditContrato.getContentPane().add(lblFechaFin);
		
		JLabel lblMonto = new JLabel("Monto:");
		springLayout.putConstraint(SpringLayout.NORTH, lblMonto, 33, SpringLayout.SOUTH, lblFechaInicio);
		springLayout.putConstraint(SpringLayout.EAST, lblMonto, 0, SpringLayout.EAST, lblCodigo);
		frameEditContrato.getContentPane().add(lblMonto);
		
		textMonto = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textMonto, 27, SpringLayout.SOUTH, lblFechaInicio);
		springLayout.putConstraint(SpringLayout.WEST, textMonto, 6, SpringLayout.EAST, lblMonto);
		springLayout.putConstraint(SpringLayout.EAST, textMonto, 67, SpringLayout.EAST, lblMonto);
		frameEditContrato.getContentPane().add(textMonto);
		textMonto.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		springLayout.putConstraint(SpringLayout.NORTH, lblDescripcion, 22, SpringLayout.SOUTH, lblMonto);
		springLayout.putConstraint(SpringLayout.WEST, lblDescripcion, 10, SpringLayout.WEST, frameEditContrato.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblDescripcion, 0, SpringLayout.EAST, lblFechaInicio);
		frameEditContrato.getContentPane().add(lblDescripcion);
		
		textDescripcion = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textDescripcion, 19, SpringLayout.SOUTH, textMonto);
		springLayout.putConstraint(SpringLayout.WEST, textDescripcion, 6, SpringLayout.EAST, lblDescripcion);
		springLayout.putConstraint(SpringLayout.EAST, textDescripcion, 286, SpringLayout.EAST, lblDescripcion);
		frameEditContrato.getContentPane().add(textDescripcion);
		textDescripcion.setColumns(10);
		
		JLabel lblComentario = new JLabel("Comentario:");
		springLayout.putConstraint(SpringLayout.NORTH, lblComentario, 19, SpringLayout.SOUTH, lblDescripcion);
		springLayout.putConstraint(SpringLayout.EAST, lblComentario, 0, SpringLayout.EAST, lblFechaInicio);
		frameEditContrato.getContentPane().add(lblComentario);
		
		textComentario = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textComentario, 16, SpringLayout.SOUTH, textDescripcion);
		springLayout.putConstraint(SpringLayout.WEST, textComentario, 6, SpringLayout.EAST, lblComentario);
		springLayout.putConstraint(SpringLayout.EAST, textComentario, 286, SpringLayout.EAST, lblComentario);
		frameEditContrato.getContentPane().add(textComentario);
		textComentario.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickGuardar();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnGuardar, 6, SpringLayout.SOUTH, textComentario);
		springLayout.putConstraint(SpringLayout.WEST, btnGuardar, -165, SpringLayout.EAST, frameEditContrato.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnGuardar, -84, SpringLayout.EAST, frameEditContrato.getContentPane());
		frameEditContrato.getContentPane().add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		springLayout.putConstraint(SpringLayout.NORTH, btnCancelar, 6, SpringLayout.SOUTH, textComentario);
		springLayout.putConstraint(SpringLayout.WEST, btnCancelar, 88, SpringLayout.WEST, frameEditContrato.getContentPane());
		frameEditContrato.getContentPane().add(btnCancelar);
		
		JLabel lblDiaPago = new JLabel("Dia Pago:");
		springLayout.putConstraint(SpringLayout.NORTH, lblDiaPago, 0, SpringLayout.NORTH, textMonto);
		springLayout.putConstraint(SpringLayout.WEST, lblDiaPago, 46, SpringLayout.EAST, textMonto);
		frameEditContrato.getContentPane().add(lblDiaPago);
		
		comboDias = new JComboBox<String>();
		springLayout.putConstraint(SpringLayout.NORTH, comboDias, 0, SpringLayout.NORTH, textMonto);
		springLayout.putConstraint(SpringLayout.WEST, comboDias, 6, SpringLayout.EAST, lblDiaPago);
		springLayout.putConstraint(SpringLayout.EAST, comboDias, 54, SpringLayout.EAST, lblDiaPago);
		frameEditContrato.getContentPane().add(comboDias);
		
		JDateChooser dateIni = new JDateChooser();
		springLayout.putConstraint(SpringLayout.WEST, dateIni, 6, SpringLayout.EAST, lblFechaInicio);
		springLayout.putConstraint(SpringLayout.SOUTH, dateIni, 0, SpringLayout.SOUTH, lblFechaInicio);
		frameEditContrato.getContentPane().add(dateIni);
		
		JDateChooser dateFin = new JDateChooser();
		springLayout.putConstraint(SpringLayout.WEST, dateFin, 6, SpringLayout.EAST, lblFechaFin);
		springLayout.putConstraint(SpringLayout.SOUTH, dateFin, 0, SpringLayout.SOUTH, lblFechaInicio);
		frameEditContrato.getContentPane().add(dateFin);
		addDias();
	}

	private void addDias(){
		comboDias.addItem("01");
		comboDias.addItem("02");
		comboDias.addItem("03");
		comboDias.addItem("04");
		comboDias.addItem("05");
		comboDias.addItem("06");
		comboDias.addItem("07");
		comboDias.addItem("08");
		comboDias.addItem("09");
		comboDias.addItem("10");
		comboDias.addItem("11");		
	}
	@Override
	protected void clickGuardar() {
		// TODO Auto-generated method stub
		lc = new LogicContrato();
		try{
			contratoAct=mapearDeFormulario();
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
			contratoAct.setDiaPago(Integer.valueOf(comboDias.getSelectedItem().toString()));
			//contratoAct.setFechaFin(Parse.dateToSql(textFechaFin.getText()));
			//contratoAct.setFechaInicio(Parse.dateToSql(textFechaIni.getText()));
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
		textComentario.setText(c.getComentario());
		comboDias.setSelectedItem(c.getDiaPago());
		//textFechaFin.setText(String.valueOf(c.getFechaFin()));
		//textFechaIni.setText(String.valueOf(c.getFechaInicio()));
		textMonto.setText(String.valueOf(c.getMonto()));
		textDescripcion.setText(c.getDescripcion());
		labelSponsor.setText(c.getSponsor().getRazonSocial());
	}
	
	public void open(Contrato c){
		contratoAct=c;
		mapearAFormulario(contratoAct);
		frameEditContrato.setVisible(true);
	}
}
