package contacto;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entidades.Contacto;
import entidades.Sponsor;
import logica.LogicContacto;
import logica.LogicSponsors;

public class EditContacto {

	private JFrame frameContactos;
	private JTable tableContactos;
	
	private Sponsor sponsorAct = null;

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
		frameContactos = new JFrame();
		frameContactos.setBounds(100, 100, 553, 384);
		frameContactos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameContactos.getContentPane().setLayout(new BorderLayout(0, 0));
		
		tableContactos = new JTable();
		frameContactos.getContentPane().add(tableContactos, BorderLayout.CENTER);
	}
	
	public void open(Sponsor s){
		sponsorAct = s;
		frameContactos.setVisible(true);
		loadTable();
	}
	
	private void loadTable(){
		
		String[] columnas = {"IdSponsors","Cuit","Razon Social","Direccion", "Numero", "Comentario"};
		DefaultTableModel tableModel = new DefaultTableModel();
		
		try{
			tableModel.setColumnIdentifiers(columnas);
			Object[] fila = new Object[tableModel.getColumnCount()];
			for (int i = 0; i < sponsorAct.getContactos().size(); i++) {

				fila[0] = sponsorAct.getContactos().get(i).getIdContacto();
				fila[1] = sponsorAct.getContactos().get(i).getNombre();
				fila[2] = sponsorAct.getContactos().get(i).getApellido();
				fila[3] = sponsorAct.getContactos().get(i).getDireccion();
				fila[4] = sponsorAct.getContactos().get(i).getCargo();
				fila[5] = sponsorAct.getContactos().get(i).getDni();
				tableModel.addRow(fila);
				}
			tableContactos.setModel(tableModel);
			//Seteo ancho de columnas
			/*tableSponsors.getColumnModel().getColumn(1).setPreferredWidth(115);
			tableSponsors.getColumnModel().getColumn(2).setPreferredWidth(150);
			tableSponsors.getColumnModel().getColumn(3).setPreferredWidth(200);
			tableSponsors.getColumnModel().getColumn(4).setPreferredWidth(70);
			tableSponsors.getColumnModel().getColumn(5).setPreferredWidth(200);
			//Oculto columna de IdPersonaje
            tableSponsors.getColumnModel().getColumn(0).setMaxWidth(0);
            tableSponsors.getColumnModel().getColumn(0).setMinWidth(0);
            tableSponsors.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
            tableSponsors.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);*/
		}
		catch(Exception ex){
			informarError(ex.getMessage());
		}		
		
	}
	
	private void informarError(String mensaje){
		JOptionPane.showMessageDialog(frameContactos, mensaje, "Editar contactos.", JOptionPane.ERROR_MESSAGE);
	}

}
