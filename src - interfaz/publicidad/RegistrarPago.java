package publicidad;

import generic.Generic;
import logica.LogicRegistrarPago;

import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import net.miginfocom.swing.MigLayout;
import util.Validate;

import javax.swing.JTextField;

import entidades.ComprobantePagoSponsor;
import entidades.Contrato;
import entidades.Pago;

import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class RegistrarPago extends Generic{
	private JTextField textImporte;
	private JComboBox<Pago> comboCuotas;
	private JTextField textRecargo;
	private JTextField textDescuento;
	private JCheckBox chckbxDescuento;
	private JCheckBox chckbxRecargo;
	private JButton btnAgregarCuota;
	private Contrato contratoAct;
	private JPanel panelFooter;
	private ViewComprobante vc = null;
	
	public RegistrarPago() {
		setLayout(new MigLayout("", "[450px,grow]", "[21px][21px][][][][][][][][][grow]"));
		
		JLabel lblNewLabel = new JLabel("Cuota:");
		add(lblNewLabel, "flowx,cell 0 1");
		
		JLabel lblImporte = new JLabel("Importe:");
		add(lblImporte, "flowx,cell 0 3");
		
		comboCuotas = new JComboBox<Pago>();
		add(comboCuotas, "cell 0 1,aligny center");
		
		textImporte = new JTextField();
		textImporte.setEnabled(false);
		add(textImporte, "cell 0 3,alignx center");
		textImporte.setColumns(10);
		
		JRadioButton rdbtnTotal = new JRadioButton("Total");
		rdbtnTotal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickImporteTotal();
			}
		});
		rdbtnTotal.setSelected(true);
		add(rdbtnTotal, "cell 0 3");
		
		JRadioButton rdbtnOtro = new JRadioButton("Otro");
		rdbtnOtro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickOtroImporte();
			}
		});
		add(rdbtnOtro, "cell 0 3");
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnOtro);
		bg.add(rdbtnTotal);
		
		JLabel lblRecargo = new JLabel("Recargo:");
		add(lblRecargo, "flowx,cell 0 5");
		
		textRecargo = new JTextField();
		textRecargo.setText("0.0%");
		textRecargo.setEnabled(false);
		add(textRecargo, "cell 0 5");
		textRecargo.setColumns(10);
		
		chckbxRecargo = new JCheckBox("Efectuar Recargo");
		chckbxRecargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickRecargo();
			}
		});
		add(chckbxRecargo, "cell 0 5");
		
		JLabel lblDescuento = new JLabel("Descuento:");
		add(lblDescuento, "flowx,cell 0 7");
		
		textDescuento = new JTextField();
		textDescuento.setText("0.0%");
		textDescuento.setEnabled(false);
		add(textDescuento, "cell 0 7");
		textDescuento.setColumns(10);
		
		chckbxDescuento = new JCheckBox("Efectuar Descuento");
		chckbxDescuento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickDescuento();
			}
		});
		add(chckbxDescuento, "cell 0 7");
		
		btnAgregarCuota = new JButton("Agregar Cuota");
		btnAgregarCuota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarCuota();
			}
		});
		add(btnAgregarCuota, "flowx,cell 0 9,alignx center");
		
		panelFooter = new JPanel();
		add(panelFooter, "cell 0 10,grow");
		panelFooter.setLayout(new BoxLayout(panelFooter, BoxLayout.Y_AXIS));
	}
	
	private void clickRecargo(){
		if(textRecargo.isEnabled()){
			textRecargo.setEnabled(false);
		}else{
			textRecargo.setEnabled(true);
		}
	}
	
	private void clickDescuento(){
		if(textDescuento.isEnabled()){
			textDescuento.setEnabled(false);
		}else{
			textDescuento.setEnabled(true);
		}
	}
	
	private void clickOtroImporte(){
		textImporte.setEnabled(true);
		textImporte.setText("");
	}
	
	private void clickImporteTotal(){
		textImporte.setEnabled(false);
		textImporte.setText(String.valueOf(contratoAct.getMonto()));
	}
	
	private void mapearAForm(Contrato c){
		for(Pago p : c.getPagos()){
			if (p.getFechaPago()==null){
				comboCuotas.addItem(p);
			}
		}
		textImporte.setText(String.valueOf(contratoAct.getMonto()));
	}
	
	public void setContrato(Contrato c){
		
		LogicRegistrarPago lrp = new LogicRegistrarPago();
		try{
			// Recupero las cuotas del contrato //
			c=lrp.setPagos(c);
			contratoAct=c;
			mapearAForm(c);			
		}
		catch(Exception ex){
			super.informarError(ex.getMessage(), "Registrar Pagos.");
		}
	}
	
	private boolean validarDatos(){
		/*
		 * Cuota seleccionada.
		 * Importe números.
		 * Si hay porcentaje (recargo o descuento) entre 0 y 100%
		 */
		boolean band = true;
		Validate v = new Validate();
		
		if(comboCuotas.getSelectedIndex()<0){
			super.informarError("Por favor seleccione una cuota.", "Registro de Pagos.");
			band=false;
		}
		
		if( !v.numeroDecimal(new String[] {textImporte.getText()}) ){
			super.informarError("El importe debe contener sólo números.", "Registro de Pagos.");
			band = false;
		}
		
		return band;
	}
	
	private Pago mapearPagoDeForm(){
		Pago p;
		Float importe;
		p = (Pago)comboCuotas.getSelectedItem();
		comboCuotas.removeItemAt(comboCuotas.getSelectedIndex());
		importe = Float.valueOf(textImporte.getText());
		p.setImporteAbonado(importe);
		
		if(chckbxRecargo.isSelected()){
			Float recargo;
			recargo = Float.valueOf(textRecargo.getText());
			p.setRecargo(recargo);
		}else{
			p.setRecargo(0F);
		}
		
		if(chckbxDescuento.isSelected()){
			Float descuento;
			descuento = Float.valueOf(textDescuento.getText());
			p.setDescuento(descuento);
		}else{
			p.setDescuento(0F);
		}
		
		return p;
	}
	private void agregarCuota(){
		if(!validarDatos()){
			return;
		}
		if(vc==null){
			vc = new ViewComprobante();
			vc.inicializar(contratoAct);
			panelFooter.removeAll();
			panelFooter.add(vc);
			panelFooter.repaint();
			panelFooter.revalidate();
		}
		vc.addCuota(mapearPagoDeForm());
	}

}
