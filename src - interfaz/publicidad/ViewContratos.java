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
import java.sql.SQLException;

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
	
	public void nuevo(){
		Contrato c = new Contrato();
		c.setId(0);
		EditContrato ec = new EditContrato();
		ec.open(c);
	}
	
	public void editar(){
		if(!validarSeleccion()){
			return;
		}
		Contrato c = mapearDeArray();
		EditContrato ec = new EditContrato();
		ec.open(c);
	}
	public void anular(){
		if(!validarSeleccion()){
			return;
		}
		if(!super.confirmarUsuario("Está seguro que desea anular el contrato seleccionado?", "Anular Contrato.")){
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
	public Contrato getContrato(){
		if(!validarSeleccion()){
			return null;
		}
		Contrato c = new Contrato();
		try{
			c = mapearDeArray();
		}
		catch(Exception ex){
			super.informarError(ex.getMessage(), "Registro de pagos");
		}
		return c;
	}
	private boolean validarSeleccion(){
		if (tableContratos.getSelectedRow()<0){
			super.informarError("Por favor seleccione un Contrato.", "Modificar Contrato.");
			return false;
		}
		return true;
	}

}
