package publicidad;

import java.sql.SQLException;

import javax.swing.JFrame;

import entidades.Contrato;
import generic.GenericAbm;
import logica.LogicContrato;

public class EditContrato extends GenericAbm {

	private JFrame frameEditContrato;

	private Contrato contratoAct;
	private LogicContrato lc;
	/**
	 * Create the application.
	 */
	public EditContrato() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameEditContrato = new JFrame();
		frameEditContrato.setBounds(100, 100, 450, 300);
		frameEditContrato.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	protected void clickGuardar() {
		// TODO Auto-generated method stub
		lc = new LogicContrato();
		try{
			contratoAct=mapearDeFormulario();
			if(contratoAct.getId()>0){
				lc.updateContrato(contratoAct);
			}
			else {
				lc.createContrato(contratoAct);
				super.informarUsuario("Contrato guardado correctamente", "Modificar Contratos");
			}						
		}
		catch(SQLException sqlex){
			super.informarError(sqlex.getMessage(), "Modificar Contratos");
		}
		catch(Exception ex){
			super.informarError(ex.getMessage(), "Modificar Contratos");
		}
	}

	@Override
	protected void clickCancelar() {
		// TODO Auto-generated method stub
		frameEditContrato.dispose();
	}

	@Override
	protected Contrato mapearDeFormulario() {
		// TODO Auto-generated method stub
		return null;
	}
	private void mapearAFormulario(Contrato c) {
		
	}
	
	public void open(Contrato c){
		contratoAct=c;
		mapearAFormulario(contratoAct);
	}

}
