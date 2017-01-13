package publicidad;

import generic.Generic;
import logica.LogicRegistrarPago;

import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entidades.ComprobantePagoSponsor;
import entidades.Contrato;
import entidades.Pago;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewComprobante extends Generic{
	private JTable tableCuotas;
	private DefaultTableModel tableModel;
	private ComprobantePagoSponsor comprobanteAct;	
	
	public ViewComprobante() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblCuotasAbonadas = new JLabel("Cuotas Abonadas");
		add(lblCuotasAbonadas, BorderLayout.NORTH);
		
		tableCuotas = new JTable();
		add(tableCuotas, BorderLayout.CENTER);
		
		JPanel panelButtons = new JPanel();
		add(panelButtons, BorderLayout.SOUTH);
		panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.X_AXIS));
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelButtons.add(btnEliminar);
		
		JButton btnRegistrarPago = new JButton("Registrar Pago");
		btnRegistrarPago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrarPago();
			}
		});
		btnRegistrarPago.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelButtons.add(btnRegistrarPago);
	}
	
	public void addCuota(Pago p){
		
		try{
			comprobanteAct.getCuotas().add(p);
		}
		catch(Exception ex){
			super.informarError(ex.getMessage(), "error.");
		}
		
		// Agrego la cuota a la JTable //;
		Object[] fila = new Object[tableModel.getColumnCount()];
		
		fila[0] = p.getFechaVenc();
		fila[1] = p.getImporteAbonado();
		fila[2] = p.getDescuento();
		fila[3] = p.getRecargo();
		
		tableModel.addRow(fila);
	}
	
	private void inicializarTabla(){
		String[] columnas = {"Fecha de Vencimiento","Importe Abonado","Descuento", "Recargo"};
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columnas);
		tableCuotas.setModel(tableModel);
		
		tableCuotas.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableCuotas.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableCuotas.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableCuotas.getColumnModel().getColumn(3).setPreferredWidth(100);		
	}
	
	public void inicializar(Contrato c){
		
		inicializarTabla();
		comprobanteAct= new ComprobantePagoSponsor();
		comprobanteAct.setContrato(c);
		
	}
	
	private void registrarPago(){
		LogicRegistrarPago lrp = new LogicRegistrarPago();
		
		try{
			lrp.registrarPago(comprobanteAct);
			super.informarUsuario("Pago registrado correctamente.", "Registro de Pago.");
		}
		catch(Exception ex){
			super.informarError(ex.getMessage(), "Registrar Pago.");
		}
	}
}
