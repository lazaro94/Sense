package sponsors;

import javax.swing.JPanel;

import entidades.Sponsor;
import logica.LogicSponsor;
import util.AppException;

import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelSponsor extends JPanel{
	
	private ViewSponsor vs = new ViewSponsor();
	private ViewContactos vc = new ViewContactos();
	
	public PanelSponsor() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		addPanels();
	}

	public void addPanels(){
		this.removeAll();
		this.add(vs);
		
		JPanel panelButtons = new JPanel();
		add(panelButtons);
		panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.X_AXIS));
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		panelButtons.add(btnEditar);
		
		JLabel lblNewLabel = new JLabel("  ");
		panelButtons.add(lblNewLabel);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregar();
			}
		});
		panelButtons.add(btnAgregar);
		
		JLabel lblNewLabel_1 = new JLabel("  ");
		panelButtons.add(lblNewLabel_1);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});
		panelButtons.add(btnEliminar);
		
		JLabel lblNewLabel_2 = new JLabel("     ");
		panelButtons.add(lblNewLabel_2);
		
		JButton btnContactos = new JButton("Ver Contactos");
		btnContactos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verContactos();
			}
		});
		panelButtons.add(btnContactos);
				
		this.add(vc);
		
		this.revalidate();
		this.repaint();
	}
	
	private void editar(){
		vs.editar();
	}
	
	private void agregar(){
		vs.nuevo();
	}
	
	private void eliminar(){
		vs.eliminar();
	}
	
	private void verContactos(){
		Sponsor s;
		try{
			s=vs.getSponsor();
			vc.show(s);
			this.add(vc);
		}
		catch(Exception ex){
			informarError(ex.getMessage(), "Ver Contactos");
		}
	}
	private void informarError(String mensaje, String titulo){
		JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
	}
}
