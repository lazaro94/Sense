package gestionCobro;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import entidades.Contrato;
import entidades.Sponsor;
import logica.LogicContrato;

import com.jgoodies.forms.layout.FormSpecs;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SpringLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

public class RegistrarCobro extends JPanel {
	private JTextField txtMonto;
	private LogicContrato lc;
	private JComboBox<Contrato> comboContratos;
	private ArrayList<Contrato> contratos = new ArrayList<Contrato>();
	private JLabel lblFechaFin;
	private JLabel lblSponsor;
	private JLabel lblFechaInicio;
	private JLabel lblMonto;
	private JLabel lblComentario;

	/**
	 * Create the panel.
	 */
	public RegistrarCobro() {
		MyItemListener actionListener = new MyItemListener();
		setLayout(new MigLayout("", "[45px][10px][45px][23px][21px][13px][19px][6px][61px][6px][102px]", "[20px][14px][14px][32px][14px][20px][23px]"));
		
		JLabel lblSeleccionarUnContrato = new JLabel("Seleccionar un Contrato:");
		add(lblSeleccionarUnContrato, "cell 0 0 5 1,alignx right,aligny center");
		
		comboContratos = new JComboBox<Contrato>();
		add(comboContratos, "cell 8 0 3 1,growx,aligny top");
		comboContratos.addItemListener(actionListener);
		comboContratos.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    }
		});
		
		JLabel lblDatosContrato = new JLabel("Datos del Contrato");
		lblDatosContrato.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblDatosContrato, "cell 4 1 5 1,alignx left,aligny top");
		
		lblMonto = new JLabel("Monto:");
		add(lblMonto, "cell 0 2,alignx right,aligny top");
		
		lblSponsor = new JLabel("Sponsor:");
		add(lblSponsor, "cell 4 2 3 1,alignx right,aligny top");
		
		lblFechaInicio = new JLabel("Fecha Inicio");
		add(lblFechaInicio, "cell 0 4 3 1,alignx left,aligny top");
		
		lblFechaFin = new JLabel("Fecha Fin:");
		add(lblFechaFin, "cell 4 4 5 1,alignx left,aligny top");
		
		JLabel lblMontoAbonado = new JLabel("Monto abonado:");
		add(lblMontoAbonado, "cell 2 5 3 1,alignx left,aligny center");
		
		txtMonto = new JTextField();
		add(txtMonto, "cell 6 5 3 1,alignx left,aligny top");
		txtMonto.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnCancelar, "cell 0 6 3 1,alignx right,aligny top");
		
		JButton btnRegistrar = new JButton("Registrar");
		add(btnRegistrar, "cell 10 6,alignx left,aligny top");
		
		lblComentario = new JLabel("Comentario");
		add(lblComentario, "cell 0 3 11 1,grow");
		loadCombo();
	}
	
	private void loadCombo(){		
		lc = new LogicContrato();	
		try{
			contratos = lc.getContratos();
			for(int i=0; i<contratos.size(); i++){
				comboContratos.addItem(contratos.get(i));
			}
		}
		catch(SQLException sqlex){
			informarError(sqlex.getMessage());
		}
		catch(Exception ex){
			informarError(ex.getMessage());
		}
	}
	
	private void actualizarDatos(){
		Sponsor s = new Sponsor();
		Contrato c = new Contrato();
		lc = new LogicContrato();
		try{
			c.setCodigo(String.valueOf(comboContratos.getSelectedItem()));
			c = contratos.get(contratos.indexOf(c));
			s.setId(c.getIdSponsor());
			s = lc.getSponsor(s);
			lblFechaInicio.setText("Fecha inicio: " + String.valueOf(c.getFechaInicio()));
			lblFechaFin.setText("Fecha fin: " + String.valueOf(c.getFechaFin()));
			lblMonto.setText("Monto: " + c.getMonto());
			lblSponsor.setText("Sponsor: " + s.getRazonSocial());
			lblComentario.setText("Comentario: " + c.getComentario());
		}
		catch(SQLException sqlex){
			informarError(sqlex.getMessage());
		}
		catch(Exception ex){
			informarError(ex.getMessage());
		}
	}
	
	private void informarError(String mensaje){
		JOptionPane.showMessageDialog(this, mensaje, "Registro de pagos", JOptionPane.ERROR_MESSAGE);
	}
	
	class MyItemListener implements ItemListener {
		  // This method is called only if a new item has been selected.
		  public void itemStateChanged(ItemEvent evt) {
		    JComboBox cb = (JComboBox) evt.getSource();

		    Object item = evt.getItem();

		    if (evt.getStateChange() == ItemEvent.SELECTED) {
		      actualizarDatos();
		    } else if (evt.getStateChange() == ItemEvent.DESELECTED) {
		      // Item is no longer selected
		    }
		  }
		}
}

