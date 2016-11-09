package sponsor;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entidades.Contacto;
import entidades.Sponsor;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewContactos extends JPanel {
	
	private JTable tableContactos;
	private JLabel lblSponsor;
	private JLabel label_1;
	private JPanel panelButtons;
	private JButton btnNuevo;
	private JLabel label_2;
	private JButton btnEditar;
	private JLabel label_3;
	private JButton btnNewButton;
	
	private Sponsor s = null;
	
	
	/**
	 * Create the panel.
	 */
	public ViewContactos() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel label = new JLabel("   ");
		add(label);
		
		lblSponsor = new JLabel("");
		lblSponsor.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblSponsor);
		
		tableContactos = new JTable();
		add(tableContactos);
		
		label_1 = new JLabel("  ");
		add(label_1);
		
		panelButtons = new JPanel();
		add(panelButtons);
		panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.X_AXIS));
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevo();
			}
		});
		panelButtons.add(btnNuevo);
		
		label_2 = new JLabel("  ");
		panelButtons.add(label_2);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		panelButtons.add(btnEditar);
		
		label_3 = new JLabel("  ");
		panelButtons.add(label_3);
		
		btnNewButton = new JButton("Eliminar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});
		panelButtons.add(btnNewButton);

	}
	
	public void show(Sponsor sponsor){
		s = sponsor;
		String[] columnas = {"IdContacto","Apellido","Nombre","Cargo", "DNI", "Telefono 1", "Telefono 2", "E-Mail", "Direccion"};
		DefaultTableModel tableModel = new DefaultTableModel();
		ArrayList<Contacto> contactos = s.getContactos();
		try{
			tableModel.setColumnIdentifiers(columnas);
			Object[] fila = new Object[tableModel.getColumnCount()];
			for (int i = 0; i < contactos.size(); i++) {

				fila[0] = contactos.get(i).getIdContacto();
				fila[1] = contactos.get(i).getApellido();
				fila[2] = contactos.get(i).getNombre();
				fila[3] = contactos.get(i).getCargo();
				fila[4] = contactos.get(i).getDni();
				fila[5] = contactos.get(i).getTelefono1();
				fila[6] = contactos.get(i).getTelefono2();
				fila[7] = contactos.get(i).getMail();
				fila[8] = contactos.get(i).getDireccion();
				
				tableModel.addRow(fila);
				}
			tableContactos.setModel(tableModel);
			//Seteo ancho de columnas
			tableContactos.getColumnModel().getColumn(1).setPreferredWidth(70);
			tableContactos.getColumnModel().getColumn(2).setPreferredWidth(70);
			tableContactos.getColumnModel().getColumn(3).setPreferredWidth(90);
			tableContactos.getColumnModel().getColumn(4).setPreferredWidth(65);
			tableContactos.getColumnModel().getColumn(5).setPreferredWidth(70);
			tableContactos.getColumnModel().getColumn(6).setPreferredWidth(70);
			tableContactos.getColumnModel().getColumn(7).setPreferredWidth(200);
			tableContactos.getColumnModel().getColumn(8).setPreferredWidth(200);
			//Oculto columna de IdContacto
			tableContactos.getColumnModel().getColumn(0).setMaxWidth(0);
			tableContactos.getColumnModel().getColumn(0).setMinWidth(0);
			tableContactos.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
			tableContactos.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
			
			lblSponsor.setText(s.getRazonSocial());
			
		}
		catch(Exception ex){
			informarError(ex.getMessage(), "Visualizar Contactos");
		}		
	}
	
	private void informarError(String mensaje, String titulo){
		JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
	}
	
	private void eliminar(){
		
	}
	
	private void editar(){
		
	}
	
	private void nuevo(){
		Contacto c = new Contacto();
		c.setSponsor(s);
		EditContacto ec = new EditContacto();
		ec.open(c);
	}
	
	
	

}
