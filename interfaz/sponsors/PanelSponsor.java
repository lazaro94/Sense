package sponsors;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;

import contacto.EditContacto;
import logica.LogicSponsors;
import entidades.Sponsor;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;

public class PanelSponsor extends JPanel{
	private JTable tableSponsors;
	private LogicSponsors ls = null;
	public PanelSponsor() {
		setLayout(new BorderLayout(0, 0));
		
		tableSponsors = new JTable();
		add(tableSponsors, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickNuevo();
			}
		});
		panel.add(btnNuevo);
		
		JLabel label = new JLabel("  ");
		panel.add(label);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickEditar();
			}
		});
		panel.add(btnEditar);
		
		JLabel label_1 = new JLabel("  ");
		panel.add(label_1);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickEliminar();
			}
		});
		panel.add(btnEliminar);
		
		JLabel label_2 = new JLabel("        ");
		panel.add(label_2);
		
		JButton btnVerContactos = new JButton("Ver contactos");
		btnVerContactos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verContactos();
			}
		});
		btnVerContactos.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel.add(btnVerContactos);
		
		JLabel lblSponsors = new JLabel("SPONSORS");
		add(lblSponsors, BorderLayout.NORTH);
		actualizarTabla();
	}
	
	public void actualizarTabla(){
		//DECLARACION VARIABLES
		ArrayList<Sponsor> sponsors = new ArrayList<Sponsor>();
		String[] columnas = {"IdSponsors","Cuit","Razon Social","Direccion", "Numero", "Comentario"};
		DefaultTableModel tableModel = new DefaultTableModel();
		ls = new LogicSponsors();
		
		try{
			sponsors=ls.getSponsors();
			tableModel.setColumnIdentifiers(columnas);
			Object[] fila = new Object[tableModel.getColumnCount()];
			for (int i = 0; i < sponsors.size(); i++) {

				fila[0] = sponsors.get(i).getId();
				fila[1] = sponsors.get(i).getCuit();
				fila[2] = sponsors.get(i).getRazonSocial();
				fila[3] = sponsors.get(i).getCalle();
				fila[4] = sponsors.get(i).getNumero();
				fila[5] = sponsors.get(i).getComentario();
				tableModel.addRow(fila);
				}
			tableSponsors.setModel(tableModel);
			//Seteo ancho de columnas
			tableSponsors.getColumnModel().getColumn(1).setPreferredWidth(115);
			tableSponsors.getColumnModel().getColumn(2).setPreferredWidth(150);
			tableSponsors.getColumnModel().getColumn(3).setPreferredWidth(200);
			tableSponsors.getColumnModel().getColumn(4).setPreferredWidth(70);
			tableSponsors.getColumnModel().getColumn(5).setPreferredWidth(200);
			//Oculto columna de IdPersonaje
            tableSponsors.getColumnModel().getColumn(0).setMaxWidth(0);
            tableSponsors.getColumnModel().getColumn(0).setMinWidth(0);
            tableSponsors.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
            tableSponsors.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		}
		catch(SQLException sqlex){
			informarError(sqlex.getMessage(), "Consultar Sponsors");
		}
		catch(Exception ex){
			informarError(ex.getMessage(), "Consultar Sponsors");
		}		
	}
	
	private Sponsor mapearDeTabla(){
		int id= Integer.parseInt(String.valueOf(tableSponsors.getValueAt(tableSponsors.getSelectedRow(), 0)));
		String razonSocial = String.valueOf(tableSponsors.getValueAt(tableSponsors.getSelectedRow(), 2));
		String cuit = String.valueOf(tableSponsors.getValueAt(tableSponsors.getSelectedRow(), 1));
		String calle = String.valueOf(tableSponsors.getValueAt(tableSponsors.getSelectedRow(), 3));
		String numero = String.valueOf(tableSponsors.getValueAt(tableSponsors.getSelectedRow(), 4));
		String comentario = String.valueOf(tableSponsors.getValueAt(tableSponsors.getSelectedRow(), 5));
		Sponsor s = new Sponsor(id, razonSocial, cuit, calle, numero, comentario);
		return s;
	}
	
	private void informarError(String mensaje, String titulo){
		JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
	}
	private void informarUsuario(String mensaje, String titulo){
		JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
	}
	
	private boolean validarSeleccion(){
		if(tableSponsors.getSelectedRow()<=0){
			informarError("Por favor, seleccione un sponsor", "Modificar sponsors");
			return false;
		}
		else {
			return true;
		}
	}
	private void clickNuevo(){
		Sponsor s = new Sponsor();
		s.setId(0);
		EditSponsor edit = new EditSponsor();
		edit.open(s);
	}
	private void clickEditar(){
		if(!validarSeleccion()){
			return;
		}
		Sponsor s = new Sponsor();
		s=mapearDeTabla();
		EditSponsor edit = new EditSponsor();
		try{

			edit.open(s);
		}
		catch(Exception ex){
			informarError("Error no controlado al intentar leer los datos de la tabla","Editar Sponsors");
		}
		
	}
	
	private void clickEliminar(){
		if(!validarSeleccion()){
			return;
		}
		
		ls= new LogicSponsors();
		Sponsor s = new Sponsor();
		try{
			s=mapearDeTabla();
			ls.DeleteSponsor(s);
			tableSponsors.removeRowSelectionInterval(tableSponsors.getSelectedRow(), tableSponsors.getSelectedRow());
			informarUsuario("Sponsor eliminado correctamente", "Eliminar Sponsor");
		}
		catch(SQLException sqlex){
			informarError(sqlex.getMessage(), "Eliminar Sponsor");
		}
		catch(Exception ex){
			informarError(ex.getMessage(), "Eliminar Sponsor");
		}
	}
	
	private void verContactos(){
		EditContacto et = new EditContacto();
		et.open(mapearDeTabla());
	}
}
