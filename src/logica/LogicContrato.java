package logica;

import java.sql.SQLException;
import java.util.ArrayList;

import com.data.sponsor.DataContrato;
import com.data.sponsor.DataPago;
import com.data.sponsor.DataSponsor;

import entidades.Contrato;

public class LogicContrato {
	
	private DataSponsor ds;
	private DataContrato dc;
	private DataPago dp;
	
	public void createContrato(Contrato c) throws Exception{		
		dc = new DataContrato();
		dp = new DataPago();
		try{
			c.generarPagos();
			c=dc.insertContrato(c);
			dp.insertCuotas(c);
			
		}
		catch(SQLException sqlex){
			throw sqlex;
		}
		catch(Exception ex){
			throw ex;
		}
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
	
	public void updateContrato(Contrato c) throws Exception{
		dc = new DataContrato();
		try{
			dc.update(c);
		}
		catch(SQLException sqlex){
			throw sqlex;
		}
		catch(Exception ex){
			throw ex;
		}
	}
	public void anularContrato(Contrato c) throws Exception{
		dc = new DataContrato();
		try{
			dc.anular(c);
		}
		catch(SQLException sqlex){
			throw sqlex;
		}
		catch(Exception ex){
			throw ex;
		}
	}
}
