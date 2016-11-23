package publicidad;

import javax.swing.JFrame;

import generic.GenericAbm;
import javax.swing.SpringLayout;

import entidades.Contrato;
import entidades.Pago;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NewPago extends GenericAbm{

	private JFrame framePago;
	private JTextField textImporte;
	private JTextField textRecargo;
	private JTextField textComprobante;
	private JComboBox<Pago> comboCuotas;
	private JCheckBox chckRecargo;

	public NewPago() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		framePago = new JFrame();
		framePago.setBounds(100, 100, 398, 389);
		framePago.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		framePago.getContentPane().setLayout(springLayout);
		
		JLabel label = new JLabel("<Contrato>");
		springLayout.putConstraint(SpringLayout.NORTH, label, 10, SpringLayout.NORTH, framePago.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, label, -54, SpringLayout.EAST, framePago.getContentPane());
		framePago.getContentPane().add(label);
		
		JLabel lblCuota = new JLabel("Cuota:");
		springLayout.putConstraint(SpringLayout.NORTH, lblCuota, 42, SpringLayout.NORTH, framePago.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblCuota, 10, SpringLayout.WEST, framePago.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblCuota, 53, SpringLayout.WEST, framePago.getContentPane());
		framePago.getContentPane().add(lblCuota);
		
		comboCuotas = new JComboBox<Pago>();
		springLayout.putConstraint(SpringLayout.WEST, comboCuotas, 6, SpringLayout.EAST, lblCuota);
		springLayout.putConstraint(SpringLayout.EAST, comboCuotas, 138, SpringLayout.EAST, lblCuota);
		framePago.getContentPane().add(comboCuotas);
		comboCuotas.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        seleccionCombo();
		    }
		});
		
		JLabel lblImporte = new JLabel("Importe:");
		springLayout.putConstraint(SpringLayout.NORTH, lblImporte, 48, SpringLayout.SOUTH, lblCuota);
		springLayout.putConstraint(SpringLayout.WEST, lblImporte, 0, SpringLayout.WEST, lblCuota);
		framePago.getContentPane().add(lblImporte);
		
		textImporte = new JTextField();
		textImporte.setEnabled(false);
		textImporte.setEditable(false);
		springLayout.putConstraint(SpringLayout.WEST, textImporte, 7, SpringLayout.EAST, lblImporte);
		springLayout.putConstraint(SpringLayout.SOUTH, textImporte, 0, SpringLayout.SOUTH, lblImporte);
		framePago.getContentPane().add(textImporte);
		textImporte.setColumns(10);
		
		JRadioButton rdbtnTotal = new JRadioButton("Total");
		rdbtnTotal.setSelected(true);
		springLayout.putConstraint(SpringLayout.SOUTH, comboCuotas, -36, SpringLayout.NORTH, rdbtnTotal);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnTotal, 17, SpringLayout.EAST, textImporte);
		springLayout.putConstraint(SpringLayout.SOUTH, rdbtnTotal, 0, SpringLayout.SOUTH, lblImporte);
		framePago.getContentPane().add(rdbtnTotal);
		
		JRadioButton rdbtnOtro = new JRadioButton("Otro");
		springLayout.putConstraint(SpringLayout.WEST, label, -199, SpringLayout.EAST, rdbtnOtro);
		springLayout.putConstraint(SpringLayout.WEST, rdbtnOtro, 16, SpringLayout.EAST, rdbtnTotal);
		springLayout.putConstraint(SpringLayout.SOUTH, rdbtnOtro, 0, SpringLayout.SOUTH, lblImporte);
		framePago.getContentPane().add(rdbtnOtro);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnOtro);
		bg.add(rdbtnTotal);
		
		JLabel lblRecargo = new JLabel("Recargo:");
		springLayout.putConstraint(SpringLayout.NORTH, lblRecargo, 40, SpringLayout.SOUTH, lblImporte);
		springLayout.putConstraint(SpringLayout.WEST, lblRecargo, 0, SpringLayout.WEST, lblCuota);
		framePago.getContentPane().add(lblRecargo);
		
		textRecargo = new JTextField();
		textRecargo.setEditable(false);
		textRecargo.setEnabled(false);
		springLayout.putConstraint(SpringLayout.NORTH, textRecargo, -3, SpringLayout.NORTH, lblRecargo);
		springLayout.putConstraint(SpringLayout.WEST, textRecargo, 0, SpringLayout.WEST, comboCuotas);
		framePago.getContentPane().add(textRecargo);
		textRecargo.setColumns(10);
		
		chckRecargo = new JCheckBox("Efectuar Recargo");
		chckRecargo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickRecargo();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, chckRecargo, -4, SpringLayout.NORTH, lblRecargo);
		springLayout.putConstraint(SpringLayout.WEST, chckRecargo, 0, SpringLayout.WEST, rdbtnTotal);
		springLayout.putConstraint(SpringLayout.EAST, chckRecargo, -108, SpringLayout.EAST, framePago.getContentPane());
		framePago.getContentPane().add(chckRecargo);
		
		JLabel lblComprobante = new JLabel("Comprobante:");
		springLayout.putConstraint(SpringLayout.NORTH, lblComprobante, 40, SpringLayout.SOUTH, lblRecargo);
		springLayout.putConstraint(SpringLayout.WEST, lblComprobante, 0, SpringLayout.WEST, lblCuota);
		framePago.getContentPane().add(lblComprobante);
		
		textComprobante = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textComprobante, -3, SpringLayout.NORTH, lblComprobante);
		springLayout.putConstraint(SpringLayout.WEST, textComprobante, 6, SpringLayout.EAST, lblComprobante);
		springLayout.putConstraint(SpringLayout.EAST, textComprobante, 171, SpringLayout.EAST, lblComprobante);
		framePago.getContentPane().add(textComprobante);
		textComprobante.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		springLayout.putConstraint(SpringLayout.WEST, btnGuardar, 0, SpringLayout.WEST, rdbtnOtro);
		springLayout.putConstraint(SpringLayout.SOUTH, btnGuardar, -42, SpringLayout.SOUTH, framePago.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnGuardar, -66, SpringLayout.EAST, framePago.getContentPane());
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickGuardar();
			}
		});
		framePago.getContentPane().add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickCancelar();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnCancelar, 0, SpringLayout.NORTH, btnGuardar);
		springLayout.putConstraint(SpringLayout.WEST, btnCancelar, 0, SpringLayout.WEST, textComprobante);
		springLayout.putConstraint(SpringLayout.EAST, btnCancelar, -53, SpringLayout.WEST, rdbtnOtro);
		framePago.getContentPane().add(btnCancelar);
	}
	
	private void clickRecargo(){
		if(chckRecargo.isSelected()){
			textRecargo.setEnabled(false);
		} else{
			textRecargo.setEnabled(true);
		}
	}
	
	private void seleccionCombo(){
		if (comboCuotas.getSelectedIndex()>0){
			mapearAFormulario((Pago)comboCuotas.getSelectedItem());
		}
	}
	
	@Override
	protected void clickGuardar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void clickCancelar() {
		// TODO Auto-generated method stub
		framePago.dispose();
	}

	@Override
	protected Object mapearDeFormulario() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void mapearACombo(Contrato c){
		for(Pago p : c.getPagos()){
			if (p.getFechaPago()==null){
				comboCuotas.addItem(p);
			}			
		}
		comboCuotas.setSelectedIndex(0);
	}
	
	private void mapearAFormulario(Pago p){
		
	}
	
	public void open(Contrato c){
		mapearACombo(c);
		framePago.setVisible(true);
	}
	
	private boolean validar(){
		/*
		 * Importe real y no vacio.
		 * Combo seleccionado.
		 * Si recargo seleccionado--> real y no vacio.
		 * Comprobante NO VACIO.
		 */
		return true;
	}
}
