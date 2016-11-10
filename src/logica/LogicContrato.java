package logica;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import datos.DataContrato;
import datos.DataSponsor;
import entidades.Sponsor;
import entidades.Contrato;

public class LogicContrato {
	
	private DataSponsor ds = null;
	private DataContrato dc = null;
	
	public void createPublicidad(Sponsor s, Date fechaInicio, Date fechaFin, int diaPago, Double monto, String codigo) throws Exception{		
		ds = new DataSponsor();
		dc = new DataContrato();
		try{
			s=ds.getIdByRS(s);
			
			dc.insertPublicidad(s, fechaInicio, fechaFin, diaPago, monto, codigo);
		}
		catch(SQLException sqlex){
			throw sqlex;
		}
		catch(Exception ex){
			throw ex;
		}
	}
	
	public void registrarPago(){
		
	}
	
	public ArrayList<Contrato> getContratos() throws Exception{
		dc = new DataContrato();
		ds = new DataSponsor();
		ArrayList<Contrato> contratos = new ArrayList<Contrato>();
		try{
			contratos=dc.contratos();
			for(Contrato c : contratos){
				c.setSponsor(ds.getById(c.getSponsor())); //Le paso el sponsor que tiene solo seteado id
			}
		}
		catch(SQLException sqlex){
			throw sqlex;
		}
		catch(Exception ex){
			throw ex;
		}
		return contratos;
	}
	
	public Sponsor getSponsor(Sponsor s) throws Exception{
		ds = new DataSponsor();
		try{
			s = ds.getById(s);
		}
		catch(SQLException sqlex){
			throw sqlex;
		}
		catch(Exception ex){
			throw ex;
		}
		return s;
	}

}
