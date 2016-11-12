package publicidad;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logica.LogicContrato;
import entidades.Contrato;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewContratos extends JPanel {

	private JTable tableContratos;
	private LogicContrato lc;
	
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
		panelButtons.add(btnModificar);
		
		JLabel lblnewlabel = new JLabel("  ");
		panelButtons.add(lblnewlabel);
		
		JButton btnDetalle = new JButton("Detalle");
		panelButtons.add(btnDetalle);
		
		JLabel lblNewLabel = new JLabel("  ");
		panelButtons.add(lblNewLabel);
		
		JButton btnAnular = new JButton("Anular");
		panelButtons.add(btnAnular);
		
		JLabel lblNewLabel_1 = new JLabel("  ");
		panelButtons.add(lblNewLabel_1);
		
		JButton btnPago = new JButton("Registrar Pago");
		panelButtons.add(btnPago);
		
		JPanel panelLeft = new JPanel();
		add(panelLeft, BorderLayout.WEST);
		panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));
		
		JRadioButton rbActivos = new JRadioButton("Activos");
		rbActivos.setSelected(true);
		panelLeft.add(rbActivos);
		
		JRadioButton rbVencidos = new JRadioButton("Vencidos");
		panelLeft.add(rbVencidos);
		
		loadContratos();
	}
	
	private Contrato mapearDeTabla(){
		Contrato c = new Contrato();
		return c;
	}
	private void loadContratos(){
		String[] columnas = {"IdContrato","Codigo","Fecha Inicio","Fecha Fin", "Monto", "Dia Pago", "Sponsor"};
		lc = new LogicContrato();
		ArrayList<Contrato> contratos = new ArrayList<Contrato>();
		DefaultTableModel tableModel = new DefaultTableModel();
		
		try{
			tableModel.setColumnIdentifiers(columnas);
			Object[] fila = new Object[tableModel.getColumnCount()];
			contratos = lc.getContratos();
			for(Contrato c : contratos){
				fila[0] = c.getId();
				fila[1] = c.getCodigo();
				fila[2] = c.getFechaInicio();
				fila[3] = c.getFechaFin();
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
		}
	}
	
	private void nuevo(){
		Contrato c = new Contrato();
		c.setId(0);
		EditContrato ec = new EditContrato();
		ec.open(c);
	}
	
	private void editar(){
		Contrato c = new Contrato();
		c.setId(0);
		EditContrato ec = new EditContrato();
		ec.open(c);
	}

}
