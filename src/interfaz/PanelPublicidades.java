package interfaz;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import entidades.Sponsor;
import logica.LogicPublicidad;
import logica.LogicSponsors;
import util.AppException;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JComboBox;

public class PanelPublicidades extends JPanel {
	private JTextField txtFechaIni;
	private JTextField txtFechaFin;
	private JTextField txtMonto;
	private JTextField txtFechaPago;
	private JLabel lblSponsor;
	private JComboBox comboSponsors;
	private LogicPublicidad lp = null;

	/**
	 * Create the panel.
	 */
	public PanelPublicidades() {
		
		txtFechaIni = new JTextField();
		txtFechaIni.setColumns(10);
		
		JLabel lblFechaDeInicio = new JLabel("Fecha de Inicio:");
		
		JLabel lblFechaDeFin = new JLabel("Fecha de fin:");
		
		txtFechaFin = new JTextField();
		txtFechaFin.setColumns(10);
		
		JLabel lblMonto = new JLabel("Monto:");
		
		txtMonto = new JTextField();
		txtMonto.setColumns(10);
		
		JLabel lblFechaDePago = new JLabel("Fecha de pago:");
		
		txtFechaPago = new JTextField();
		txtFechaPago.setColumns(10);
		
		lblSponsor = new JLabel("Sponsor:");
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickGuardar();
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickCancelar();
			}
		});
		
		comboSponsors = new JComboBox();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblFechaDeInicio)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(txtFechaIni, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblFechaDeFin)
									.addGap(17)
									.addComponent(txtFechaFin, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblMonto)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(12)
											.addComponent(btnCancelar))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(txtMonto, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(lblFechaDePago)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txtFechaPago, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(31)
							.addComponent(lblSponsor)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboSponsors, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(292, Short.MAX_VALUE)
					.addComponent(btnGuardar)
					.addGap(94))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(156)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSponsor)
						.addComponent(comboSponsors, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFechaFin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtFechaIni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFechaDeInicio)
						.addComponent(lblFechaDeFin))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMonto)
						.addComponent(txtMonto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFechaDePago)
						.addComponent(txtFechaPago, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGuardar)
						.addComponent(btnCancelar))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		llenarCombo();
	}

	private void llenarCombo(){
		ArrayList<Sponsor> sponsors = new ArrayList<Sponsor>();
		LogicSponsors ls = new LogicSponsors();
		try{
			sponsors = ls.getSponsors();
			for (int i=0; i<sponsors.size(); i++){
				comboSponsors.addItem(sponsors.get(i));
			}
		}
		catch(SQLException sqlex){
			informarError(sqlex.getMessage(), "Consultar Sponsors");
		}
		catch(Exception ex){
			informarError(ex.getMessage(), "Consultar Sponsors");
		}
		
		
	}
	private void informarError(String mensaje, String titulo){
		JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
	}
	private void informarUsuario(String mensaje, String titulo){
		JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void clickCancelar(){
		this.removeAll();
	}
	private void clickGuardar(){
		lp= new LogicPublicidad();
		Sponsor s = new Sponsor();
		Double monto;
		try{
			monto = Double.parseDouble(txtMonto.getText());
			s.setRazonSocial(String.valueOf(comboSponsors.getSelectedItem()));
			lp.createPublicidad(s, convertFecha(txtFechaIni.getText()), convertFecha(txtFechaFin.getText()), Integer.parseInt(txtFechaPago.getText()), monto);
			informarUsuario("Publicidad guardad correctamente", "Agregar Publicidad");
		}
		catch(ParseException pex){
			informarError(pex.getMessage(), "Agregar Publicidad");
		}
		catch(SQLException sqlex){
			informarError(sqlex.getMessage(), "Agregar Publicidad");
		}
		catch(Exception ex){
			informarError(ex.getMessage(), "Agregar Publicidad");
		}		
	}
	
	private java.sql.Date convertFecha(String fecha) throws Exception{
		java.sql.Date sqlDate = null;
		try{
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date date = format.parse(fecha);
			sqlDate = new java.sql.Date(date.getTime());
		}
		catch(ParseException pex){
			throw new AppException("Error al intentar convertir las fechas");
		}
		catch(Exception ex){
			throw new Exception("Error no controlado al intentar convertir las fechas");
		}
		return sqlDate;	
 
	}
}
