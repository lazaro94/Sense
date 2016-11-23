package publicidad;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logica.LogicContrato;
import entidades.Contrato;
import generic.Generic;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ViewContratos extends Generic {
	
	private JTable tableContratos;
	private LogicContrato lc;
	private ArrayList<Contrato> contratos = new ArrayList<Contrato>();
	
	public ViewContratos() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelHeader = new JPanel();
		add(panelHeader, BorderLayout.NORTH);
		panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.Y_AXIS));
		
		JLabel label = new JLabel("  ");
		panelHeader.add(label);
		
		JLabel lblContratos = new JLabel("Contratos");
		lblContratos.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelHeader.add(lblContratos);
		
		JLabel label_1 = new JLabel("  ");
		panelHeader.add(label_1);
		
		tableContratos = new JTable();
		this.add(tableContratos, BorderLayout.CENTER);
		
		JPanel panelButtons = new JPanel();
		add(panelButtons, BorderLayout.SOUTH);
		panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.X_AXIS));
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevo();
			}
		});
		panelButtons.add(btnNuevo);
		
		JLabel label_2 = new JLabel("  ");
		panelButtons.add(label_2);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		panelButtons.add(btnModificar);
		
		JLabel lblnewlabel = new JLabel("  ");
		panelButtons.add(lblnewlabel);
		
		JButton btnDetalle = new JButton("Detalle");
		btnDetalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				detalle();
			}
		});
		panelButtons.add(btnDetalle);
		
		JLabel lblNewLabel = new JLabel("  ");
		panelButtons.add(lblNewLabel);
		
		JButton btnAnular = new JButton("Anular");
		btnAnular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anular();
			}
		});
		panelButtons.add(btnAnular);
		
		JLabel lblNewLabel_1 = new JLabel("  ");
		panelButtons.add(lblNewLabel_1);
		
		JButton btnPago = new JButton("Registrar Pago");
		btnPago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrarPago();
			}
		});
		panelButtons.add(btnPago);
		
		JPanel panelLeft = new JPanel();
		add(panelLeft, BorderLayout.WEST);
		panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));
		
		JRadioButton rbActivos = new JRadioButton("Activos");
		rbActivos.setSelected(true);
		panelLeft.add(rbActivos);
		
		JRadioButton rbVencidos = new JRadioButton("Vencidos");
		panelLeft.add(rbVencidos);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rbVencidos);
		bg.add(rbActivos);
		
		loadContratos();
	}
	
	private Contrato mapearDeArray(){		
		Contrato c = new Contrato();
		c.setId(Integer.parseInt(tableContratos.getValueAt(tableContratos.getSelectedRow(), 0).toString()));
		c = contratos.get(contratos.indexOf(c));
		return c;
	}
	private void loadContratos(){
		String[] columnas = {"IdContrato","Codigo","Fecha Inicio","Fecha Fin", "Monto", "Dia Pago", "Sponsor"};
		lc = new LogicContrato();		
		DefaultTableModel tableModel = new DefaultTableModel();
		
		try{
			tableModel.setColumnIdentifiers(columnas);
			Object[] fila = new Object[tableModel.getColumnCount()];
			contratos = lc.getContratos();
			for(Contrato c : contratos){
				fila[0] = c.getId();
				fila[1] = c.getCodigo();
				fila[2] = c.getFechaInicio("dd/MM/yyyy");
				fila[3] = c.getFechaFin("dd/MM/yyyy");
				fila[4] = c.getMonto();
				fila[5] = c.getDiaPago();
				fila[6] = c.getSponsor().getRazonSocial();
				tableModel.addRow(fila);
			}
			tableContratos.setModel(tableModel);
			tableContratos.getColumnModel().getColumn(1).setPreferredWidth(70);
			tableContratos.getColumnModel().getColumn(2).setPreferredWidth(75);
			tableContratos.getColumnModel().getColumn(3).setPreferredWidth(75);
			tableContratos.getColumnModel().getColumn(4).setPreferredWidth(80);
			tableContratos.getColumnModel().getColumn(5).setPreferredWidth(30);
			tableContratos.getColumnModel().getColumn(6).setPreferredWidth(200);
			//Oculto columna de IdContratos
            tableContratos.getColumnModel().getColumn(0).setMaxWidth(0);
            tableContratos.getColumnModel().getColumn(0).setMinWidth(0);
            tableContratos.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
            tableContratos.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
			
		}
		catch(Exception ex){
			super.informarError(ex.getMessage(), "Consultar Contratos.");
		}
	}
	
	private void nuevo(){
		Contrato c = new Contrato();
		c.setId(0);
		EditContrato ec = new EditContrato();
		ec.open(c);
	}
	
	private void editar(){
		if(!validarSeleccion()){
			return;
		}
		Contrato c = mapearDeArray();
		EditContrato ec = new EditContrato();
		ec.open(c);
	}
	private void anular(){
		if(!validarSeleccion()){
			return;
		}
		Contrato c = mapearDeArray();
		lc = new LogicContrato();
		try{
			lc.anularContrato(c);
			super.informarUsuario("Contrato anulado correctamente.", "Anular Contrato.");
		}
		catch(SQLException sqlex){
			super.informarError(sqlex.getMessage(), "Anular Contrato.");
		}
		catch(Exception ex){
			super.informarError(ex.getMessage(), "Anular Contrato.");
		}
	}
	private void registrarPago(){
		if(!validarSeleccion()){
			return;
		}
		NewPago np = new NewPago();
		Contrato c = mapearDeArray();
		np.open(c);
	}
	private void detalle(){
		
	}
	private boolean validarSeleccion(){
		if (tableContratos.getSelectedRow()<0){
			super.informarError("Por favor seleccione un Contrato.", "Modificar Contrato.");
			return false;
		}
		return true;
	}

}
