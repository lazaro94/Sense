package sponsor;

import generic.GenericAbm;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;

import logica.LogicSponsor;
import util.AppException;
import entidades.Sponsor;
import javax.swing.JLabel;
import javax.swing.BoxLayout;

public class ViewSponsor extends GenericAbm{
	private JTable tableSponsors;
	private LogicSponsor ls;
	private EditSponsor es;
	private ArrayList<Sponsor> sponsors = new ArrayList<Sponsor>();
	private JLabel lblSponsors;
	
	public ViewSponsor() {
		setLayout(new BorderLayout(0, 0));
		
		tableSponsors = new JTable();
		add(tableSponsors, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JLabel label_2 = new JLabel("        ");
		panel.add(label_2);
		
		lblSponsors = new JLabel("SPONSORS");
		add(lblSponsors, BorderLayout.NORTH);
		actualizarTabla();
	}
	
	public void actualizarTabla(){
		//DECLARACION VARIABLES
		String[] columnas = {"IdSponsors","Cuit","Razon Social","Direccion", "Numero", "Comentario"};
		DefaultTableModel tableModel = new DefaultTableModel();
		ls = new LogicSponsor();
		
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
			super.informarError(sqlex.getMessage(), "Consultar Sponsors");
		}
		catch(Exception ex){
			super.informarError(ex.getMessage(), "Consultar Sponsors");
		}		
	}
	
	private boolean validarSeleccion(){
		if(tableSponsors.getSelectedRow()<0){
			super.informarError("Por favor, seleccione un sponsor", "Modificar sponsors");
			return false;
		}
		else {
			return true;
		}
	}
	public void eliminar(){
		if(!validarSeleccion()){
			return;
		}
		
		ls= new LogicSponsor();
		Sponsor s;
		try{
			s=mapearDeFormulario();
			ls.DeleteSponsor(s);
			tableSponsors.removeRowSelectionInterval(tableSponsors.getSelectedRow(), tableSponsors.getSelectedRow());
			informarUsuario("Sponsor eliminado correctamente", "Eliminar Sponsor");
		}
		catch(SQLException sqlex){
			super.informarError(sqlex.getMessage(), "Eliminar Sponsor");
		}
		catch(Exception ex){
			super.informarError(ex.getMessage(), "Eliminar Sponsor");
		}
	}
	public void editar(){
		Sponsor s;
		try{
			s=mapearDeFormulario();
			es = new EditSponsor();
			es.open(s);
		}
		catch(Exception ex){
			super.informarError(ex.getMessage(), "Modificar Sponsor");
		}
	}
	public void nuevo(){
		Sponsor s = new Sponsor();
		s.setId(0);
		try{
			es = new EditSponsor();
			es.open(s);
		}
		catch(Exception ex){
			super.informarError(ex.getMessage(), "Modificar Sponsor");
		}
	}
	public Sponsor getSponsor() throws AppException{
		if(!validarSeleccion()){
			throw new AppException("Por favor, seleccione un sponsor");
		}
		return mapearDeFormulario();
	}

	@Override
	protected void clickGuardar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void clickCancelar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Sponsor mapearDeFormulario() {
		// TODO Auto-generated method stub
		int id= Integer.parseInt(String.valueOf(tableSponsors.getValueAt(tableSponsors.getSelectedRow(), 0)));
		Sponsor s = new Sponsor(id);
		s=sponsors.get(sponsors.indexOf(s));
		return s;
	}
}
