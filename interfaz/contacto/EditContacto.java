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
	private LogicContacto lc = null;
	private ArrayList<Contacto> contactos = new ArrayList<Contacto>();

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
		loadContactos();
		frameContactos.setVisible(true);
	}
	
	private void loadContactos(){
		lc = new LogicContacto();
		
		try{
			contactos=lc.getContactos(sponsorAct);
			loadTable();
		}
		catch(SQLException sqlex){
			informarError(sqlex.getMessage());
		}
		catch(Exception ex){
			informarError(ex.getMessage());
		}
	}
	
	private void loadTable(){
		
		String[] columnas = {"IdSponsors","Cuit","Razon Social","Direccion", "Numero", "Comentario"};
		DefaultTableModel tableModel = new DefaultTableModel();
		
		try{
			tableModel.setColumnIdentifiers(columnas);
			Object[] fila = new Object[tableModel.getColumnCount()];
			for (int i = 0; i < contactos.size(); i++) {

				fila[0] = contactos.get(i).getIdContacto();
				fila[1] = contactos.get(i).getNombre();
				fila[2] = contactos.get(i).getApellido();
				fila[3] = contactos.get(i).getDireccion();
				fila[4] = contactos.get(i).getCargo();
				fila[5] = contactos.get(i).getDni();
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
